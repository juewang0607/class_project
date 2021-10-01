package com.springboot.app.Zihao_Mockito;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import com.springboot.app.Databases.ItemDatabase;
import com.springboot.app.Databases.UserDatabase;
import com.springboot.app.Entities.Item;
import com.springboot.app.Entities.User;
import com.springboot.app.Services.ChangeItemInfoService;
import com.springboot.app.Services.ChangeUserInfoService;
import com.springboot.app.Services.LoginService;
import com.springboot.app.Services.UserService;

@RunWith(SpringRunner.class)
public class TestingChangeUnit {

	@TestConfiguration
	static class StringContextConfiguration { // can be named whatever

		@Bean
		public ChangeUserInfoService lService() {
			return new ChangeUserInfoService();
		}

		@Bean
		UserDatabase getRepo() {
			return mock(UserDatabase.class);
		}

		@Bean
		public UserService uService() {
			return new UserService();
		}

	}

	@Autowired
	private ChangeUserInfoService ls;

	@Autowired
	private UserDatabase db;

	@Test
	public void testChangeUnit() {

		List<User> l = new ArrayList<User>();

		when(db.findAll()).thenReturn(l);

		when(db.save((User) any(User.class))).thenAnswer(x -> {
			User r = x.getArgument(0);
			l.add(r);
			return null;
		});
		Boolean isRight;
		Boolean isWrong;
		User new_user = new User();
		new_user.setEmail("Test@email.com");
		new_user.setUnit("2");
		db.save(new_user);
		ls.ChangeUnit("Test@email.com", "3");
		if (new_user.getUnit().equals("3")) {
			isRight = true;
		} else {
			isRight = false;
		}
		if (new_user.getUnit().equals("2")) {
			isWrong = true;
		} else {
			isWrong = false;
		}
		assertEquals(true, isRight);
		assertEquals(false, isWrong);
	}
}