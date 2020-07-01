package ru.darzam.payservice.dto;

import java.util.UUID;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author zamaliev
 */
@Data
public class TransferTransactionDto {

  @ApiModelProperty(example = "8682034e-bbb2-11ea-b3de-0242ac130004")
  @NotNull
  private UUID fromAccountNumber;
  @ApiModelProperty(example = "8682034e-bbb2-11ea-b3de-0242ac130004")
  @NotNull
  private UUID toAccountNumber;
  @NotNull
  private Long sum;
}
