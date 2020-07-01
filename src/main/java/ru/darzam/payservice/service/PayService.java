package ru.darzam.payservice.service;


import ru.darzam.payservice.dto.AccountDto;
import ru.darzam.payservice.dto.TransactionDto;
import ru.darzam.payservice.dto.TransferTransactionDto;

/**
 * @author zamaliev
 */
public interface PayService {

  AccountDto put(TransactionDto request);

  AccountDto get(TransactionDto request);

  AccountDto transfer(TransferTransactionDto request);
}
