package com.capgemini.security;

import static org.junit.Assert.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.core.AuthenticationException;

import com.capgemini.security.AjaxLogoutSuccessHandler;

public class AjaxAuthenticationLogoutSuccessfulHandlerTest {

                @Test
                public void testonLogoutSuccess(){
                                AjaxLogoutSuccessHandler ajaxLogoutSuccessHandler=new  AjaxLogoutSuccessHandler();
                                HttpServletRequest request=null;
                                HttpServletResponse response = null;
                                Authentication authentication = null;
                                try{
                                                ajaxLogoutSuccessHandler.onLogoutSuccess(request,response,authentication);
                                }
                                catch(Exception e)
                                {
                                                
                                 }
                }
}
