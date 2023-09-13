package com.emotionbank.business.api.health.controller;

import java.util.Arrays;

import org.springframework.core.env.Environment;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emotionbank.business.api.health.dto.HealthResource;
import com.emotionbank.business.api.health.dto.HealthResponseDto;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/health", produces = MediaTypes.HAL_JSON_VALUE)
@RequiredArgsConstructor
public class HealthController {

	private final Environment environment;

	@GetMapping
	public ResponseEntity<EntityModel<HealthResponseDto>> checkHealth() {
		HealthResponseDto responseDto = HealthResponseDto.of(
			"ok",
			Arrays.asList(environment.getActiveProfiles()),
			environment.getProperty("local.server.port"),
			environment.getProperty("server.port")
		);

		return ResponseEntity.ok(HealthResource.from(responseDto));
	}

}
