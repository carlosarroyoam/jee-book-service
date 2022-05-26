package com.carlosarroyoam.library.dto;

import java.time.ZonedDateTime;

import javax.json.bind.annotation.JsonbPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonbPropertyOrder({ "message", "error", "status", "timestamp" })
public class APIErrorDto {
	private String message;
	private String error;
	private int status;
	private ZonedDateTime timestamp;
}