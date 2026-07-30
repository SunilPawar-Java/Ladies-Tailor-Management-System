import {Routes, Route} from 'react-router-dom'
import Home from "./Pages/home/Home.jsx";
import Layout from "./layout/Layout.jsx";
import About from "./Pages/about/About.jsx";
import Login from "./Pages/authentication/Login.jsx";
import ProductDetails from "./Pages/product/ProductDetails.jsx"
import Products from "./Pages/product/Products.jsx";
import ProductGallery from "./Pages/product/ProductGallery.jsx";
import OrderForm from "./Pages/order/OrderForm.jsx";
import Payment from "./Pages/order/Payment.jsx";
import NotFound from "./Pages/notfound/NotFound.jsx";
import AdminDashboard from "./Pages/dashboard/admin/AdminDashboard.jsx";
import EditProduct from "./Pages/dashboard/admin/product/EditProduct.jsx";
import AddProduct from "./Pages/dashboard/admin/product/AddProduct.jsx";
import ProductsDash from "./Pages/dashboard/admin/product/ProductsDash.jsx";
import AdminProfile from "./Pages/dashboard/admin/AdminProfile.jsx";
import {useAuth} from "./context/AuthContext.jsx";
import CustomerDashboard from "./Pages/dashboard/customer/CustomerDashboard.jsx";
import Orders from "./Pages/dashboard/admin/order/Orders.jsx";

const App = () => {
    const {isLoggedIn, role} = useAuth()
    return (
        <>
            <Routes>
                <Route path="/" element={<Layout/>}>
                    <Route index element={<Home/>}/>
                    <Route path="/home" element={<Home/>}/>
                    <Route path="/about" element={<About/>}/>
                    <Route path="/login" element={<Login/>}/>
                    <Route path="/products" element={<Products/>}>
                        <Route index element={<ProductGallery/>}/>
                        <Route path="/products/gallery" element={<ProductGallery/>}/>
                        <Route path="/products/product/:id" element={<ProductDetails/>}/>
                        <Route path="/products/product/:id/order/:id" element={<OrderForm/>}/>
                        <Route path="/products/product/:id/order/:id/payment" element={<Payment/>}/>
                    </Route>
                </Route>
                <Route path="/profile" element={role === "ROLE_ADMIN" && <AdminProfile/> || role === null && <NotFound/>}>
                    <Route index element={role === "ROLE_ADMIN" && <AdminDashboard/> || role === "ROLE_CUSTOMER" && <CustomerDashboard/>} />
                    <Route path="/profile/dashboard" element={role === "ROLE_ADMIN" && <AdminDashboard/>} />
                    <Route path="/profile/products" element={role === "ROLE_ADMIN" && <ProductsDash/>}/>
                    <Route path="/profile/products/edit" element={role === "ROLE_ADMIN" && <EditProduct/>}/>
                    <Route path="/profile/products/add" element={role === "ROLE_ADMIN" && <AddProduct/>}/>
                    <Route path="/profile/orders" element={role === "ROLE_ADMIN" && <Orders/>} />
                    <Route index element={role === "ROLE_CUSTOMER" && <CustomerDashboard/>}/>
                </Route>
                <Route path="*" element={<NotFound/>}/>
            </Routes>
        </>
    )
}
export default App
