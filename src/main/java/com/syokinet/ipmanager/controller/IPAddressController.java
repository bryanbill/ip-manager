package com.syokinet.ipmanager.controller;

import com.syokinet.ipmanager.entity.Customer;
import com.syokinet.ipmanager.service.IPAddressService;
import com.syokinet.ipmanager.utils.IpValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/ip")
public class IPAddressController {

    @Autowired
    private IPAddressService ipAddressService;

    // Endpoint to allocate IP Address
    @PostMapping("/allocate")
    public ResponseEntity<?> allocateIPAddress(@RequestBody Customer customer) {
        String customerName = customer.getCustomerName();
        String email = customer.getEmail();

        return ipAddressService.allocateIPAddress(customerName, email);
    }

    // Endpoint to release IP Address
    @PutMapping("/release/{ipAddress}")
    public ResponseEntity<?> releaseIPAddress(@PathVariable String ipAddress) {
        if (!IpValidationUtil.isValidIpAddress(ipAddress))
            return ResponseEntity.badRequest().body("Invalid IP Address");

        return ipAddressService.releaseIPAddress(ipAddress);
    }

    // Endpoint to list allocated IP Addresses
    @GetMapping("/allocated")
    public ResponseEntity<?> listAllocatedIPs(
            @RequestParam(required = false) Optional<String> startIp,
            @RequestParam(required = false) Optional<String> endIp
    ) {
        if (startIp.isPresent() && !IpValidationUtil.isValidIpAddress(startIp.get()))
            return ResponseEntity.badRequest().body("Invalid start IP Address");

        if (endIp.isPresent() && !IpValidationUtil.isValidIpAddress(endIp.get()))
            return ResponseEntity.badRequest().body("Invalid end IP Address");

        return ipAddressService.listAllocatedIPs(startIp, endIp);
    }

    // Endpoint to list available IP Addresses
    @GetMapping("/available")
    public ResponseEntity<?> listAvailableIPs(
            @RequestParam(required = false) Optional<String> startIp,
            @RequestParam(required = false) Optional<String> endIp
    ) {
        if (startIp.isPresent() && !IpValidationUtil.isValidIpAddress(startIp.get()))
            return ResponseEntity.badRequest().body("Invalid start IP Address");

        if (endIp.isPresent() && !IpValidationUtil.isValidIpAddress(endIp.get()))
            return ResponseEntity.badRequest().body("Invalid end IP Address");

        return ipAddressService.listAvailableIPs(
                startIp,
                endIp
        );
    }

}
