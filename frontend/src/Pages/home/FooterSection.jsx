import WhatsappButton from "../../components/buttons/WhatsappButton.jsx";
import InstagramButton from "../../components/buttons/InstagramButton.jsx";
import LocationButton from "../../components/buttons/LocationButton.jsx";
import {useState} from "react";

const FooterSection = () => {
    const [services, setServices] = useState([
        {
            id: 1,
            name: "Bespoke Stitching"
        },{
            id: 2,
            name: "Alterations"
        },{
            id: 3,
            name: "Hemming"
        },{
            id: 4,
            name: "Repairs"
        },{
            id: 5,
            name: "Saree Fall & Pico"
        },{
            id: 6,
            name: "Custom Design"
        },{
            id: 7,
            name: "Upcycling"
        }
    ]);
    const [products, setProducts] = useState([
        {
            id: 1,
            name: "Blouse"
        },
        {
            id: 2,
            name: "Kurthi"
        },{
            id: 3,
            name: "Pants"
        }
    ]);


    return (<>
        <div className='w-screen flex flex-col gap-5 bg-gray-300 text-blue-950 py-5  md:px-15 lg:px-15'>
            <div className='h-full w-full flex justify-between  '>
                <div className='w-1/3 flex flex-col items-center justify-center gap-5'>
                    <h1 className='font-bold hover:cursor-pointer'>LTR</h1>
                </div>
                <div className='w-1/3 flex flex-col items-center gap-5'>
                    <h1 className='font-bold hover:cursor-pointer'>Services</h1>
                    <div>
                        {
                            services.map((service,idx)=><p className='text-sm hover:underline hover:cursor-pointer p-0.5' key={idx}>{service.name}</p>)
                        }
                    </div>

                </div>
                <div className='w-1/3 flex flex-col items-center gap-5'>
                    <h1 className='font-bold hover:cursor-pointer'>Products</h1>
                    <div>
                        {
                            products.map((product,idx)=><p className='text-sm hover:underline hover:cursor-pointer p-0.5' key={idx}>{product.name}</p>)
                        }
                    </div>
                </div>
            </div>
            <div className='flex flex-col items-center justify-between sm:flex-row md:flex-row gap-4 px-5'>
                <h1 className='font-bold hover:cursor-pointer'>Contact</h1>
                <WhatsappButton/>
                <InstagramButton/>
                <LocationButton/>
            </div>
            <p className='flex pt-5 items-center justify-center text-sm border-t border-zinc-400'>Copyright @ 2026 LTR  -  Trademark Policy</p>
        </div>
    </>)
}
export default FooterSection