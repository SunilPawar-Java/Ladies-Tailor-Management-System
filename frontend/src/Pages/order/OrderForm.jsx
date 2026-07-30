import React, {useState} from 'react'
import {useForm} from "react-hook-form";
import {useNavigate, useParams} from "react-router-dom";
import {SelectFields} from "../../components/form/SelectField.jsx";
import {NumberInputField} from "../../components/form/NumberInputField.jsx";
import {
    backNeckDesigns, colors,
    commonFittingTypes, frontNeckDesigns,
    materials, openingTypes,
    sizes, sleeveLengths
} from "../../constants/OrderConstants.js";
import {useAuth} from "../../context/AuthContext.jsx";


const OrderForm = () => {
    const navigate = useNavigate()
    const {id} = useParams()
    const {isLoggedIn} = useAuth()
    const [viewImage, setViewImage] = useState(false);
    const {
        register,
        handleSubmit,
        getValues
    } = useForm({defaultValues: JSON.parse(localStorage.getItem("orderFormData"))});
    const saveToDraft = (formData) => {
        formData.productId = id;
       localStorage.setItem("orderFormData", JSON.stringify(formData));
   }
    const submitOrderDetails = async (formData) => {
        if (isLoggedIn){
            const result = confirm("Are you sure you fill all data correctly as you wanted!");
            saveToDraft(formData)
            if (result) {
                navigate(`/products/product/${id}/order/${id}/payment`, {state: formData});
            }
        }else{
            navigate("/login")
        }


    }
    return (<>
        <form onSubmit={handleSubmit(submitOrderDetails)}
              className='w-screen space-y-5 pt-24 md:px-15 bg-zinc-100'>
            <div className='px-5 flex flex-col sm:flex-row justify-between md:px-10  sm:grid-cols-2 gap-5 border-5 border-blue-950 w-full rounded shadow-xl bg-white'>
                <div className='flex flex-col gap-5'>
                    <p className='font-extrabold text-2xl text-blue-950'>Body Measurements</p>
                    <h1 className='font-extrabold'>Basic Details:</h1>
                    <div className='flex flex-col sm:flex-row flex-wrap gap-5'>
                        <SelectFields label="Clothing material" name="clothMaterial" options={materials} register={register} />
                        <SelectFields label="Standerd Size" name="size" options={sizes} register={register} />
                        <SelectFields label="Colour of cloth" name="clothColor" options={colors} register={register} />
                    </div>

                </div>
                <div className='flex items-center '>
                    <p className='font-bold w-40 text-zinc-500'>See How to measure <span className='underline'>click on Image</span>
                    </p>
                    <img
                        className='h-50 w-auto cursor-pointer object-contain border-2 shadow-xl duration-500 hover:scale-105 active:scale-100'
                        onClick={() => setViewImage(true)}
                        src="/src/assets/images/Measurement.png" alt="Measurement Images"/>

                    {viewImage && (
                        <div
                            className='absolute h-screen pt-24 w-screen top-7 sm:top-0 fixed inset-0 bg-black/20 h-145 backdrop-blur flex justify-center items-center'>
                            <img className='shadow-2xl h-100 sm:h-140 w-screen object-contain'
                                 src="/src/assets/images/Measurement.png" alt="Measurement Images"/>
                            <p className='text-lg top-15 flex items-center justify-center sm:text-4xl right-2 sm:right-20 fixed bg-black/50 rounded-full px-4 py-2 text-white duration-500 cursor-pointer hover:scale-105 active:scale-100'
                               onClick={() => setViewImage(false)}
                            >x</p>
                        </div>
                    )}
                </div>
            </div>

            <div className='border-5 border-blue-950 flex flex-col flex-wrap rounded gap-5 p-5 sm:p-10 shadow-xl bg-white'>
                <p className='font-extrabold text-2xl text-blue-500'>Choose Your Custom Design</p>
                <div className='grid grid-cols-1 gap-5 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4'>
                    <SelectFields label="Front Neck" name="frontNeck" options={frontNeckDesigns} register={register}/>
                    <SelectFields label="Back Neck" name="backNeck" options={backNeckDesigns} register={register}/>
                    <SelectFields label="Fitting" name="fitting" options={commonFittingTypes} register={register}/>
                    <SelectFields label="Sleeve Length" name="sleeve" options={sleeveLengths} register={register}/>
                    <SelectFields label="Open/Close" name="openingClosing" options={openingTypes} register={register}/>
                </div>
            </div>

            <div className='border-5 border-blue-950 flex flex-col flex-wrap rounded gap-5 p-5 sm:p-10 shadow-xl bg-white'>
                <p className='font-extrabold text-2xl text-blue-950'>Specific Measurements</p>
                <div className='grid grid-cols-1 gap-5 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4'>
                    <NumberInputField label="Upper full leangth" name="upperFullLength" register={register}/>
                    <NumberInputField label="Bottom full length" name="bottomFullLength" register={register}/>
                    <NumberInputField label="Bust" name="bust" register={register}/>
                    <NumberInputField label="Waist" name="waist" register={register}/>
                    <NumberInputField label="Shoulder width" name="shoulder" register={register}/>
                    <NumberInputField label="Sleeve length" name="sleeveLength" register={register}/>
                    <NumberInputField label="Sleeve round" name="sleeveRound" register={register}/>
                    <NumberInputField label="Armhole round" name="armholeRound" register={register}/>
                    <NumberInputField label="Front neck depth" name="frontNeckDepth" register={register}/>
                    <NumberInputField label="Back Neck Depth" name="backNeckDepth" register={register}/>
                    <NumberInputField label="Hip" name="hip" register={register}/>
                    <NumberInputField label="Rise" name="rise" register={register}/>
                    <NumberInputField label="Thigh" name="thigh" register={register}/>
                    <NumberInputField label="Knee" name="knee" register={register}/>
                    <NumberInputField label="Calf" name="calf" register={register}/>
                    <NumberInputField label="Ankle" name="ankle" register={register}/>
                    <NumberInputField label="Instep" name="instep" register={register}/>
                </div>
                <div className='border-t-2'>
                    <p className='font-bold'>Message:</p>
                    <textarea {...register("message")} className='border w-full p-5'/>
                </div>
            </div>
            <div className='flex justify-center items-center gap-5 border border-blue-950 p-5 bg-white'>
                <button type="button" className='bg-yellow-600 rounded py-2 px-10 text-white font-bold transition-all active:scale-100 hover:scale-105 duration-500'
                        onClick={() => navigate(-1)}
                >
                    Previous
                </button>
                <button type="button"  className='bg-green-700 rounded py-2 px-10 text-white font-bold transition-all active:scale-100 hover:scale-105 duration-500'
                        onClick={() => saveToDraft(getValues())}
                >
                    Save
                </button>
                <button type="submit" className='bg-blue-700 rounded py-2 px-10 text-white font-bold transition-all active:scale-100 hover:scale-105 duration-500'>
                    Save And Next
                </button>
            </div>
        </form>
    </>);

};
export default OrderForm