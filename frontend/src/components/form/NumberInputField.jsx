export const NumberInputField = ({label, name, register, placeholder}) => (
    <div>
        <p> {label} </p>
        <input {...register(name)}
               className='border w-full h-10 rounded pl-10 pr-1'
               step="0.01"
               type="number"
               placeholder={`${placeholder}`}
        />
    </div>
);