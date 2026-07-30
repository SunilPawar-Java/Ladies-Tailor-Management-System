import {useForm} from "react-hook-form";
import {login} from "../../api/Authentication.js";
import {useNavigate} from "react-router-dom";
import {useState} from "react";
import {useAuth} from "../../context/AuthContext.jsx";

const Login = () => {
    const navigate = useNavigate()
    const  {
        handleSubmit,
        register,
        formState: {errors}
    } = useForm();
    const [loginMessage, setLoginMessage] = useState(null);
    const {authLogin} = useAuth()
    const handleLogin = async (loginCred) => {
        try {
            const response = await login(loginCred)
            setLoginMessage("Login Successfully..")
            authLogin(response.data.JToken, response.data.role.authority)
        }catch (error){
            if (error.response)
                setLoginMessage("Wrong Username or Password")
        }
    }

    return (
        <div className="border flex flex-col gap-10 h-screen w-screen justify-center items-center">
            <form onSubmit={handleSubmit(handleLogin)} className="flex flex-col gap-5">
                <p className='text-md'>{loginMessage}</p>
                <div className='flex flex-col'>
                    <label>Enter Username or Email</label>
                    <input type="text" {...register("username", {required: "Enter Username"})}
                           placeholder="Username or Email"
                           className="border w-100 rounded py-2"/>
                    {errors.username && (<p className='text-red-700 text-sm'>{errors.username.message}</p>)}
                </div>
                <div className='flex flex-col'>
                    <label>Enter Password</label>
                    <input type="text" {...register("password", {required: "Enter Password"})}
                           placeholder='Password'
                           className='border w-100 rounded py-2'/>
                    {errors.password && (<p className='text-red-700 text-sm'>{errors.password.message}</p>)}
                </div>
                <div className='flex gap-5 justify-center items-center'>
                    <button type="reset"
                            className='border py-1 px-2 bg-yellow-600 text-sm text-white font-bold rounded py-2 px-4 cursor-pointer active:scale-95'>
                        Reset
                    </button>
                    <button type="submit"
                            className='border py-1 px-2 bg-blue-600 text-sm text-white font-bold rounded py-2 px-4 cursor-pointer active:scale-95'>
                        login
                    </button>
                </div>
            </form>
            <div className='flex gap-5'>
                <button
                    onClick={() => navigate(-1)}
                    className='py-2 px-4 text-white text-sm font-bold bg-blue-950 rounded hover:scale-105 active:scale-100 duration-500'>
                    Back
                </button>
                <button
                    onClick={() => navigate("/home")}
                    className='py-2 px-4 text-white text-sm font-bold bg-blue-950 rounded hover:scale-105 active:scale-100 duration-500'>
                    Go Home
                </button>
            </div>
        </div>
    )
}
export default Login
