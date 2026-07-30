import React, {useState} from 'react'
import {useForm} from "react-hook-form";
import {TextInputField} from "../../../../components/form/TextInputField.jsx";
import {NumberInputField} from "../../../../components/form/NumberInputField.jsx";
import {
    colors, commonFittingTypes,
    materials,
    occasions,
    openingTypes,
    seasons,
    sizes,
    sleeveLengths
} from "../../../../constants/OrderConstants.js";
import {SelectFields} from "../../../../components/form/SelectField.jsx";
import {useLocation, useNavigate} from "react-router-dom";
import {updateProductPartially} from "../../../../api/ProductApi.js";

const EditProduct = () => {
    const navigate = useNavigate()
    const productId = useLocation().state
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
    const updateProductInformation = async (formData) => {
        const confirmation = confirm("Are you sure?")
        if (confirmation){
            formData.id = productId;
            const response = await updateProductPartially(formData);
            alert(response.data.message)
            navigate(-1)
        }
    }
    return (
        <div className=" px-10 flex-1 flex-col gap-10 items-center justify-center shadow-2xl overflow-y-auto">
            <div>
                <p className='text-xl text-center font-bold text-blue-950'>Edit Product Details</p>
            </div>
            <form onSubmit={handleSubmit(updateProductInformation)}
                  className=' flex flex-col  gap-5'>
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
                    <TextInputField label="Name" name="itemName" register={register} placeholder="Enter itemName"/>
                    <TextInputField label="Type" name="itemType" register={register} placeholder="Enter itemType"/>
                    <TextInputField label="Main Category" name="mainCategory" register={register} placeholder="Enter Main Category"/>
                    <TextInputField label="Location Category" name="locationCategory" register={register} placeholder="locationCategory"/>
                    <TextInputField label="Neck line" name="neckline" register={register} placeholder="Enter neckline"/>
                    <TextInputField label="Sleeve Type" name="sleeveType" register={register} placeholder="Enter sleeveType"/>
                    <TextInputField label="Length Type" name="lengthType" register={register} placeholder="Enter lengthType"/>
                    <TextInputField label="Hem line" name="hemline" register={register} placeholder="Enter hemline"/>
                    <SelectFields label="Standerd size" name="size" options={sizes} register={register}/>
                    <SelectFields label="Colour of cloth" name="color" options={colors} register={register}/>
                    <SelectFields label="Occasion" name="occasion" options={occasions} register={register}/>
                    <SelectFields label="Season" name="season" options={seasons} register={register}/>
                    <SelectFields label="Cloth material" name="fabric" options={materials} register={register}/>
                    <SelectFields label="Sleeve Length" name="sleeveLength" options={sleeveLengths} register={register}/>
                    <SelectFields label="FitType" name="fitType" options={commonFittingTypes} register={register}/>
                    <SelectFields label="Open/Close" name="closureType" options={openingTypes} register={register}/>
                    <NumberInputField label="Stock" name="stock" register={register} placeholder="Enter stock"/>
                    <NumberInputField label="Price" name="price" register={register} placeholder="Enter price"/>
                    <NumberInputField label="Discount Percentage" name="discountPercentage" register={register} placeholder="Enter discountPercentage"/>
                </div>
                <div className='border-t-2'>
                <p className='font-bold'>Description:</p>
                <textarea {...register("description")} placeholder="Enter description" className='border w-full p-2'/>
            </div>
                <div className='flex items-center justify-center gap-5'>
                    <button type="button"
                            onClick={() => navigate(-1)}
                            className='bg-red-700 py-2 px-5 rounded font-semibold text-white hover:scale-105 active:scale-100 duration-500'>
                        Cancel
                    </button>
                    <button type="submit"
                        className='bg-green-700 py-2 px-5 rounded font-semibold text-white hover:scale-105 active:scale-100 duration-500'>
                        Save Changes
                    </button>
                </div>
            </form>
        </div>
    )
}
export default EditProduct