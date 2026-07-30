import React from 'react'
import {useLocation, useNavigate} from "react-router-dom";
import {createPayment, placeOrder} from "../../api/OrderApi.js";
import {paymentFailure, verifyPayment} from "../../api/PaymentApi.js";

const Payment = () => {
    const data = useLocation().state;
    const navigate = useNavigate()
    const createOrder = async () => {
        try {
            const orderResponse = await placeOrder(data);
            const paymentSuccess = await makePayment(orderResponse.data.orderId);
            alert(paymentSuccess.data)
        }catch (error) {
            if (error.response){
                alert("Error:\n"+error.response.message)
            }
        }
    }

    const makePayment = async (orderId) => {
        try {
            const paymentResponse = await createPayment(orderId);
            const paymentData = paymentResponse.data;
            const options = {
                name: "Ap Tailor Maravde",
                key: import.meta.env.VITE_RAZORPAY_KEY_ID,
                amount: paymentData.amount,
                currency: paymentData.currency,
                order_id: paymentData.id,
                receipt: paymentData.receipt,
                handler: async (response) => {
                    const verificationData = {
                        rozOrderId: response.razorpay_order_id,
                        rozPaymentId: response.razorpay_payment_id,
                        rozSignature: response.razorpay_signature,
                        orderId: paymentData.receipt
                    }
                    const verificationResponse = await verifyPayment(verificationData)
                    alert("Payment -> "+ verificationResponse.data)
                },
                theme:{
                    color: "darkblue"
                }
            }
            var Razor = window.Razorpay(options);
            Razor.open();
            Razor.on('payment.failed', async (response) => {
                const failureData = {
                    rozOrderId: response.error.metadata.order_id,
                    roz: response.error.metadata.payment_id,
                    failureCode: response.error.code,
                    failureReason: response.error.description,
                    failureSource: response.error.source
                }
                try {
                    const failureResponse = await paymentFailure(failureData)
                    alert(failureResponse.data)
                }catch (error) {
                    if (error.response)
                        alert("Error: "+error.response.data)
                }
            })
        }catch (error){
            if (error.response){
                alert("Payment: \n"+error.response.data)
            }
        }

    }
    return (<>
        <div className='pt-24 lg:pt-25 w-screen '>
            <div className='flex flex-col shadow-2xl mx-10 rounded-lg overflow-hidden'>
                <table >
                    <thead className=''>
                    <tr className='border-b'>
                        <th className='py-2 bx-4 text-center bg-gray-300'>Product ID {data.productId}</th>
                    </tr>
                    </thead>
                    <tbody className='grid grid-cols-3'>
                    <tr className='hover:bg-gray-100'>
                        <td className=' font-semibold py-2 px-4 text-left'>Clothing material</td>
                        <td className=' py-2 px-4 text-left'>{data.clothMaterial}</td>
                    </tr>
                    <tr className='w-100 hover:bg-gray-100'>
                        <td className='font-semibold py-2 px-4 text-left'>Colour of cloth/material</td>
                        <td className=' py-2 px-4 text-left'>{data.clothColor}</td>
                    </tr>
                    <tr className='w-100 hover:bg-gray-100'>
                        <td className='font-semibold py-2 px-4 text-left'>Front Neck</td>
                        <td className=' py-2 px-4 text-left'>{data.frontNeck}</td>
                    </tr>
                    <tr className='w-100 hover:bg-gray-100'>
                        <td className='font-semibold py-2 px-4 text-left'>Back Neck</td>
                        <td className=' py-2 px-4 text-left'>{data.backNeck}</td>
                    </tr>
                    <tr className='w-100 hover:bg-gray-100'>
                        <td className='font-semibold py-2 px-4 text-left'>Fitting</td>
                        <td className=' py-2 px-4 text-left'>{data.fitting}</td>
                    </tr>
                    <tr className='w-100 hover:bg-gray-100'>
                        <td className='font-semibold py-2 px-4 text-left'>Sleeve Length</td>
                        <td className=' py-2 px-4 text-left'>{data.sleeve}</td>
                    </tr>
                    <tr className='w-100  hover:bg-gray-100'>
                        <td className='font-semibold py-2 px-4 text-left'>Open/Close</td>
                        <td className=' py-2 px-4 text-left'>{data.openingClosing}</td>
                    </tr>
                    <tr className='w-100  hover:bg-gray-100'>
                        <td className='font-semibold py-2 px-4 text-left'>Upper full length</td>
                        <td className=' py-2 px-4 text-left'>{data.upperFullLength}</td>
                    </tr>
                    <tr className='w-100  hover:bg-gray-100'>
                        <td className='font-semibold py-2 px-4 text-left'>Bottom full length</td>
                        <td className=' py-2 px-4 text-left'>{data.bottomFullLength}</td>
                    </tr>
                    <tr className='w-100  hover:bg-gray-100'>
                        <td className='font-semibold py-2 px-4 text-left'>Bust</td>
                        <td className=' py-2 px-4 text-left'>{data.bust}</td>
                    </tr>
                    <tr className='w-100  hover:bg-gray-100'>
                        <td className='font-semibold py-2 px-4 text-left'>Waist</td>
                        <td className=' py-2 px-4 text-left'>{data.waist}</td>
                    </tr>
                    <tr className='w-100  hover:bg-gray-100'>
                        <td className='font-semibold py-2 px-4 text-left'>Shoulder width</td>
                        <td className=' py-2 px-4 text-left'>{data.shoulder}</td>
                    </tr>
                    <tr className='w-100  hover:bg-gray-100'>
                        <td className='font-semibold py-2 px-4 text-left'>Sleeve length</td>
                        <td className=' py-2 px-4 text-left'>{data.sleeveLength}</td>
                    </tr>
                    <tr className='w-100  hover:bg-gray-100'>
                        <td className='font-semibold py-2 px-4 text-left'>Sleeve round</td>
                        <td className=' py-2 px-4 text-left'>{data.sleeveRound}</td>
                    </tr>
                    <tr className='w-100  hover:bg-gray-100'>
                        <td className='font-semibold py-2 px-4 text-left'>Armhole round</td>
                        <td className=' py-2 px-4 text-left'>{data.armholeRound}</td>
                    </tr>
                    <tr className='w-100  hover:bg-gray-100'>
                        <td className='font-semibold py-2 px-4 text-left'>Front neck depth</td>
                        <td className=' py-2 px-4 text-left'>{data.frontNeckDepth}</td>
                    </tr>
                    <tr className='w-100  hover:bg-gray-100'>
                        <td className='font-semibold py-2 px-4 text-left'>Back Neck Depth</td>
                        <td className=' py-2 px-4 text-left'>{data.backNeckDepth}</td>
                    </tr>
                    <tr className='w-100  hover:bg-gray-100'>
                        <td className='font-semibold py-2 px-4 text-left'>Hip</td>
                        <td className=' py-2 px-4 text-left'>{data.hip}</td>
                    </tr>
                    <tr className='w-100  hover:bg-gray-100'>
                        <td className='font-semibold py-2 px-4 text-left'>Rise</td>
                        <td className=' py-2 px-4 text-left'>{data.rise}</td>
                    </tr>
                    <tr className='w-100  hover:bg-gray-100'>
                        <td className='font-semibold py-2 px-4 text-left'>Thigh</td>
                        <td className=' py-2 px-4 text-left'>{data.thigh}</td>
                    </tr>
                    <tr className='w-100  hover:bg-gray-100'>
                        <td className='font-semibold py-2 px-4 text-left'>Knee</td>
                        <td className=' py-2 px-4 text-left'>{data.knee}</td>
                    </tr>
                    <tr className='w-100  hover:bg-gray-100'>
                        <td className='font-semibold py-2 px-4 text-left'>Calf</td>
                        <td className=' py-2 px-4 text-left'>{data.calf}</td>
                    </tr>
                    <tr className='w-100  hover:bg-gray-100'>
                        <td className='font-semibold py-2 px-4 text-left'>Ankle</td>
                        <td className=' py-2 px-4 text-left'>{data.angle}</td>
                    </tr>
                    <tr className='w-100  hover:bg-gray-100'>
                        <td className='font-semibold py-2 px-4 text-left'>Instep</td>
                        <td className=' py-2 px-4 text-left'>{data.instep}</td>
                    </tr>
                    <tr className='w-100  hover:bg-gray-100'>
                        <td className='font-semibold py-2 px-4 text-left'>Message</td>
                        <td className=' py-2 px-4 text-left'>{data.message}</td>
                    </tr>
                    </tbody>
                </table>
            </div>
            <div className='flex justify-center p-5 m-5 shadow-2xl gap-5 bg-white rounded-lg'>
                <button className='bg-red-700 py-2 px-8 rounded font-bold text-white hover:scale-105 active:scale-100 duration-500'
                        onClick={() => navigate(-1)}
                >
                    Edit Details
                </button>
                <button
                    onClick={createOrder}
                    className='bg-blue-700 py-2 px-8 rounded font-bold text-white hover:scale-105 active:scale-100 duration-500'>
                    Place Order
                </button>
            </div>
        </div>
    </>)
}
export default Payment