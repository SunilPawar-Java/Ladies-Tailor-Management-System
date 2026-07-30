import {useAuth} from "../../../context/AuthContext.jsx";
import {Outlet, useNavigate, NavLink} from "react-router-dom";
import NotFound from "../../notfound/NotFound.jsx";
import {Box, Handbag, HomeIcon, Users, Wallet} from "lucide-react";

const AdminProfile = () => {
    const navigate = useNavigate()
    const {isLoggedIn} = useAuth()
    const ordersPage = () => {
      navigate()
    }
    return (<>
        <div className='w-screen h-screen flex'>
            <div className='w-50 flex flex-col gap-10 p-5 shrink-0 shadow-2xl bg-blue-50/50'>
                <p className='text-2xl text-blue-600 font-bold'>Welcome</p>
                <div className='flex flex-col text-sm text-blue-950'>
                    <NavLink to="/profile/dashboard"
                        className={({isActive})=> isActive ?'flex items-center border-2 border-blue-900 gap-2 bg-blue-100 text-start font-bold bg-blue-100 p-2 rounded cursor-pointer': 'flex items-center gap-2 focus:bg-blue-100 text-start hover:font-bold hover:bg-blue-100 p-2 rounded cursor-pointer'}>
                        <HomeIcon className='w-5'/>
                        Dashboard
                    </NavLink>
                    <NavLink to="/profile/orders"
                        className={({isActive})=> isActive ?'flex items-center border-2 border-blue-900 gap-2 bg-blue-100 text-start font-bold bg-blue-100 p-2 rounded cursor-pointer': 'flex items-center gap-2 focus:bg-blue-100 text-start hover:font-bold hover:bg-blue-100 p-2 rounded cursor-pointer'}>
                        <Handbag className='w-5'/>
                            Orders
                    </NavLink>
                    <NavLink to="/profile/products"
                          className={({isActive})=> isActive ?'flex items-center border-2 border-blue-900 gap-2 bg-blue-100 text-start font-bold bg-blue-100 p-2 rounded cursor-pointer': 'flex items-center gap-2 focus:bg-blue-100 text-start hover:font-bold hover:bg-blue-100 p-2 rounded cursor-pointer'}>
                          <Box className='w-5'/> Products
                    </NavLink>
                    <NavLink to="/profile/customers"
                        className={({isActive})=> isActive ?'flex items-center border-2 border-blue-900 gap-2 bg-blue-100 text-start font-bold bg-blue-100 p-2 rounded cursor-pointer': 'flex items-center gap-2 focus:bg-blue-100 text-start hover:font-bold hover:bg-blue-100 p-2 rounded cursor-pointer'}>
                        <Users className='w-5'/>
                        Customers/Buyers
                    </NavLink>
                    <div>
                    </div>
                    <NavLink to="/profile/payments"
                        className={({isActive})=> isActive ?'flex items-center border-2 border-blue-900 gap-2 bg-blue-100 text-start font-bold bg-blue-100 p-2 rounded cursor-pointer': 'flex items-center gap-2 focus:bg-blue-100 text-start hover:font-bold hover:bg-blue-100 p-2 rounded cursor-pointer'}>
                        <Wallet className='w-5'/>
                        Payments
                    </NavLink>
                    <NavLink to="/profile/todo"
                        className={({isActive})=> isActive ?'flex items-center border-2 border-blue-900 gap-2 bg-blue-100 text-start font-bold bg-blue-100 p-2 rounded cursor-pointer': 'flex items-center gap-2 focus:bg-blue-100 text-start hover:font-bold hover:bg-blue-100 p-2 rounded cursor-pointer'}>
                        Todo
                    </NavLink>
                </div>
            </div>
            <Outlet/>
            {isLoggedIn === 0 && (<NotFound/>)}
        </div>
    </>)
}
export default AdminProfile
