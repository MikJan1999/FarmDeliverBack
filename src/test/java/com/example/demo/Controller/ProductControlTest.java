package com.example.demo.Controller;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser
class ProductControlTest {
    @Autowired
    private MockMvc mockMvc;
@Test
    void editById() throws Exception {
//given
    //when
    mockMvc.perform(put("/product/edit/1"))
            .andDo(print())
            .andExpect(MockMvcResultMatchers.status().is(200))
            .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)));

    //then
}
@Test
void getById() throws Exception {
    mockMvc.perform(get("/product/get/1"))
            .andDo(print())
            .andExpect(MockMvcResultMatchers.status().is(200))
            .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)));


}
}