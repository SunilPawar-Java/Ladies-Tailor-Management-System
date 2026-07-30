import api, {api2} from "./api.js";

export const getAllProducts = () => {
    return api.get("/product/all");
}

export const getProductById =  (id) => {
    return api.get(`/product/get/${id}`);
}

export const addProduct = (newProduct) =>{
    const product = new FormData();
    Object.entries(newProduct).forEach(([key, value]) =>{
        if (key === "image"){
            product.append(key, value[0])
        }else {
            product.append(key, value);
        }
    })
    return api2.post("/product", product, {
        headers:{
            Authorization: `Bearer ${localStorage.getItem("JToken")}`
        }
    });
}

export const updateProductPartially = (updatedData) => {
  return api2.patch("/product/update", updatedData, {
      headers:{
          Authorization: `Bearer ${localStorage.getItem("JToken")}`
      }
  });
}