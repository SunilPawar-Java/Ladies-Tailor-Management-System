import React, {useRef} from 'react'
import api from "../../api/api.js";

const Login = () => {
    const username = useRef("");
    const password = useRef("");
    const formDate = async (e) => {
        e.preventDefault();
        try {
            const response = await api.post("/auth/login", {
                username: username.current.value,
                password: password.current.value
                },{
                withCredentials: true
                }
            );
            console.log(response.data);
            localStorage.setItem("JToken",response.data.JToken)

        }catch (error){
            console.error(error);
        }
    }
    return (
        <div className="border flex h-screen w-screen justify-center items-center">
            <form onSubmit={formDate} className="flex flex-col gap-5">
                <input type="text" ref={username} placeholder="Username or Email"
                       className="border"
                />
                <input type="text" ref={password} placeholder='Password'
                       className='border'
                />
                <button type="submit"
                className='border py-1 px-2 cursor-pointer active:scale-95'
                >
                    login
                </button>
            </form>
        </div>
    )
}
export default Login
