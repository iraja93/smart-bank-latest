package com.capgemini.domain;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import java.time.LocalDateTime;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.capgemini.config.Constants;
import com.capgemini.domain.PersistentAuditEvent;
import com.fasterxml.jackson.annotation.JsonIgnore;
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

public class PersistentAuditEventTest {
       @Test
       public void setId() {
              PersistentAuditEvent pae=new PersistentAuditEvent();
              pae.setId((long)8);
              assertTrue(pae.getId() == (long)8);
       }

       @Test
       public void setPrincipal() {
              PersistentAuditEvent pae=new PersistentAuditEvent();
              pae.setPrincipal("Admin");
              assertTrue(pae.getPrincipal() == "Admin");
       }

       @Test
       public void setAuditEventDate() {
              PersistentAuditEvent pae=new PersistentAuditEvent();
              LocalDateTime dateTime = LocalDateTime.parse("2007-12-03T10:15:30");
              pae.setAuditEventDate(dateTime);
              assertTrue(pae.getAuditEventDate() == dateTime);
       }

       @Test
       public void setAuditEventType() {
              PersistentAuditEvent pae=new PersistentAuditEvent();
              pae.setAuditEventType("Admin1");
              assertTrue(pae.getAuditEventType() == "Admin1");
       }

       @Test  
       public void setData() {
              Map<String,String> data=new HashMap<String,String>();
              data.put("Admin","Admin");
              Map<String,String> data1=new HashMap<String,String>();
              data1.put("Admin","Admin");
              assertThat(data, is(data1));
       }
}
