import React, {useEffect, useState} from 'react'
import {Outlet, useNavigate, useParams} from "react-router-dom";
import {getProductById} from "../../api/ProductApi.js";
import axios from "axios";

const ProductDetails = () => {
    const navigate = useNavigate()
    const {id} = useParams();
    const [productDetails, setProductDetails] = useState([]);
    async function getProductDetails(){
        const response = await getProductById(id)
        setProductDetails(response.data)
        console.log(response.data)
    }
    useEffect( () => {
            getProductDetails()
    }, [id]);

    const payment = async (e) => {
      const response = await axios.post(`http://localhost:8081/payment/create-order/${500}`);
      console.log(response.data);

      const options = {
          key: "rzp_test_TBmIHI9QV6wr3d",
          amount: response.data.amount,
          name: "LTR",
          currency: response.data.currency,
          order_id: response.data.id,
          handler: function (response){
              alert(
                  "Payment id is:\n\t\t" + response.razorpay_payment_id +
                  "\n\norder id is:\n\t\t"  + response.razorpay_order_id +
                  "\n\nSignature:\n\t\t" + response.razorpay_signature
              );
          },
         theme:{
              "color": "#7c3aed"
         }
      };
        var rpay = window.Razorpay(options);
        rpay.open();
        e.preventDefault();
    }
    return (
        <div className=' pt-25 px-10 grid grid-cols-7'>
            <div className='col-span-2 flex flex-col justify-start py-5'>
                <p className='text-zinc-800'><span className='font-bold text-black text-lg'>{productDetails.itemName}</span> </p>
                <p className='text-zinc-800'><span className='font-bold text-black text-lg'>item Type: </span>{productDetails.itemType}</p>
                <p className='text-zinc-800'><span className='font-bold text-black text-lg'>color: </span>{productDetails.color}</p>
                <p className='text-zinc-800'><span className='font-bold text-black text-lg'>fabric: </span>{productDetails.fabric}</p>
                <p className='text-zinc-800'><span className='font-bold text-black text-lg'>sleeve Length: </span>{productDetails.sleeveLength}</p>
                <p className='text-zinc-800'><span className='font-bold text-black text-lg'>neck line: </span>{productDetails.neckline}</p>
                <p className='text-zinc-800'><span className='font-bold text-black text-lg'>sleeve Type: </span>{productDetails.sleeveType}</p>
                <p className='text-zinc-800'><span className='font-bold text-black text-lg'>length Type: </span>{productDetails.lengthType}</p>
                <p className='text-zinc-800'><span className='font-bold text-black text-lg'>fit Type: </span>{productDetails.fitType}</p>
                <p className='text-zinc-800'><span className='font-bold text-black text-lg'>hem line: </span>{productDetails.hemline}</p>
                <p className='text-zinc-800'><span className='font-bold text-black text-lg'>occasion: </span>{productDetails.occasion}</p>
                <p className='text-zinc-800'><span className='font-bold text-black text-lg'>season: </span>{productDetails.season}</p>
                <p className='text-zinc-800'><span className='font-bold text-black text-lg'>sub Category: </span>{productDetails.subCategory}</p>
                <p className='text-zinc-800'><span className='font-bold text-black text-lg'>main Category: </span>{productDetails.mainCategory}</p>
                <p className='text-zinc-800'><span className='font-bold text-black text-lg'>description: </span>{productDetails.description}</p>
            </div>
            <div className='col-span-3 h-150 flex justify-center overflow-hidden '>
                <img src={`http://localhost:8081/product/image/${productDetails.id}`} alt="product image"
                     className='h-auto w-auto  hover:scale-105 duration-500'
                />
            </div>
            <div className='col-span-2 flex flex-col justify-center items-center overflow-hidden py-5'>
                <div className=''>
                    <p className='text-zinc-800'><span
                        className='font-bold text-black text-lg'>stock: </span>{productDetails.stock}</p>
                    <p className='text-zinc-800'><span
                        className='font-bold text-black text-lg'>discount: </span>{productDetails.discountPercentage}
                    </p>
                    <p className='text-zinc-800'><span
                        className='font-bold text-black text-lg'>price: </span>{productDetails.price}</p>
                </div>
                <div>
                    <p className='font-bold'>Size</p>
                </div>
                <div className='flex gap-5'>
                    <button className='text-md text-white font-bold bg-blue-950 rounded py-1 px-5 hover:scale-105 active:scale-100 duration-500'>Wishlist</button>
                    <button
                        //onClick={payment}
                        onClick={() => navigate(`/products/product/${id}/order/${id}`)}
                        className='text-md text-white font-bold bg-blue-950 rounded py-1 px-5 hover:scale-105 active:scale-100 duration-500'>
                        Order Now
                    </button>
                </div>
            </div>
            <Outlet/>
        </div>
    )
}
export default ProductDetails
