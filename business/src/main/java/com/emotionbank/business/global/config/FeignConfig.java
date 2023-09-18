package com.emotionbank.business.global.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClientsConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import feign.Logger;

@Configuration
@EnableFeignClients
@Import(FeignClientsConfiguration.class)
public class FeignConfig {

	@Bean
	Logger.Level feignLoggerLevel() {
		return Logger.Level.FULL;
	}

}
