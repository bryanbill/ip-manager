package com.syokinet.ipmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.syokinet.ipmanager.entity")
public class IpmanagerApplication {
	public static void main(String[] args) {
		SpringApplication.run(IpmanagerApplication.class, args);
	}
}
