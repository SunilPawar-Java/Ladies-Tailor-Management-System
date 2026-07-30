import React from 'react'
import {useNavigate} from "react-router-dom";

const LoginButton = () => {
    const navigate = useNavigate();
    return (
        <button
            onClick={()=>navigate("/login", {replace: true})}
            className='text-white font-bold bg-blue-950 rounded-3xl p-2 text-sm hover:scale-105 active:scale-99 duration-500'>
            Log in
        </button>
    )
}
export default LoginButton