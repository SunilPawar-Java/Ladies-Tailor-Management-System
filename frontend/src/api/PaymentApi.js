import api from "./api.js";

export const verifyPayment = (verificationData) => {
  return api.post("/payment/verify", verificationData,{
      headers:{
          Authorization: `Bearer ${localStorage.getItem("JToken")}`
      }
  })
}

export const paymentFailure = (failureData) =>{
    return api.post("/payment/failure", failureData,{
        headers:{Authorization: `Bearer ${localStorage.getItem("JToken")}`}
    })
}