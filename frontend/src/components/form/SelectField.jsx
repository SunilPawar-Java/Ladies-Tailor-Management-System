export const SelectFields = ({label, name, options, register}) => (
    <div>
        <p>{label}</p>
        <select {...register(name)} className='border text-center w-full h-10 rounded'>
            {options.map((option, index) => (
                <option key={index} value={option}>
                    {option}
                </option>
            ))}
        </select>
    </div>
);