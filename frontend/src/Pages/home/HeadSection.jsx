import React from 'react'

const HeadSection = () => {
    return (<>
        <div className='mt-15 sm:h-142 md:h-screen sm:mt-17 w-screen p-7 sm:p-15 md:pl-15 md:pr-15 lg:pl-15 lg:pr-15
         flex flex-col gap-15'>

                <div>
                    <h1 className='text-blue-950 font-mono font-extrabold text-3xl sm:text-6xl'>
                        Your
                        <span className='text-blue-600'> Design </span>
                        & Our scissor</h1>
                </div>
                <div className='sm:w-2/5 lg:mb-30 md:mb-10 sm:text-lg text-zinc-700'>
                    <p className=''>Where fashion meets precision.
                        We craft bespoke silhouettes designed to empower every woman,
                        blending timeless elegance with a flawless, custom fit.
                        Discover the luxury of a wardrobe made exclusively for you.
                    </p>
                </div>
                <div>
                    <button
                        className='text-white bg-blue-950 font-bold rounded-3xl py-2 px-5 text-sm hover:scale-105 active:scale-99 duration-500'>
                        Get Started
                    </button>
                </div>
        </div>
    </>)
}
export default HeadSection
