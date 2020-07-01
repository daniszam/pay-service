package ru.darzam.payservice.dto;

import java.util.UUID;
import javax.validation.constraints.NotNull;

import lombok.Data;

/**
 * @author zamaliev
 */
@Data
public class AccountDto {

  private UUID id;
  @NotNull
  private Long amount;
}
