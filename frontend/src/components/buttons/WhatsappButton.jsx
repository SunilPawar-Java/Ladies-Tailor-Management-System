import React from 'react'
import whatsappSvg from '../../assets/images/whatsapp-icon.svg'

const WhatsappButton = () => {
    return (<button className='w-50 text-sm font-bold  text-white py-1 md:px-15 lg:px-15 bg-blue-950 rounded flex justify-center items-center gap-2
                hover:scale-105 active:scale-99 duration-500
            '>
            <img className='h-5' src={whatsappSvg} alt="whatsapp icon"/>Whatsapp
        </button>)
}
export default WhatsappButton
