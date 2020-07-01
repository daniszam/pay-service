package ru.darzam.payservice.service;

import java.util.UUID;
import javax.persistence.EntityNotFoundException;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.darzam.payservice.dto.AccountDto;
import ru.darzam.payservice.dto.TransactionDto;
import ru.darzam.payservice.dto.TransferTransactionDto;
import ru.darzam.payservice.entity.Account;
import ru.darzam.payservice.mapper.AccountMapper;
import ru.darzam.payservice.repository.AccountRepository;

/**
 * @author zamaliev
 */
@Service
@RequiredArgsConstructor
@Transactional
public class PayServiceImpl implements PayService {

  private final AccountRepository repository;
  private final AccountMapper mapper;

  @Override
  public AccountDto put(TransactionDto request) {
    Account account = increase(request.getId(), request.getSum());

    if (account == null) {
      account = Account.builder()
          .amount(request.getSum())
          .build();
      account = repository.save(account);
    }

    return mapper.toDto(account);
  }

  @Override
  public AccountDto get(TransactionDto request) {
    Account account = get(request.getId(), request.getSum());
    return mapper.toDto(account);
  }

  @Override
  public AccountDto transfer(TransferTransactionDto request) {
    Account from = get(request.getFromAccountNumber(), request.getSum());
    increase(request.getToAccountNumber(), request.getSum());
    return mapper.toDto(from);
  }


  private Account get(UUID id, Long sum) {
    Account account = repository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("account not found"));

    if (account.getAmount() < sum) {
      throw new IllegalArgumentException("not enough money in the account");
    }
    account.setAmount(account.getAmount() - sum);
    return account;
  }

  private Account increase(UUID id, Long sum) {
    Account account = repository.getById(id);

    if (account != null) {
      account.setAmount(account.getAmount() + sum);
    }
    else if (id != null) {
      throw new EntityNotFoundException("account not found");
    }
    return account;
  }
}

