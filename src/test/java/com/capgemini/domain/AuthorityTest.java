package com.capgemini.domain;

import static org.junit.Assert.*;

import org.junit.Test;

import com.capgemini.config.Constants;
import com.capgemini.domain.Authority;
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

public class AuthorityTest {

       @Test
       public void setName() {
              Authority auth=new Authority();
              auth.setName("Administrator");
              assertTrue(auth.getName() == "Administrator");              
       }
       
       @Test
       public void testEquals_Symmetric() {
              Authority authority= new Authority();
              assertTrue(authority.equals(authority)==true);
              assertTrue(authority.equals(null)==false||authority.equals(authority.getClass())==true);
       }
       @Test
       public void testToString() {
              Authority authority= new Authority();
              String expected = authority.toString(); // put the expected value here
              System.out.println(expected);
              Assert.assertEquals("Authority{name=\'null\'}",authority.toString());
       }

       @Test
       public void testhashCode() {
              Authority auth=new Authority();
              int expected=auth.hashCode();
              assertTrue(expected==0);
       }
       @Test
       public void testNameSuccessful() {
           Authority authority= new Authority();
           authority.setName("Admin");
           String name="Admin";
           assertTrue(authority.getName().equals(name));          
       }
       
       @Test
       public void testNameNotNull() {
           Authority authority= new Authority();
           authority.setName("Admin");
           String name="ashok";
           assertFalse(authority.getName().equals(name));
       }
       @Test
       public void testNameIsNull() {
           Authority authority= new Authority();
           authority.setName("Admin");
           String name=null;
           assertFalse(authority.getName().equals(name));
       }
}

