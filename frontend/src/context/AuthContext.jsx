import React, {useState, createContext, useContext} from 'react'
import {useNavigate} from "react-router-dom";

const AuthContext = createContext();

export const AuthProvider = ({children}) => {
    const [isLoggedIn, setIsLoggedIn] = useState(!!localStorage.getItem("JToken"));
    const [role, setRole] = useState(localStorage.getItem("role"));
    const navigate = useNavigate()
    const authLogin = (token, role) =>{
        localStorage.setItem("JToken", token);
        localStorage.setItem("role", role);
        setIsLoggedIn(true);
        navigate(-1);
    }

    const authLogout = () => {
        localStorage.removeItem("JToken");
        localStorage.removeItem("role");
        setIsLoggedIn(false)
        setRole(null)
        navigate("/login")
    }
    return (
        <AuthContext.Provider value={{authLogin, authLogout, isLoggedIn, setIsLoggedIn, role}}>
            {children}
        </AuthContext.Provider>
    )
}
export const useAuth = () => useContext(AuthContext);
