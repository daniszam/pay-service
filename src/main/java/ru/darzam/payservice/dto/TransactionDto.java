package ru.darzam.payservice.dto;

import java.util.UUID;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import ru.darzam.payservice.util.AccountConstant;

/**
 * @author zamaliev
 */
@Data
public class TransactionDto {

  @ApiModelProperty(example = "8682034e-bbb2-11ea-b3de-0242ac130004")
  private UUID id;
  @NotNull
  @Min(value = AccountConstant.MIN_SUM, message = "the sum cannot be less than zero")
  private Long sum;
}
