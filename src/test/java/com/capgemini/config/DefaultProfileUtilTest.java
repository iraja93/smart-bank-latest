package com.capgemini.config;

import static org.junit.Assert.*;
import org.junit.Test;
import com.capgemini.config.DefaultProfileUtil;

public class DefaultProfileUtilTest {

       @Test
       public void test() {
              String expected=DefaultProfileUtil.getDefaultActiveProfiles().toString();
              assertTrue(expected.equals(DefaultProfileUtil.getDefaultActiveProfiles().toString()));
              
       }
       

}
