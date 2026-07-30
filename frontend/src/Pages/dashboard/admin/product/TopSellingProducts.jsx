import React from 'react'

const TopSellingProducts = () => {
    return (
        <div>
            <p>Top selling Products</p>
            <div className='shadow-lg p-2'>
                <table>
                    <tbody>
                    <tr className='border-b border-zinc-200 bg-gray-100 text-sm font-bold'>
                        <td className='py-1 px-2 text-start'>Image And Name</td>
                        <td className='py-1 px-2 text-start'>View All BTN</td>
                    </tr>
                    <tr className='border-b border-zinc-200 text-sm text-zinc-600 font-semibold'>
                        <td className='py-1 px-2 text-start'>IMG And NAME</td>
                        <td className='py-1 px-2 text-start'>120 orders</td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
    )
}
export default TopSellingProducts
