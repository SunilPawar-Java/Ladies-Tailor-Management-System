import React from 'react'
import MobileNavBar from "../../components/navbar/MobileNavBar.jsx";
import LaptopNavBar from "../../components/navbar/LaptopNavBar.jsx";

const NavBar = () => {
    return (
        <>
            <div className='block sm:hidden lg:hidden '>
                <MobileNavBar/>
            </div>
            <div className='hidden sm:block'>
                <LaptopNavBar/>
            </div>
        </>
    )
}
export default NavBar
