import api from "./api.js";

export const login = (usernamePassword) => {
  return api.post("/auth/login", usernamePassword, {
      withCredentials: true
  })
}

export const logout = () => {
    return api.post("/auth/logout", {},{withCredentials:true})
}