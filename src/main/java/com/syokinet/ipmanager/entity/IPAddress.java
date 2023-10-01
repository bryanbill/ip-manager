package com.syokinet.ipmanager.entity;

import jakarta.persistence.*;

@Entity
public class IPAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ip;

    @Enumerated(EnumType.STRING)
    private IPAddressStatus status;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;


    public IPAddress() {
    }

    public IPAddress(String ip, IPAddressStatus status, Customer customer) {
        this.ip = ip;
        this.status = status;
        this.customer = customer;
    }

    public Long getId() {
        return id;
    }

    public String getIp() {
        return ip;
    }

    public IPAddressStatus getStatus() {
        return status;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public void setStatus(IPAddressStatus status) {
        this.status = status;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "IPAddress{" +
                "id=" + id +
                ", ip='" + ip + '\'' +
                ", status=" + status +
                ", customer=" + customer +
                '}';
    }

}
