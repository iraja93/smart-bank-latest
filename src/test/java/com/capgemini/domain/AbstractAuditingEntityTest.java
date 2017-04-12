package com.capgemini.domain;

import static org.junit.Assert.*;
import java.time.ZonedDateTime;
import org.junit.Test;
import com.capgemini.domain.AbstractAuditingEntity;

public class AbstractAuditingEntityTest extends AbstractAuditingEntity  {       
       @Test
       public void testSetCreatedBy() {
              AbstractAuditingEntityTest abstractAuditingEntityTest=new AbstractAuditingEntityTest();
              abstractAuditingEntityTest.setCreatedBy("admin");
              assertEquals("admin",abstractAuditingEntityTest.getCreatedBy());
       }
       @Test
       public void testSetCreatedDate() {              
              AbstractAuditingEntityTest abstractAuditingEntityTest=new AbstractAuditingEntityTest();
              ZonedDateTime dateTime = ZonedDateTime.parse("2007-12-03T10:15:30+01:00[Europe/Paris]");
              abstractAuditingEntityTest.setCreatedDate(dateTime);
              assertEquals(dateTime,abstractAuditingEntityTest.getCreatedDate());
       }
       @Test
       public void testSetLastModifiedBy() {              
              AbstractAuditingEntityTest abstractAuditingEntityTest=new AbstractAuditingEntityTest();
              abstractAuditingEntityTest.setLastModifiedBy("admin");
              assertEquals("admin",abstractAuditingEntityTest.getLastModifiedBy());
       }
       @Test
       public void testSetLastModifiedDate() {
              AbstractAuditingEntityTest abstractAuditingEntityTest=new AbstractAuditingEntityTest();
              ZonedDateTime dateTime = ZonedDateTime.parse("2007-12-03T10:15:30+01:00[Europe/Paris]");
              abstractAuditingEntityTest.setLastModifiedDate(dateTime);
              assertEquals(dateTime,abstractAuditingEntityTest.getLastModifiedDate());
       }
}
