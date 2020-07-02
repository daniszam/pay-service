package ru.darzam.payservice.controller;

import javax.validation.Valid;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.darzam.payservice.dto.AccountDto;
import ru.darzam.payservice.dto.TransactionDto;
import ru.darzam.payservice.dto.TransferTransactionDto;
import ru.darzam.payservice.service.PayService;
import ru.darzam.payservice.util.Api;

/**
 * @author zamaliev
 */
@RestController(Api.Pay.PAY_CONTROLLER)
@RequiredArgsConstructor
public class PayController {

  private final PayService payService;

  @PutMapping(Api.Pay.GET_MONEY)
  @ApiResponses(value = {
      @ApiResponse(code = 404, message = "Account not found")
  })
  public AccountDto get(@RequestBody @Valid TransactionDto transactionDto) {
    return payService.get(transactionDto);
  }

  @PutMapping(Api.Pay.ADD_MONEY)
  @ApiOperation(value = "To create account send request without 'id' field")
  public AccountDto add(@RequestBody @Valid TransactionDto transactionDto) {
    return payService.put(transactionDto);
  }

  @PutMapping(Api.Pay.TRANSFER_MONEY)
  @ApiResponses(value = {
      @ApiResponse(code = 404, message = "Account not found")
  })
  public AccountDto transfer(@RequestBody @Valid TransferTransactionDto transferTransactionDto) {
    return payService.transfer(transferTransactionDto);
  }
}
