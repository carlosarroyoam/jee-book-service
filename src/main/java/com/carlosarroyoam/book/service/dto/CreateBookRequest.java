package com.carlosarroyoam.book.service.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateBookRequest {
  @NotBlank(message = "Isbn should not be blank")
  @Size(min = 10, max = 17, message = "Isbn should be between 10 and 17")
  private String isbn;

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
