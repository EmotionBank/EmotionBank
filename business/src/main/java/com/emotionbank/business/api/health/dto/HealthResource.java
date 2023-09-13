package com.emotionbank.business.api.health.dto;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import com.emotionbank.business.api.health.controller.HealthController;

public class HealthResource extends EntityModel<HealthResponseDto> {

	public static EntityModel<HealthResponseDto> from(HealthResponseDto healthResponseDto) {
		EntityModel<HealthResponseDto> healthModel = EntityModel.of(healthResponseDto);

		WebMvcLinkBuilder selfLinkBuilder = linkTo(HealthController.class);

		healthModel.add(selfLinkBuilder.withSelfRel());
		return healthModel;
	}

}
