package com.emotionbank.business.global.properties;

import javax.validation.constraints.NotEmpty;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
@ConfigurationProperties(prefix = "jwt")
@Getter
@Setter
public class JwtProperties {

	@NotEmpty
	private String secretKey;

	@NotEmpty
	private Long accessTokenExpirationTime;

	@NotEmpty
	private Long refreshTokenExpirationTime;

	@NotEmpty
	private String accessTokenHeader;

	@NotEmpty
	private String refreshTokenHeader;

}
