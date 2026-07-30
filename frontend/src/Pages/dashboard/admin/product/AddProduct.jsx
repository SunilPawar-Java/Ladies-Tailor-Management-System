import React, {useState} from 'react'
import {TextInputField} from "../../../../components/form/TextInputField.jsx";
import {SelectFields} from "../../../../components/form/SelectField.jsx";
import {
    colors,
    commonFittingTypes,
    materials,
    occasions, openingTypes,
    seasons,
    sizes,
    sleeveLengths
} from "../../../../constants/OrderConstants.js";
import {NumberInputField} from "../../../../components/form/NumberInputField.jsx";
import {useForm} from "react-hook-form";
import {addProduct} from "../../../../api/ProductApi.js";
import {useNavigate} from "react-router-dom";
const AddProduct = () => {
    const navigate = useNavigate()
    const {
        handleSubmit,
        register
    } = useForm();
    const [imageReview, setImageReview] = useState(null);
    const handleImage = (e) => {
        const file = e.target.files[0];
        if (!file) return;
        setImageReview(URL.createObjectURL(file))
    }
    const submitProduct = async (productData) =>{
        try {
            const response = await addProduct(productData)
        }catch (error) {
            if (error.response){
                alert(error.response.data)
            }
        }
    }
    return (
        <>
            <div className="flex flex-1 overflow-y-auto py-5 flex-col items-center justify-center shadow-2xl">
                <div>
                    <p className='text-xl font-bold text-blue-950'>Add Product</p>
                </div>
                <form onSubmit={handleSubmit(submitProduct)} className='flex flex-col gap-5'>
                    <div className='grid sm:grid-cols-2 md:grid-cols-4 gap-5'>
                        <div className='flex items-center justify-center'>
                            <p>Upload Image</p>
                            <input type="file" {...register("image")}
                                   accept="image/*" placeholder="Upload Image"
                                   onChange={handleImage}
                                   className='border-2 active:scale-95 w-23 p-2 bg-gray-400'/>
                            <img src={imageReview}
                                 className='h-15 rounded hover:scale-150 duration-500'
                                 alt="Selected Image"/>
                        </div>
                        <TextInputField label="itemName" name="Name" register={register} placeholder="Enter itemName"/>
                        <TextInputField label="itemType" name="Type" register={register} placeholder="Enter itemType"/>
                        <TextInputField label="mainCategory" name="mainCategory" register={register} placeholder="Enter Main Category"/>
                        <TextInputField label="locationCategory" name="locationCategory" register={register} placeholder="locationCategory"/>
                        <TextInputField label="neckline" name="neckline" register={register} placeholder="Enter neckline"/>
                        <TextInputField label="sleeveType" name="sleeveType" register={register} placeholder="Enter sleeveType"/>
                        <TextInputField label="lengthType" name="lengthType" register={register} placeholder="Enter lengthType"/>
                        <TextInputField label="hemline" name="hemline" register={register} placeholder="Enter hemline"/>
                        <SelectFields label="Standerd size" name="size" options={sizes} register={register}/>
                        <SelectFields label="Colour of cloth" name="color" options={colors} register={register}/>
                        <SelectFields label="occasion" name="occasion" options={occasions} register={register}/>
                        <SelectFields label="season" name="season" options={seasons} register={register}/>
                        <SelectFields label="Clothing material" name="fabric" options={materials} register={register}/>
                        <SelectFields label="Sleeve Length" name="sleeveLength" options={sleeveLengths} register={register}/>
                        <SelectFields label="fitType" name="fitType" options={commonFittingTypes} register={register}/>
                        <SelectFields label="Open/Close" name="closureType" options={openingTypes} register={register}/>
                        <NumberInputField label="stock" name="stock" register={register} placeholder="Enter stock"/>
                        <NumberInputField label="price" name="price" register={register} placeholder="Enter price"/>
                        <NumberInputField label="discountPercentage" name="discountPercentage" register={register} placeholder="Enter discountPercentage"/>
                    </div>
                    <div className='border-t-2'>
                        <p className='font-bold'>Description:</p>
                        <textarea {...register("description")} placeholder="Enter description" className='border w-full p-2'/>
                    </div>
                    <div className='flex gap-2 items-center justify-center'>
                        <button type="button"
                                onClick={()=> navigate(-1)}
                                className='bg-red-700 py-2 px-5 rounded font-semibold text-white hover:scale-105 active:scale-100 duration-500'>
                            Cancel
                        </button>
                        <button type="submit"
                            className='bg-blue-700 py-2 px-5 rounded font-semibold text-white hover:scale-105 active:scale-100 duration-500'>
                            Save Product
                        </button>
                    </div>
                </form>
            </div>
        </>
    )
}
export default AddProduct