package com.carlosarroyoam.book.service.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateBookRequestDto {
  @NotBlank(message = "Title should not be blank")
  @Size(min = 3, max = 128, message = "Title should be between 3 and 128")
  private String title;

  @NotNull(message = "Price should not be null")
  @Digits(integer = 5, fraction = 2, message = "Price should have max 5 integral digits and max 2 fractional digits")
  private BigDecimal price;

  @NotNull(message = "IsAvailableOnline should not be null")
  private Boolean isAvailableOnline;

  @NotNull(message = "PublishedAt should not be null")
  @PastOrPresent(message = "PublishedAt should be a date in past or present")
  private LocalDate publishedAt;
}
