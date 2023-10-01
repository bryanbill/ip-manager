package com.syokinet.ipmanager.controller;

import com.syokinet.ipmanager.utils.IpValidationUtil;
import org.apache.commons.net.util.SubnetUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/subnet")
public class SubnetCalculatorController {
    @GetMapping("/calculate")
    public ResponseEntity<?> calculateSubnet(@RequestParam String ip, @RequestParam String subnetMask) {
        try {
            if(!IpValidationUtil.isValidIpAddress(ip))
                return ResponseEntity.badRequest().body(Collections.singletonMap("error", "Invalid IP address."));


            if(!IpValidationUtil.isValidSubnetMask(subnetMask))
                return ResponseEntity.badRequest().body(Collections.singletonMap("error", "Invalid subnet mask."));

            SubnetUtils utils = new SubnetUtils(ip, subnetMask);
            Map<String, String> result = getStringStringMap(utils);

            return ResponseEntity.ok(result);
        } catch (IllegalArgumentException e) {
            // Handle validation errors
            return ResponseEntity.badRequest().body(Collections.singletonMap("error", "Invalid input. Please check your IP address and subnet mask."));
        }
    }

    private static Map<String, String> getStringStringMap(SubnetUtils utils) {
        SubnetUtils.SubnetInfo info = utils.getInfo();

        String networkAddress = info.getNetworkAddress();
        String broadcastAddress = info.getBroadcastAddress();
        String usableIpRange = networkAddress + " to " + broadcastAddress;

        Map<String, String> result = new HashMap<>();
        result.put("networkAddress", networkAddress);
        result.put("broadcastAddress", broadcastAddress);
        result.put("usableIpRange", usableIpRange);
        return result;
    }
}
