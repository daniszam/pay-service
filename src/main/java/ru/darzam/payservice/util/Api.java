package ru.darzam.payservice.util;

/**
 * @author zamaliev
 */
public interface Api {

  String API_V1 = "/api/v1";

  interface Pay {
    String PAY_CONTROLLER = API_V1 + "/pay";
    String ADD_MONEY = PAY_CONTROLLER + "/add";
    String GET_MONEY = PAY_CONTROLLER + "/get";
    String TRANSFER_MONEY = PAY_CONTROLLER + "/transfer";
  }
}
