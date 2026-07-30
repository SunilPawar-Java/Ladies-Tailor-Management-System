import React, {useEffect, useState} from 'react'
import {getAllProducts} from "../../../../api/ProductApi.js";
import {useNavigate} from "react-router-dom";
import {ArrowDown, ArrowUp, ArrowUpDown, Funnel, SortAsc, SortAscIcon} from "lucide-react";

const ProductsDash = () => {
    const navigate = useNavigate()
    const [productsList, setProductsList] = useState([]);
    const [actionOpen, setActionOpen] = useState(null);
    const loadAddProducts = async () => {
        const response = await getAllProducts();
        setProductsList(response.data);
    }
    useEffect(() => {
        loadAddProducts();
    }, []);
    const [sortBy, setSortBy] = useState("");
    const [isSortOpen, setIsSortOpen] = useState(false);
    const sortedProductList = [...productsList].sort((a, b) => {
        switch (sortBy) {
            case "priceASC":
                return a.price - b.price;
            case "priceDESC":
                return b.price - a.price;
            case "stockASC":
                return a.stock - b.stock;
            case "stockDESC":
                return b.stock - a.stock;
            case "discASC":
                return a.discountPercentage - b.discountPercentage;
            case "discDESC":
                return b.discountPercentage - a.discountPercentage;
            case "idASC":
                return a.id - b.id;
            case "idDESC":
                return b.id - a.id;
            default:
                return 0;
        }
    })
    const editProductDetails = (productId) => {
        navigate("/profile/products/edit", {state: productId})
    }
    const viewProductDetails = (productId) => {
        navigate(`/products/product/${productId}`)
    }
    return (
        <div className='w-screen h-screen flex'>
            <div className='shadow-2xl flex-1 overflow-y-auto p-4 overflow-hidden'>
                <div
                    className='flex w-284 items-center justify-between z-1 fixed bg-white/90 shadow-md rounded py-2 px-30'>
                    <input
                        className='border rounded h-8 px-2'
                        type="text" placeholder="Search"/>
                    <div className='flex gap-5'>
                        <div>
                            <button
                                onClick={() => setIsSortOpen(!isSortOpen)}
                                className='relative flex items-center bg-blue-950 text-white font-bold text-sm py-1 px-4 rounded'>
                                <ArrowUpDown className='h-4 w-5'/>
                                Sort
                            </button>
                            {isSortOpen && (
                                <div className='absolute overflow-hidden flex flex-col p-2 rounded bg-white shadow-lg'>
                                    <button onClick={() => {
                                        setSortBy("priceASC")
                                        setIsSortOpen(false)
                                    }}
                                            className='flex items-center justify-between text-sm text-blue-950 rounded p-1 hover:font-semibold hover:bg-blue-100 active:scale-99'>
                                        Price <ArrowDown className='w-4'/>
                                    </button>
                                    <button onClick={() => {
                                        setSortBy("priceDESC")
                                        setIsSortOpen(false)
                                    }}
                                            className='flex items-center justify-between text-sm text-blue-950 rounded p-1 hover:font-semibold hover:bg-blue-100 active:scale-99'>
                                        Price <ArrowUp className='w-4'/>
                                    </button>
                                    <button onClick={() => {
                                        setSortBy("stockASC")
                                        setIsSortOpen(false)
                                    }}
                                            className='flex items-center justify-between text-sm text-blue-950 rounded p-1 hover:font-semibold hover:bg-blue-100 active:scale-99'>
                                        Stock <ArrowDown className='w-4'/>
                                    </button>
                                    <button onClick={() => {
                                        setSortBy("stockDESC")
                                        setIsSortOpen(false)
                                    }}
                                            className='flex items-center justify-between text-sm text-blue-950 rounded p-1 hover:font-semibold hover:bg-blue-100 active:scale-99'>
                                        Stock <ArrowUp className='w-4'/>
                                    </button>
                                    <button onClick={() => {
                                        setSortBy("discASC")
                                        setIsSortOpen(false)
                                    }}
                                            className='flex items-center justify-between text-sm text-blue-950 rounded p-1 hover:font-semibold hover:bg-blue-100 active:scale-99'>
                                        Discount <ArrowDown className='w-4'/>
                                    </button>
                                    <button onClick={() => {
                                        setSortBy("discDESC")
                                        setIsSortOpen(false)
                                    }}
                                            className='flex items-center justify-between text-sm text-blue-950 rounded p-1 hover:font-semibold hover:bg-blue-100 active:scale-99'>
                                        Discount <ArrowUp className='w-4'/>
                                    </button>
                                    <button onClick={() => {
                                        setSortBy("idASC")
                                        setIsSortOpen(false)
                                    }}
                                            className='flex items-center justify-between text-sm text-blue-950 rounded p-1 hover:font-semibold hover:bg-blue-100 active:scale-99'>
                                        ID <ArrowDown className='w-4'/>
                                    </button>
                                    <button onClick={() => {
                                        setSortBy("idDESC")
                                        setIsSortOpen(false)
                                    }}
                                            className='flex items-center justify-between text-sm text-blue-950 rounded p-1 hover:font-semibold hover:bg-blue-100 active:scale-99'>
                                        ID <ArrowUp className='w-4'/>
                                    </button>
                                </div>
                            )}
                        </div>
                        <button
                            className='flex items-center bg-blue-950 text-white font-bold text-sm py-1 px-4 rounded'>
                            <Funnel className='h-4 w-5'/>
                            Filter
                        </button>
                        <button
                            onClick={() => navigate("/profile/products/add")}
                            className='flex items-center bg-blue-500 text-white font-bold text-sm py-1 px-4 rounded active:scale-99'>
                            Add Product +
                        </button>
                    </div>
                </div>
                <div className='pt-15 flex items-center justify-center'>
                    <table>
                        <thead>
                        <tr className='border-b border-zinc-200 bg-zinc-300'>
                            <th className='p-2 text-sm text-start'>Sr. No</th>
                            <th className='p-2 text-sm text-start'>ID</th>
                            <th className='p-2 text-sm text-start'>IMAGE</th>
                            <th className='p-2 text-sm text-start'>NAME</th>
                            <th className='p-2 text-sm text-start'>TYPE</th>
                            <th className='p-2 text-sm text-start'>Price</th>
                            <th className='p-2 text-sm text-start'>Discount</th>
                            <th className='p-2 text-sm text-start'>Stock</th>
                            <th className='p-2 text-sm text-start'>ACTION</th>
                        </tr>
                        </thead>
                        <tbody className='text-sm'>
                        {sortedProductList.map((product, index) => {
                            return <tr className='border-b border-zinc-200 hover:bg-blue-200 duration-200'
                                       key={index}>
                                <td className=' p-2 text-sm text-zinc-700 font-semibold text-center font-semibold'>{index + 1}</td>
                                <td className=' p-2 text-sm text-zinc-700 font-semibold text-center'>{product.id}</td>
                                <td className=' p-2 text-sm text-zinc-700 font-semibold '>
                                    <div className='flex items-center justify-center'>
                                        <img
                                            className='h-10 w-10 rounded hover:scale-200 duration-500 shadow-2xl'
                                            src={`http://localhost:8081/product/image/${product.id}`}
                                            alt="product image"/>
                                    </div>
                                </td>
                                <td className=' p-2 text-sm text-zinc-700 font-semibold '>{product.itemName}</td>
                                <td className=' p-2 text-sm text-zinc-700 font-semibold '>{product.itemType}</td>
                                <td className=' p-2 text-sm text-zinc-700 font-semibold  text-start'>${product.price}</td>
                                <td className=' p-2 text-sm text-zinc-700 font-semibold  text-center'>{product.discountPercentage}%</td>
                                <td className=' p-2 text-sm text-zinc-700 font-semibold  text-center'>{product.stock}</td>

                                <td className=' relative p-2 text-sm text-zinc-700 font-semibold  text-center'>
                                    <button onClick={() => setActionOpen(actionOpen === product.id ? null : product.id)}
                                            className=' bg-gray-300 px-2 rounded-full font-semibold text-center text-white cursor-pointer active:scale-95'>
                                        ...
                                    </button>
                                    {
                                        actionOpen === product.id && (
                                            <div
                                                className='absolute right-0 mt-1 z-0 rounded px-2 flex justify-between bg-white/90 w-30 shadow-2xl'>
                                                <button
                                                    onClick={() => viewProductDetails(product.id)}
                                                    className='rounded font-semibold text-blue-600 cursor-pointer'>
                                                    View
                                                </button>
                                                <button
                                                    onClick={() => editProductDetails(product.id)}
                                                    className='rounded font-semibold text-yellow-500 cursor-pointer'>
                                                    Edit
                                                </button>
                                                <button
                                                    className='rounded font-semibold text-red-600 cursor-pointer'>
                                                    Delete
                                                </button>
                                            </div>
                                        )
                                    }
                                </td>
                            </tr>
                        })}
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    )
}
export default ProductsDash