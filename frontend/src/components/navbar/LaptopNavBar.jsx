import React, {useState} from 'react'
import SignUpButton from "../buttons/SignUpButton.jsx";
import LoginButton from "../buttons/LoginButton.jsx";
import {ChevronDown} from "lucide-react";
import { ChevronUp } from 'lucide-react';
import {Link, useNavigate} from "react-router-dom";
import Logout from "../buttons/Logout.jsx";
import {useAuth} from "../../context/AuthContext.jsx";
import {NavLink} from "react-router-dom";

const LaptopNavBar = () => {
    const {isLoggedIn} = useAuth()
    const [serviceActive, setServiceActive] = useState(false);
    const navigate = useNavigate()
    const handleProfile = () => {
      navigate("/profile")
    }
    return (<>
        <div className='flex p-4 w-full justify-between items-center bg-white/80 backdrop-blur-2xl border border-white/20 fixed z-1 top-0'>
            <div className='flex flex-col gap-1 items-center text-white'>
                <h1 className='font-bold text-xl text-blue-900 hover:cursor-pointer'>Ap Tailors Maravde</h1>
                <div className='flex pl-5 pr-5 text-black text-sm font-semibold items-center rounded-t-2xl gap-5'>
                    <NavLink className={({ isActive })=> isActive ? 'opacity-80 underline':'hover:opacity-80 focus:underline' } to="/home">Home</NavLink>
                    <NavLink className={({ isActive })=> isActive ? 'opacity-80 underline':'hover:opacity-80 focus:underline' } to="products">Products</NavLink>
                    <div className='flex items-center hover:opacity-80'>
                        <NavLink className={({ isActive })=> isActive ? 'opacity-80 underline':'hover:opacity-80 focus:underline' }  to="sevices">Services</NavLink>
                        <p onClick={() => {
                            if (serviceActive) setServiceActive(false)
                            else setServiceActive(true)}}
                        >{
                            serviceActive && (<ChevronUp className='h-4 w-4 ml-1'/>) ||
                                serviceActive === false && (<ChevronDown className='h-4 w-4 ml-1'/>)
                            }
                        </p>
                        { serviceActive && (
                            <div className='p-5 flex flex-col items-center bg-white/70 backdrop-blur-lg shadow-lg fixed top-22 left-22 rounded
                            z-10'>
                                <ul className='flex flex-col gap-4 text-sm'>
                                    <NavLink to='Blouse_Stitching_url' className={({ isActive })=> isActive ? 'opacity-80 underline':'hover:opacity-80 focus:underline' }>Blouse Stitching</NavLink>
                                    <NavLink to='Kurti_Stitching_url' className={({ isActive })=> isActive ? 'opacity-80 underline':'hover:opacity-80 focus:underline' }>Kurthi Stitching</NavLink>
                                </ul>
                            </div>
                        )}
                    </div>
                    <NavLink className={({ isActive })=> isActive ? 'opacity-80 underline':'hover:opacity-80 focus:underline' } to="blogs">Blogs</NavLink>
                    <NavLink className={({ isActive })=> isActive ? 'opacity-80 underline':'hover:opacity-80 focus:underline' } to="about">About</NavLink>
                    <NavLink className={({ isActive })=> isActive ? 'opacity-80 underline':'hover:opacity-80 focus:underline' } to="contact">Contact</NavLink>

                </div>
            </div>
            <div className='flex gap-5 items-center text-white'>
                <SignUpButton/>
                {isLoggedIn ? (<Logout/>) : (<LoginButton/>)}
                {isLoggedIn && (
                    <button
                        onClick={handleProfile}
                        className='text-white font-bold bg-blue-950 rounded-3xl p-2 text-sm hover:scale-105 active:scale-99 duration-500'>
                        Profile
                    </button>
                )}
            </div>
        </div>
    </>);
}
export default LaptopNavBar
