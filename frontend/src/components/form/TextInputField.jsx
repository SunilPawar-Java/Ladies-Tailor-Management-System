export const TextInputField = ({label, name, register, placeholder}) => (
    <div>
        <p>{label}</p>
        <input className='border w-full h-10 rounded pl-10 pr-1'
            type="text" {...register(name)} placeholder={placeholder} />
    </div>
)