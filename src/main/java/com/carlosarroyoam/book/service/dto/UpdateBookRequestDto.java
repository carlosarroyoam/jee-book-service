package com.carlosarroyoam.book.service.dto;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/** Representa los datos enviados para actualizar un libro existente. */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateBookRequestDto {
  @NotBlank(message = "Title should not be blank")
  @Size(min = 3, max = 128, message = "Title should be between 3 and 128")
  private String title;

  @NotNull(message = "Price should not be null")
  @Digits(
      integer = 5,
      fraction = 2,
      message = "Price should have max 5 integral digits and max 2 fractional digits")
  private BigDecimal price;

  @NotNull(message = "IsAvailableOnline should not be null")
  private Boolean isAvailableOnline;

  @NotNull(message = "PublishedAt should not be null")
  @PastOrPresent(message = "PublishedAt should be a date in past or present")
  private LocalDate publishedAt;
}
