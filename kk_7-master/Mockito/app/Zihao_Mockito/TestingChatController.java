package com.springboot.app.Zihao_Mockito;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.ArrayList;
import java.util.List;

import com.springboot.app.Controllers.ChatController;
import com.springboot.app.Databases.ItemDatabase;
import com.springboot.app.Databases.UserDatabase;
import com.springboot.app.Entities.Item;
import com.springboot.app.Services.UserService;

@RunWith(SpringRunner.class)
@WebMvcTest(ChatController.class)
public class TestingChatController {

	@TestConfiguration
	static class StringContextConfiguration { // can be named whatever

		@Bean
		public UserService uService() {
			return new UserService();
		}

		@Bean
		ItemDatabase getRepoItem() {
			return mock(ItemDatabase.class);
		}

		@Bean
		UserDatabase getRepoUser() {
			return mock(UserDatabase.class);
		}

	}

	@Autowired
	private MockMvc controller;

	@Autowired
	private ItemDatabase itemList;

	@Test
	public void testGetPurchases() throws Exception {

		List<Item> l = new ArrayList<Item>();

		when(itemList.findAll()).thenReturn(l);

		controller.perform(get("/home/test@gmail.com/messages").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().is(200)).andExpect(MockMvcResultMatchers.view().name("message_home"))
				.andExpect(MockMvcResultMatchers.model().attribute("Email", "test@gmail.com"));
	}

}