import React, {useEffect, useState} from "react";
import {getAllOrders} from "../../../../api/OrderApi.js";

const Orders = () => {
    const [listOfOrders, setListOfOrders] = useState([]);
    const loadOrders = async () =>{
        try{
            const response = await getAllOrders();
            console.log(response.data)
            setListOfOrders(response.data)
        }catch (error) {
            if (error.response)
                alert(error.response.data)
        }
    }
    useEffect(() => {
        loadOrders()
    }, []);

    return (
        <div className='flex w-screen h-screen'>
            <div className='shadow-2xl flex-1 overflow-y-auto p-4 overflow-hidden'>
                <div className='pt-15 flex items-center justify-center'>
                    <table>
                        <thead>
                        <tr className='border-b border-zinc-200 bg-zinc-300'>
                            <th className='p-2 text-sm text-start'>Sr.</th>
                            <th className='p-2 text-sm text-start'>ID</th>
                            <th className='p-2 text-sm text-start'>Customer</th>
                            <th className='p-2 text-sm text-start'>Date</th>
                            <th className='p-2 text-sm text-start'>Amount</th>
                            <th className='p-2 text-sm text-start'>Payment Status</th>
                            <th className='p-2 text-sm text-start'>Order Status</th>
                            <th className='p-2 text-sm text-start'>Delivery Date</th>
                            <th className='p-2 text-sm text-start'>Action</th>

                        </tr>
                        </thead>
                        <tbody className='text-sm'>
                        {listOfOrders.map((order, index) =>{
                            return <tr key={index+1} className='border-b border-zinc-200'>
                                <td className='p-2 text-sm text-zinc-700 font-semibold text-center font-semibold'>{index+1}</td>
                                <td className='p-2 text-sm text-zinc-700 font-semibold text-center'>{order.id}</td>
                                <td className='p-2 text-sm text-zinc-700 font-semibold'>{order.usersDao.fullName}<br/>{order.usersDao.phone}</td>
                                <td className='p-2 text-sm text-zinc-700 font-semibold'>{order.placedDate}</td>
                                <td className='p-2 text-sm text-zinc-700 font-semibold text-start'>${order.totalAmount}</td>
                                <td className='p-2 text-sm text-zinc-700 font-semibold'>
                                    <p className={
                                        order.status === "PAYMENT_FAILED" ? `bg-red-200 rounded-lg p-1 text-red-700`:
                                            order.status === "PAYMENT_COMPLETE" ? `bg-green-200 rounded-lg p-1 text-green-700`:``}>
                                        {order.paymentStatus}
                                    </p>
                                </td>
                                <td className='p-2 text-sm text-zinc-700 font-semibold'>
                                    <p className={
                                        order.status === "PAYMENT_FAILED" ? `bg-red-200 rounded-lg p-1 text-red-700`:
                                        order.status === "PAYMENT_COMPLETE" ? `bg-green-200 rounded-lg p-1 text-green-700`:``}>
                                        {order.status}
                                    </p>
                                </td>
                                <td className='p-2 text-sm text-zinc-700 font-semibold'>{order.deliveryDate}</td>
                            </tr>
                        })
                        }

                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    )
}
export default Orders
