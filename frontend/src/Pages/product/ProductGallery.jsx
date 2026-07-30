import {useEffect, useState} from "react";
import {getAllProducts} from "/src/api/ProductApi.js";
import {Outlet, useNavigate} from "react-router-dom";

const ProductGallery = () => {
    const [products, setProducts] = useState([]);
    const [imageHoverId, setImageHoverId] = useState(null);
    const navigate =   useNavigate();
    const loadProducts = async () => {
        const response = await getAllProducts();
        setProducts(response.data);
    }
    useEffect(() => {
        loadProducts();
    }, []);

    return (<>
        <div className='mt-23 grid  '>
            <div className='p-2 columns-2 sm:columns-3 md:columns-4 border overflow-hidden'>
                {products.map((product) => {
                    return <div key={product.id} className='h-auto w-auto mb-5 relative overflow-hidden rounded hover:scale-105 duration-500'
                                onMouseEnter={() => setImageHoverId(product.id)}
                                onMouseLeave={() => setImageHoverId(null)}>

                        <img src={`http://localhost:8081/product/image/${product.id}`} alt="image"
                             className='object-cover'/>

                        {imageHoverId === product.id && (<div
                                className='absolute inset-0 bg-black/20 flex flex-col  justify-between text-white p-5'>
                                <p className='text-white text-sm'>{product.itemName}</p>
                                <div>
                                    <button className='bg-white text-sm font-semibold text-black py-1 px-5 rounded
                                    hover:scale-105 active:scale-100 duration-500
                                    '
                                    onClick={() => navigate(`/products/product/${product.id}`)}
                                    >
                                        See more details
                                    </button>
                                </div>
                            </div>
                        )}

                    </div>
                })}
            </div>
        </div>
    </>)
}
export default ProductGallery