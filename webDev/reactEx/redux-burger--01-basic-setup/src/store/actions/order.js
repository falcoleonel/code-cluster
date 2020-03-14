import * as actionsTypes from "./actionsTypes";
import axios from "../../axios-orders";
export const purchaseSuccess = (id, orderData) => {
  return {
    type: actionsTypes.PURCHASE_SUCCESS,
    orderId: id,
    orderData: orderData
  };
};

export const purchaseFailed = error => {
  return {
    type: actionsTypes.PURCHASE_FAILED,
    error: error
  };
};
export const purchaseBurgerStart = payload => {
  return { type: actionsTypes.PURCHASE_START, payload };
};

export const purchaseBurger = orderData => {
  return dispatch => {
    dispatch(purchaseBurgerStart());
    axios
      .post("/orders.json", orderData)
      .then(response => {
        dispatch(purchaseSuccess(response.data.name, orderData));
      })
      .catch(error => {
        dispatch(purchaseFailed(error));
      });
  };
};

export const purchaseInit = () => {
  return {
    type: actionsTypes.PURCHASE_INIT,
    purchaseInit
  };
};
