import React, {useState} from 'react'

const ServiceSection = () => {
    const [services, ] = useState([
        {
            id:1,
            name: "Bespoke Stitching",
            description: "Making new clothes (blouses, suits, dresses) from fabric."
        },
        {
            id:2,
            name:"Alterations",
            description: "Adjusting existing clothes to fit better (sizing up or down)."
        },
        {
            id:3,
            name:"Hemming",
            description: "Shortening or lengthening the bottom of pants, skirts, or sleeves."
        },
        {
            id:4,
            name:"Repairs",
            description: "Fixing broken zippers, missing buttons, or torn seams."
        },
        {
            id:5,
            name:"Saree Fall & Pico",
            description: "Adding a protective border and finishing the edges of a saree."
        },
        {
            id:6,
            name:"Custom Design",
            description: "Adding unique necklines, sleeves, embroidery, or beadwork."
        },
        {
            id:7,
            name:"Upcycling",
            description: "Converting an old garment (like a saree) into a new style (like a gown)."
        }
    ]);
    return (
        <div className='w-screen px-5 py-5 md:px-15 lg:px-15 flex flex-col gap-10 bg-white'>
            <h1 className='font-extrabold text-xl sm:text-2xl md:text-2xl'>Our Services</h1>
            <div className='grid grid-cols-1 sm:grid-cols-3 md:grid-cols-3 lg:grid-cols-4 justify-center gap-5 md:gap-10'>
            {services.map((service) => {
                    return <div key={service.id}
                    className='p-5 flex flex-col shadow-md hover:cursor-pointer hover:scale-110 rounded
                    active:scale-100  bg-blue-950 duration-500'>
                        <p className='font-bold text-white text-lg'>{service.name}</p>
                        <p className='text-sm text-white ml-2'>{service.description}</p>
                        <button className='font-bold text-white underline cursor-pointer'> Explore </button>
                    </div>})
            }</div>

        </div>
    )
}
export default ServiceSection
