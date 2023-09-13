package com.emotionbank.business.api.health.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.emotionbank.business.common.BaseControllerTest;

public class HealthControllerTest extends BaseControllerTest {
	@Test
	@DisplayName("Health Check API 테스트")
	public void checkHealth() throws Exception {
		// When & Then
		mockMvc.perform(get("/health"))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(jsonPath("health").exists())
			.andExpect(jsonPath("activeProfiles").exists())
			.andExpect(jsonPath("localServerPort").exists())
			.andExpect(jsonPath("serverPort").exists())
			.andExpect(jsonPath("_links.self").exists());
	}
}