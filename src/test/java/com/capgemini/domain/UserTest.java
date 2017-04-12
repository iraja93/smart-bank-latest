package com.capgemini.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

import org.junit.Test;

import com.capgemini.config.Constants;
import com.capgemini.domain.Authority;
import com.capgemini.domain.PersistentToken;
import com.capgemini.domain.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

import junit.framework.Assert;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import java.time.ZonedDateTime;

public class UserTest {

	@Test
	public void setId() {
		User user=new User();
		user.setId((long)8);
		assertTrue(user.getId() == (long)8);
	}

	@Test
	public void setLogin() {
		User user=new User();
		user.setLogin("admin");
		assertTrue(user.getLogin() == "admin");
	}

	@Test
	public void setPassword() {
		User user=new User();
		user.setPassword("admin");
		assertTrue(user.getPassword() == "admin");
	}

	@Test
	public void setFirstName() {
		User user=new User();
		user.setFirstName("Administrator");
		assertTrue(user.getFirstName() == "Administrator");
	}

	@Test
	public void setLastName() {
		User user=new User();
		user.setLastName("Administrator");
		assertTrue(user.getLastName() == "Administrator");
	}

	@Test
	public void setEmail() {
		User user=new User();
		user.setEmail("admin@localhost");
		assertTrue(user.getEmail() =="admin@localhost");
	}

	@Test
	public void setActivated() {
		User user=new User();
		user.setActivated(true);
		assertTrue(user.getActivated() == true);
	}

	@Test
	public void setActivationKey() {
		User user=new User();
		user.setActivationKey("abcd");
		assertTrue(user.getActivationKey() == "abcd");
	}

	@Test
	public void setResetKey() {
		User user=new User();
		user.setResetKey("asdg");
		assertTrue(user.getResetKey() == "asdg");
	}

	@Test
	public void setResetDate() {
		User user=new User();
		ZonedDateTime dateTime = ZonedDateTime.parse("2007-12-03T10:15:30+01:00[Europe/Paris]");
		user.setResetDate(dateTime);
		assertTrue(user.getResetDate() == dateTime);
	}

	@Test
	public void setLangKey()
	{
		User user=new User();
		user.setLangKey("en-US");
		assertTrue(user.getLangKey() == "en-US");
	}

	@Test
	public void setAuthorities(){

		Set<Authority> authorities=new HashSet<Authority>();
		authorities.add(null);


	}

	@Test 
	public void setPersistentTokens()
	{
		Set<PersistentToken> persistentTokens=new HashSet<PersistentToken>();
		persistentTokens.add(null);
	}
	@Test
	public void testEquals_Symmetric() {

		User user = new User();
		assertTrue(user.equals(user)==true);
		assertTrue(user.equals(null)==false||user.equals(user.getClass())==true);

	}
	@Test
	public void testToString()
	{
		User user = new User();
		String expected = user.toString(); // put the expected value here
		Assert.assertEquals("User{login=\'null\', firstName=\'null\', lastName=\'null\', email=\'null\', activated=\'false\', langKey=\'null\', activationKey=\'null\'}",user.toString());



	}

	@Test
	public void testLoginSuccessful(){
		User u1=new User();
		u1.setLogin("arpi");
		User u2=new User();
		u2.setLogin("arpi");
		assertTrue(u1.getLogin().equals(u2.getLogin())==true);
	}

	@Test
	public void testLoginFailure(){
		User u1=new User();
		u1.setLogin("arpi");
		User u2=new User();
		u2.setLogin("ta");
		assertFalse(u1.getLogin().equals(u2.getLogin())==true);
	}
	@Test
	public void should_return_true_when_equals_called_given_same_instances() throws Exception {
		User user = new User();
		assertThat(user.equals(user)).isTrue();
	}

	@Test
	public void should_return_false_when_equals_called_given_null() throws Exception {
		User user = new User();
		assertThat(user.equals(null)).isFalse();
	}
}
