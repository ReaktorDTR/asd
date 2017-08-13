package com.asd.config;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.realm.text.TextConfigurationRealm;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.apache.shiro.subject.Subject;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.ModelAttribute;

@Configuration
public class ShiroConfig {

	@Bean
	public Realm realm() {
		TextConfigurationRealm realm = new TextConfigurationRealm();
		realm.setUserDefinitions("admin=root,admin\n" +
				"user=password,user");

		realm.setRoleDefinitions("admin=data,reports\n" +
				"user=data");
		realm.setCachingEnabled(true);
		return realm;
	}

	@Bean
	public ShiroFilterChainDefinition shiroFilterChainDefinition() {
		return new DefaultShiroFilterChainDefinition();
	}

	@ModelAttribute(name = "subject")
	public Subject subject() {
		return SecurityUtils.getSubject();
	}

}
