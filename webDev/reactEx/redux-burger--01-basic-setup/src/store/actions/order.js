import * as actionTypes from "./actionsTypes";
import axios from "../../axios-orders";
export const purchaseSuccess = (id, orderData) => {
  return {
    type: actionTypes.PURCHASE_SUCCESS,
    orderId: id,
    orderData: orderData
  };
};

export const purchaseFailed = error => {
  return {
    type: actionTypes.PURCHASE_FAILED,
    error: error
  };
};

export const purchaseBurgerStart = orderData => {
  return dispatch => {
    axios
      .post("/orders.json", orderData)
      .then(response => {
        dispatch(purchaseSuccess(response.data, orderData));
      })
      .catch(error => {
        dispatch(purchaseFailed(error));
      });
  };
};
