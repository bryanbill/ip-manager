package com.syokinet.ipmanager;

import com.syokinet.ipmanager.repository.IPAddressRepository;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class IPAddressControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testAllocateIPAddress() throws Exception {

        String body = new JSONObject()
                .put("name", "John Doe")
                .put("email", "doe@example.com")
                .toString();

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/ip/allocate")
                        .with(httpBasic("admin", "admin"))
                        .contentType("application/json")
                        .content(body)
                )
                .andExpect(status().isCreated());
    }

    @Test
    public void testReleaseIPAddress() throws Exception {
        String ipAddressStr = "192.168.1.1";

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/ip/release/" + ipAddressStr)
                        .with(httpBasic("admin", "admin"))
                        .contentType("application/json")
                )
                .andExpect(status().isOk());
    }

    @Test
    public void testListAllocatedIPs() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/ip/allocated")
                        .with(httpBasic("admin", "admin"))
                        .contentType("application/json")
                )
                .andExpect(status().isOk());
    }

    @Test
    public void testListAvailableIPs() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/ip/available")
                        .with(httpBasic("admin", "admin"))
                        .contentType("application/json")
                )
                .andExpect(status().isOk());
    }

}
