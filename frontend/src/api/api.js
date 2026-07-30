import axios from "axios";

const api = axios.create({
    baseURL: "http://localhost:8081",
    headers:{"Content-Type": "application/json"}
});

export const api2 = axios.create({
    baseURL: "http://localhost:8081",
    headers:{"Content-Type": "multipart/form-data"}
});

export default api;