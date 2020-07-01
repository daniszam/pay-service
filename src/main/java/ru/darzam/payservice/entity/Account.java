package ru.darzam.payservice.entity;

import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import ru.darzam.payservice.util.AccountConstant;

/**
 * @author zamaliev
 */
@Builder
@Entity
@Table(name = "account")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Account {

  @Id
  @GeneratedValue(generator = "UUID")
  @GenericGenerator(
      name = "UUID",
      strategy = "org.hibernate.id.UUIDGenerator"
  )
  private UUID id;

  @Min(value = AccountConstant.MIN_SUM)
  private Long amount;
}
