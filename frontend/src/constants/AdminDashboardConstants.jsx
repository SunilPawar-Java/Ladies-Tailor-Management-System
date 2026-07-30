import {Box, CircleCheckBig, Clock, Handbag, IndianRupee, Users} from "lucide-react";

export const totals = [
    {
        id: 1,
        icon: Users,
        name: "Total Customers",
        number: "1,200",
        style: "bg-blue-100 text-blue-500 rounded p-2 h-8 w-8"
    },{
        id: 2,
        icon: Box,
        name: "Total Products",
        number: "3,200",
        style: "bg-green-100 text-green-500 rounded p-2 h-8 w-8"
    },{
        id: 3,
        icon: Handbag,
        name: "Total Orders",
        number: "200",
        style: "bg-orange-100 text-orange-500 rounded p-2 h-8 w-8"
    },{
        id: 4,
        icon: Clock,
        name: "Pending Orders",
        number: "10",
        style: "bg-blue-100 text-blue-500 rounded p-2 h-8 w-8"
    },{
        id: 5,
        icon: CircleCheckBig,
        name: "Completed Orders",
        number: "190",
        style: "bg-purple-100 text-purple-500 rounded p-2 h-8 w-8"
    },{
        id: 6,
        icon: IndianRupee,
        name: "Total Revenue",
        number: "$12,200",
        style: "bg-pink-100 text-pink-500 rounded p-2 h-8 w-8"
    }
]