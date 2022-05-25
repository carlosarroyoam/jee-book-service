package com.carlosarroyoam.library.dto;

import java.time.ZonedDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class APIErrorDto {
	private String message;
	private String error;
	private int status;
	private ZonedDateTime timestamp;
}
