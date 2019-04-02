package com.candywrapper.candywrapper;

import com.candywrapper.controller.ProductController;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(ProductController.class)
@AutoConfigureDataMongo
public class CandywrapperWebTests {

  @Autowired
  private MockMvc mvc;

  @Test
  public void getProductsTest() throws Exception {
    this.mvc.perform(get("/products/")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Products")));
  }

	@Test
	public void getSignupTest() throws Exception {
		this.mvc.perform(get("/accounts/signup")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString("Role")));
	}  
}