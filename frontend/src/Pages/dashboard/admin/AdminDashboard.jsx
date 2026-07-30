import {useNavigate} from "react-router-dom";
import RecentOrders from "./order/RecentOrders.jsx";
import TopSellingProducts from "./product/TopSellingProducts.jsx";
import AdminDashboardStats from "./stats/AdminDashboardStats.jsx";

const AdminDashboard = () => {
    const navigate = useNavigate()
    return (
        <div className='w-screen p-5 flex flex-col gap-10 flex-1 shadow-2xl overflow-y-auto'>
            <AdminDashboardStats/>
            <RecentOrders/>
            <TopSellingProducts/>
            <div>
                <p className='font-bold'>Quick Actions</p>
                <div className='flex gap-2'>
                    <div className='w-50 h-20 flex items-center justify-center gap-5 cursor-pointer rounded shadow-md p-2 active:scale-99'
                         onClick={()=>navigate("/profile/products/add")}>
                        <div className=''>
                            +
                        </div>
                        <div>
                            <p className='text-sm text-nowrap text-zinc-600'>Add Product</p>
                        </div>
                    </div>
                    <div className='w-50 h-20 flex items-center justify-center gap-5 cursor-pointer rounded shadow-md p-2 active:scale-99'>
                        <div className=''>
                            +
                        </div>
                        <div>
                            <p className='text-sm text-nowrap text-zinc-600'>Add Customer</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    )
}
export default AdminDashboard