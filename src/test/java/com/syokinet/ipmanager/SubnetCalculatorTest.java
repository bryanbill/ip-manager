package com.syokinet.ipmanager;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class SubnetCalculatorTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testSubnetCalculator() throws Exception {
        String ipAddressStr = "192.168.1.1";
        String subnetMaskStr = "255.255.255.0";

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/subnet/calculate?ip=" + ipAddressStr + "&subnetMask=" + subnetMaskStr)
                        .with(httpBasic("admin", "admin"))
                        .contentType("application/json")
                )
                .andExpect(status().isOk());
    }
}
