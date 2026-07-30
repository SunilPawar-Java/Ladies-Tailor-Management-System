import React, {useState} from 'react'

const WhyChooseUs = () => {
    const [chooseUs, setChooseUs] = useState([
        {
            id: 1,
            heading: "Perfect Fit",
            body: "Every garment is tailored according to precise measurements to ensure maximum comfort and confidence."
        },{
            id: 2,
            heading: "Quality Craftsmanship",
            body: "We focus on fine stitching, premium finishing, and attention to every detail."
        },{
            id: 3,
            heading: "Timely Delivery",
            body: "We respect your time and work hard to deliver every order as promised."
        },{
            id: 4,
            heading: "Customer Satisfaction",
            body: "Your satisfaction is our priority, and we continuously strive to exceed expectations."
        },{
            id: 5,
            heading: "Affordable Pricing",
            body: "Professional tailoring services at fair and transparent prices."
        }
    ]);
    return (<>
        <div className='px-2 sm:px-5 md:px-10'>
            <p className='text-lg lg:text-2xl font-extrabold sm:text-xl sm:text-2xl md:text-2xl'>Why Choose Us</p>
            <div className='px-2 sm:px-5'>
                {
                    chooseUs.map((choose, idx) =>{
                        return <div key={choose.id} className='flex flex-col gap-2 py-3'>
                            <p className='font-bold'>{choose.heading}</p>
                            <p className='text-base font-light'>{choose.body}</p>
                        </div>
                    })
                }
            </div>
        </div>
        </>)
}
export default WhyChooseUs
