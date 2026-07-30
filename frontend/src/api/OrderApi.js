import api from "./api.js";

export const placeOrder = (orderData) => {
    return api.post("/order", orderData, {
        headers: {Authorization: `Bearer ${localStorage.getItem("JToken")}`}
    });
}

export const createPayment = (orderId) => {
    return api.post(`/payment/create-order/${orderId}`, {}, {
        headers: {Authorization: `Bearer ${localStorage.getItem("JToken")}`}
    });
}

export const getAllOrders = () => {
  return api.get("/order/all",{
      headers:{Authorization: `Bearer ${localStorage.getItem("JToken")}`}
  })
}
