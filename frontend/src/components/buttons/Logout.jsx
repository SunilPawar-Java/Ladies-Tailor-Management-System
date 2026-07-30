import React, {useContext} from 'react'
import {logout} from "../../api/Authentication.js";
import {useAuth} from "../../context/AuthContext.jsx";

const Logout = () => {
    const {authLogout} = useAuth()
    const handleLogout = async () => {
        try {
            const response = await logout()
            alert(response.data.message)
            authLogout()
        }catch (error){
            if (error){
                alert(error.response.data)
            }
        }

    }
    return (
        <button
            onClick={handleLogout}
            className='text-white font-bold bg-blue-950 rounded-3xl p-2 text-sm hover:scale-105 active:scale-99 duration-500'>
            Logout
        </button>
    )
}
export default Logout
