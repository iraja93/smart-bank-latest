package com.capgemini.security;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.ArrayList;
import java.util.Collection;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.capgemini.security.AjaxAuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.*;

import static org.assertj.core.api.Assertions.assertThat;

public class AjaxAuthenticationFailureHandlerTest {
       
       @Test
       public void AjaxAuthenticationFailureHandler() throws Exception {
              AjaxAuthenticationFailureHandler ajaxAuthenticationFailureHandler=new AjaxAuthenticationFailureHandler();
              MockHttpServletRequest request = new MockHttpServletRequest();
              HttpServletResponse response = null;
              AuthenticationException exception = null;
              try {
                     ajaxAuthenticationFailureHandler.onAuthenticationFailure(request,response,exception);
              }
              catch(Exception e){
					 System.out.println(e.getMessage());
              }            
       }    
}

