import React from 'react'
import {totals} from "../../../../constants/AdminDashboardConstants.jsx";

const AdminDashboardStats = () => {
    return (
        <div>
            <p className='font-bold'>Dashboard Overview Cards</p>
            <div className='flex gap-2'>
                {totals.map((total, index) => {
                    const Icon = total.icon;
                    return <div key={index}
                                className='w-50 h-20 flex items-center justify-center gap-5 rounded shadow-md p-2 cursor-pointer active:scale-99'>
                        <div className=''>
                            <Icon className={total.style}/>
                        </div>
                        <div>
                            <p className='text-sm text-nowrap text-zinc-600'>{total.name}</p>
                            <p className='text-sm font-bold'>{total.number}</p>
                        </div>

                    </div>
                })}
            </div>
        </div>
    )
}
export default AdminDashboardStats
