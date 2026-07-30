import React from 'react'
import instagramSvg from '../../assets/images/instagram-icon.svg'

const InstagramButton = () => {
    return (
        <button className='w-50 text-sm font-bold  text-white py-1 md:px-15 lg:px-15 bg-blue-950 rounded flex justify-center items-center gap-2 h
         hover:scale-105 active:scale-99
        duration-500
        '>
            <img className='h-5' src={instagramSvg} alt="whatsapp icon"/> Instagram
        </button>
    )
}
export default InstagramButton