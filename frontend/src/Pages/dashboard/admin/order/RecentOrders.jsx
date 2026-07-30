import React from 'react'

const RecentOrders = () => {
    return (
        <div>
            <p>Recent Orders</p>
            <div className='shadow-lg p-2'>
                <table>
                    <tbody>
                    <tr className='border-b border-zinc-200 bg-gray-100 text-sm font-bold'>
                        <td className='py-1 px-2 text-start'>Sr.</td>
                        <td className='py-1 px-2 text-start'>ID</td>
                        <td className='py-1 px-2 text-start'>Customer</td>
                        <td className='py-1 px-2 text-start'>Amount</td>
                        <td className='py-1 px-2 text-start'>Status</td>
                        <td className='py-1 px-2 text-start'>Date</td>
                        <td className='py-1 px-2 text-start'>Action</td>
                    </tr>
                    <tr className='border-b border-zinc-200 text-sm text-zinc-600 font-semibold'>
                        <td className='py-1 px-2 text-start'>1</td>
                        <td className='py-1 px-2 text-start'>Order_1</td>
                        <td className='py-1 px-2 text-start'>S</td>
                        <td className='py-1 px-2 text-start'>350</td>
                        <td className='py-1 px-2 text-start'>Completed</td>
                        <td className='py-1 px-2 text-start'>12 Jul 2026</td>
                        <td className='py-1 px-2 text-start'>...</td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
    )
}
export default RecentOrders
