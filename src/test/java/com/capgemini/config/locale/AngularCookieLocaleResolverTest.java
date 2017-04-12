package com.capgemini.config.locale;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import com.capgemini.config.locale.AngularCookieLocaleResolver;

public class AngularCookieLocaleResolverTest{

	@Test
	public void testresolveLocale() {
		AngularCookieLocaleResolver angularCookieLocaleResolver=new AngularCookieLocaleResolver();
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.addParameter("parameterName", "someValue");
		angularCookieLocaleResolver. resolveLocaleContext(request);
	}

	@Test
	public void testaddCookie() {
		AngularCookieLocaleResolver angularCookieLocaleResolver=new AngularCookieLocaleResolver();
		MockHttpServletResponse response = new MockHttpServletResponse();
		response.addHeader("parameterName", "someValue");
		angularCookieLocaleResolver. addCookie(response,"cookieValue");
	}

}
