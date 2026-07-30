import NavBar from "../Pages/home/NavBar.jsx";
import FooterSection from "../Pages/home/FooterSection.jsx";
import {Outlet} from "react-router-dom";

const Layout = () => {
    return (
        <>
            <NavBar/>
            <Outlet/>
            <FooterSection/>
        </>
    )
}
export default Layout
