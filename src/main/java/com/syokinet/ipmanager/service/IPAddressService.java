package com.syokinet.ipmanager.service;

import com.syokinet.ipmanager.entity.Customer;
import com.syokinet.ipmanager.entity.IPAddress;
import com.syokinet.ipmanager.entity.IPAddressStatus;
import com.syokinet.ipmanager.repository.CustomerRepository;
import com.syokinet.ipmanager.repository.IPAddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IPAddressService {
    @Autowired
    private IPAddressRepository ipAddressRepository;

    @Autowired
    private CustomerRepository customerRepository;

    // Method to allocate an IP address
    public ResponseEntity<?> allocateIPAddress(String customerName, String email) {
        // Check if there are available IPs
        IPAddress availableIP = ipAddressRepository.findFirstByStatus(IPAddressStatus.AVAILABLE);
        if (availableIP == null) {
            return new ResponseEntity<>("No available IPs", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        // Set the IP status to ALLOCATED
        availableIP.setStatus(IPAddressStatus.ALLOCATED);

        // Find the customer in the database
        Optional<Customer> optionalCustomer = customerRepository.findByEmail(email);
        Customer customer;
        if (optionalCustomer.isEmpty()) {
            // Create a new customer if not found
            customer = new Customer(customerName, email);
            customerRepository.save(customer);
        } else {
            customer = optionalCustomer.get();
        }

        // Associate the customer with the allocated IP
        availableIP.setCustomer(customer);

        //update the IP address in the database
        ipAddressRepository.updateStatusAndCustomerByIp(availableIP.getStatus(), customer.getId(), availableIP.getIp());

        return new ResponseEntity<>(availableIP, HttpStatus.CREATED);
    }

    // Method to release an allocated IP address
    public ResponseEntity<?> releaseIPAddress(String ipAddress) {
        // Find the IP address in the database
        Optional<IPAddress> optionalIPAddress = ipAddressRepository.findByIp(ipAddress);
        if (optionalIPAddress.isEmpty()) {
            return new ResponseEntity<>("IP address not found", HttpStatus.NOT_FOUND);
        }

        IPAddress releasedIP = optionalIPAddress.get();

        // Check if the IP is already released
        if (releasedIP.getStatus() != IPAddressStatus.ALLOCATED) {
            return new ResponseEntity<>("IP is not all ocated", HttpStatus.NOT_FOUND);
        }

        // Set the IP status back to AVAILABLE and remove customer association
        releasedIP.setStatus(IPAddressStatus.AVAILABLE);
        releasedIP.setCustomer(null);

        // update the IP address in the database
        ipAddressRepository.updateStatusAndCustomerByIp(releasedIP.getStatus(), null, releasedIP.getIp());

        return new ResponseEntity<>("IP released successfully", HttpStatus.OK);
    }

    // Method to list allocated IP addresses
    public ResponseEntity<?> listAllocatedIPs(
            Optional<String> startIp,
            Optional<String> endIp
    ) {
        if (startIp.isPresent() && endIp.isPresent()) {
            List<IPAddress> allocatedIPs = ipAddressRepository.findByStatusAndIpBetween(IPAddressStatus.ALLOCATED, startIp, endIp);
            return new ResponseEntity<>(allocatedIPs, HttpStatus.OK);
        }

        List<IPAddress> allocatedIPs = ipAddressRepository.findByStatus(IPAddressStatus.ALLOCATED);
        return new ResponseEntity<>(allocatedIPs, HttpStatus.OK);
    }

    // Method to list available IP addresses
    public ResponseEntity<?> listAvailableIPs(
            Optional<String> startIp,
            Optional<String> endIp
    ) {
        if (startIp.isPresent() && endIp.isPresent()) {
            List<IPAddress> allocatedIPs = ipAddressRepository.findByStatusAndIpBetween(IPAddressStatus.AVAILABLE, startIp, endIp);
            return new ResponseEntity<>(allocatedIPs, HttpStatus.OK);
        }

        List<IPAddress> allocatedIPs = ipAddressRepository.findByStatus(IPAddressStatus.AVAILABLE);
        return new ResponseEntity<>(allocatedIPs, HttpStatus.OK);
    }


}
