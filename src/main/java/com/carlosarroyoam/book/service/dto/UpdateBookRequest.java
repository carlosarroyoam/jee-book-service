package com.carlosarroyoam.book.service.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class UpdateBookRequest {

	@NotBlank(message = "Title should not be blank")
	@Size(min = 3, max = 128, message = "Title should be between 3 and 128")
	private String title;

	@NotNull(message = "Price should not be null")
	private BigDecimal price;

	@NotNull(message = "IsAvailableOnline should not be null")
	private Boolean isAvailableOnline;

	@NotNull(message = "PublishedAt should not be null")
	private LocalDate publishedAt;

}
