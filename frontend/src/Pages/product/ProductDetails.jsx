import React, {useEffect, useState} from 'react'
import {Outlet, useNavigate, useParams} from "react-router-dom";
import {getProductById} from "../../api/ProductApi.js";
import {useAuth} from "../../context/AuthContext.jsx";

const ProductDetails = () => {
    const navigate = useNavigate()
    const {id} = useParams();
    const {isLoggedIn} = useAuth()
    const [productDetails, setProductDetails] = useState([]);

    async function getProductDetails() {
        const response = await getProductById(id)
        setProductDetails(response.data)
    }
    useEffect(() => {
        getProductDetails()
    }, [id]);
    const navToOrderForm = () => {
        if (isLoggedIn){
            navigate(`/products/product/${id}/order/${id}`)
        }else{
            navigate("/login")
        }
    }
    return (
        <div className={`pt-20 lg:pt-25 sm:pt-30 w-screen sm:px-10 md:px-15 grid grid-cols-2 gap-10`}>
            <div className='order-1 col-span-2 sm:col-span-1 sm:order-0 flex justify-between flex-col gap-2'>
                <div className=' pl-5'>
                    <div className=''>
                        <p className='font-bold font-serif text-lg lg:text-2xl text-blue-950'>
                            {productDetails.itemType}
                        </p>
                        <p className='col-span-2 text-md font-bold text-zinc-700'>
                            <span className='text-md'>{productDetails.itemName}</span>
                        </p>
                        <p>Rating 5 stars - developing</p>
                    </div>
                    <div className=''>
                        <p className='text-green-600 flex gap-5 lg:text-lg p-1 hover:-translate-y-1 duration-500'>
                            ${productDetails.price - (productDetails.price * productDetails.discountPercentage / 100)}
                            <span className='line-through text-zinc-400'>${productDetails.price}</span>
                            <span className=' text-red-500 font-normal text-md'>{productDetails.discountPercentage}% off</span>
                        </p>
                        <p className='flex gap-2 text-black font-semibold lg:text-md p-1 '>
                            <span className='text-sm text-zinc-700 font-bold border border-gray-200 rounded cursor-pointer py-1 px-2 hover:-translate-y-1 duration-300'>S</span>
                            <span className='text-sm text-zinc-700 font-bold border border-gray-200 rounded cursor-pointer py-1 px-2 hover:-translate-y-1 duration-300'>X</span>
                            <span className='text-sm text-zinc-700 font-bold border border-gray-200 rounded cursor-pointer py-1 px-2 hover:-translate-y-1 duration-300'>L</span>
                            <span className='text-sm text-zinc-700 font-bold border border-gray-200 rounded cursor-pointer py-1 px-2 hover:-translate-y-1 duration-300'>XL</span>
                            <span className='text-sm text-zinc-700 font-bold border border-gray-200 rounded cursor-pointer py-1 px-2 hover:-translate-y-1 duration-300'>XXL</span>
                        </p>
                        <p className='text-red-500 lg:text-md font-bold p-1 hover:-translate-y-1 duration-500'>
                            {productDetails.stock} left
                        </p>
                        <p className='text-zinc-700 mt-10 font-semibold text-sm'>
                            <span className='font-bold text-md text-black'>Colour - </span>
                            {productDetails.color}
                        </p>
                        <p className='text-zinc-700 font-semibold text-sm'><span
                            className='font-bold text-md text-black'>Material & Fabric - </span>{productDetails.fabric}</p>
                        <p className='text-zinc-700 font-semibold text-sm list-item ml-5'>
                            {productDetails.description}
                        </p>
                    </div>
                </div>
                <div className='flex w-screen sm:w-full justify-center text-nowrap gap-1 sm:gap-5 lg:pt-15'>
                    <button className='text-md text-white font-bold bg-pink-700 rounded p-1 sm:px-5 hover:scale-105 active:scale-100 duration-500'>
                        Add To Wishlist
                    </button>
                    <button onClick={navToOrderForm}
                            className='text-md text-white font-bold bg-blue-700 rounded p-1 sm:px-5 hover:scale-105 active:scale-100 duration-500'>
                            Order Now
                    </button>
                </div>
            </div>
            <div className='order-0 sm:order-1 col-span-2 sm:col-span-1 h-90 md:h-125 flex justify-center bg-zinc-200 overflow-hidden'>
                <img src={`http://localhost:8081/product/image/${productDetails.id}`} alt="product image"
                     className='object-cover hover:scale-110 duration-500 '
                />
            </div>
            <div className='order-2 col-span-2'>
                <p className='font-bold font-serif text-lg lg:text-2xl text-blue-950'>
                    More Details About This Product:-
                </p>
                <div className=''>
                    <p className='text-zinc-700 font-semibold text-sm'>
                        <span className='font-bold text-md text-black'>Fitting - </span>
                        {productDetails.fitType}
                    </p>
                    <p className='text-zinc-700 font-semibold text-sm'>
                        <span className='font-bold text-md text-black'>You can wear on Occasion - </span>
                        {productDetails.occasion}
                    </p>
                    <p className='text-zinc-700 font-semibold text-sm'><span
                        className='font-bold text-md text-black'>You can wear in Season - </span>{productDetails.season}
                    </p>
                </div>
            </div>
            <Outlet/>
        </div>
    )
}
export default ProductDetails
