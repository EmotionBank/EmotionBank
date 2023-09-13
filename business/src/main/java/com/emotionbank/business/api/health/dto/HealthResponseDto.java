package com.emotionbank.business.api.health.dto;

import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class HealthResponseDto {
	private final String health;
	private final List<String> activeProfiles;
	private final String localServerPort;
	private final String serverPort;

	public static HealthResponseDto of(String health, List<String> activeProfiles, String localServerPort,
		String serverPort) {
		return HealthResponseDto.builder()
			.health(health)
			.activeProfiles(activeProfiles)
			.localServerPort(localServerPort)
			.serverPort(serverPort)
			.build();
	}
}
