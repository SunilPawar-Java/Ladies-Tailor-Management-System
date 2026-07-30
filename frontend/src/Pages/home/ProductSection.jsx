import React, {useState} from 'react'
import {Link} from 'react-router-dom'
const ProductSection = () => {
    const [imageHoverId, setImageHoverId] = useState(null);
    const [products] = useState([
        {
            id:1,
            name:"Blouses",
            src:"/src/assets/images/Blouse.png"
        }, {
            id:2,
            name:"Kurtis",
            src:"/src/assets/images/Kurti.png"
        }, {
            id:3,
            name:"Pants",
            src:"/src/assets/images/Plazzo.png"
        }, {
        id:4,
            name: "Top",
            src: "/src/assets/images/Top.png",
        },{
            id: 5,
            name: "Full Outfit",
            src: "/src/assets/images/kurti 1.png"
        }
    ]);
    return (<>
        <div className='w-screen px-5 py-5 md:px-15 lg:px-15 flex flex-col gap-10 bg-white'>
            <h5 className='font-extrabold text-xl sm:text-2xl md:text-2xl'>Our Products</h5>
            <div className='flex flex-nowrap px-5 py-10 rounded-t-2xl overflow-x-auto scrollbar-hide shadow-xl gap-5 md:gap-5 lg:gap-10 bg-blue-950'>
                {products.map((product) => {
                    return (
                        <div
                            key={product.id}
                            onMouseEnter={() => setImageHoverId(product.id)}
                            onMouseLeave={() => setImageHoverId(null)}
                            className=" relative h-100 w-60 md:h-100 overflow-hidden rounded-lg hover:cursor-pointer hover:scale-110
                                duration-500 flex-shrink-0 shadow-xl
                            ">
                            <img
                                className="h-full w-full object-cover rounded-lg"
                                src={product.src}
                                alt={product.name}
                            />
                            {imageHoverId === product.id && (<div>
                                <div
                                    className="absolute inset-x-0 bottom-0 h-40 bg-gradient-to-t from-black/70"></div>
                                <div
                                    className="absolute inset-x-0 bottom-0 text-white flex flex-col justify-between p-5 ">
                                    <p className="font-bold">{product.name}</p>
                                    <Link
                                        className="underline"
                                        to="/products"> see more </Link>
                                </div>
                            </div>)}
                        </div>
                    );
                })}
            </div>
        </div>
    </>)
}
export default ProductSection