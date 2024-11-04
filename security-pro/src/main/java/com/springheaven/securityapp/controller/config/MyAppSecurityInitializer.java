package com.springheaven.securityapp.controller.config;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
//this class will initialize and protect your resource by enable the security but this class will 
// internally look for the bean name called spring security filter chain that will be created by enabling 
//@Enablewebsecurity from spring-security-config dependencies that will create a security filter chain 
//it will be used by the this abstractsecuritywebapplicationinitilizer
public class MyAppSecurityInitializer extends AbstractSecurityWebApplicationInitializer {

}
