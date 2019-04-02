package com.candywrapper.candywrapper;

import static org.assertj.core.api.Assertions.assertThat;

import com.candywrapper.controller.AccountController;
import com.candywrapper.controller.ProductController;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CandywrapperApplicationTests {

	@Autowired
	private ProductController productController;

	@Autowired
	private AccountController accountController;	

	@Test
	public void contextLoads() throws Exception {
		assertThat(productController).isNotNull();
		assertThat(accountController).isNotNull();
	}
}