package com.capgemini.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;

import junit.framework.Assert;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

import org.junit.Test;

import com.capgemini.config.Constants;
import com.capgemini.domain.PersistentToken;
import com.capgemini.domain.User;
import com.capgemini.security.AuthoritiesConstants;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import java.time.ZonedDateTime;

public class PersistentTokenTest {
	
	   private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("d MMMM yyyy");
	   
       @Test
       public void setSeries() {
              PersistentToken pt= new PersistentToken();
              pt.setSeries("adm");
              assertTrue(pt.getSeries() == "adm");
       }
       
       @Test
       public void setTokenValue() {
              PersistentToken pt= new PersistentToken();
              pt.setTokenValue("agh");
              assertTrue(pt.getTokenValue() == "agh");
       }
       
       @Test
       public void setTokenDate() {
              PersistentToken pt= new PersistentToken();
                LocalDate dateTime = LocalDate.parse("2007-12-03");
              pt.setTokenDate(dateTime);
              assertTrue(pt.getTokenDate() == dateTime);
       }
       
       @Test
       public void setUserAgent() {
              PersistentToken pt= new PersistentToken();
              pt.setUserAgent("agh3565");
              assertTrue(pt.getUserAgent() == "agh3565");
       }
       
       @Test
       public void setIpAddress() {
              PersistentToken pt= new PersistentToken();
              pt.setIpAddress("agh3hf565");
              assertTrue(pt.getIpAddress() == "agh3hf565");
       }
       
       @Test
       public void setUser() {
              User user=new User();
              PersistentToken pt= new PersistentToken();
              pt.setUser(user);
              assertTrue(pt.getUser() == user);
       }
       
       @Test
       public void testEquals() {
              
              PersistentToken persistenttoken=new PersistentToken();
              assertTrue(persistenttoken.equals(persistenttoken)==true);
              assertTrue(persistenttoken.equals(null)==false||persistenttoken.equals(persistenttoken.getClass())==true);
           
       }
       @Test
       public void testToString()
       {
              PersistentToken persistenttoken=new PersistentToken();
              String expected=persistenttoken.toString();
              Assert.assertEquals("PersistentToken{series=\'null\', tokenValue=\'null\', tokenDate=null, ipAddress=\'null\', userAgent=\'null\'}",persistenttoken.toString());
                           
       }
	   
       @Test
	   public void testGetFormattedTokenDate() {
   		
			PersistentToken pt= new PersistentToken();
			LocalDate dateTime = LocalDate.parse("2007-12-03");
			pt.setTokenDate(dateTime);
			String expected=DATE_TIME_FORMATTER.format(dateTime);
			assertTrue(expected.equals(pt.getFormattedTokenDate()));   		
	   } 

	   @Test
       public void testSeriesSuccessful(){
              PersistentToken pt1=new PersistentToken();
              pt1.setSeries("arpi");
              String series="arpi";
              assertTrue(pt1.getSeries().equals(series));
       }

       @Test
       public void testSeriesFailure(){
              PersistentToken pt1=new PersistentToken();
              pt1.setSeries("arpi");
              String series="arp";
              assertFalse(pt1.getSeries().equals(series));
       }
       @Test
       public void should_return_true_when_equals_called_given_same_instances() throws Exception {
              PersistentToken pt1=new PersistentToken();
              assertThat(pt1.equals(pt1)).isTrue();
       }

       @Test
       public void should_return_false_when_equals_called_given_null() throws Exception {
              PersistentToken pt1=new PersistentToken();
              assertThat(pt1.equals(null)).isFalse();
       }
}

