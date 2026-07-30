import SubmitButton from "../../components/buttons/SubmitButton.jsx";
import WhatsappButton from "../../components/buttons/WhatsappButton.jsx";
import InstagramButton from "../../components/buttons/InstagramButton.jsx";
import LocationButton from "../../components/buttons/LocationButton.jsx";

const ContactSection = () => {
    return (<>
        <div className='w-screen px-5 py-5 md:px-15 lg:px-15 flex flex-col gap-10 bg-white'>
            <h1 className='font-extrabold text-xl sm:text-2xl md:text-2xl'>Contact us</h1>
            <div className='flex flex-col gap-5 md:flex-row md:gap-25 lg:gap-50'>
                <form action="" method="post" className='text-white bg-blue-950 border-blue-950 rounded
                      p-10 md:w-1/2 flex flex-col items-center gap-5'>
                    <div>
                        <p>Enter Your Name*</p>
                        <input type="text" placeholder="Enter your name here"
                            className='outline-0 text-black px-1 md:w-60 bg-white sm:w-70 lg:w-80 rounded'
                        />
                    </div>
                    <div>
                        <p>Enter Your Gmail*</p>
                        <input type="gmail" placeholder="Enter your gmail here"
                               className='outline-0 text-black px-1 md:w-60 bg-white sm:w-70 lg:w-80 rounded'
                        />
                    </div>

                    <div>
                        <p>Description*</p>
                        <input type="text" placeholder="What's on your mind? ask anything..."
                               className='outline-0 text-black px-1 md:w-60 bg-white sm:w-70 lg:w-80 rounded h-15'
                        />
                    </div>

                    <div>
                        <SubmitButton className=''/>
                    </div>
                </form>
                <div className='md:w-1/3 gap-5 lg:w-1/4 flex flex-col items-center justify-center'>
                    <WhatsappButton/>
                    <InstagramButton/>
                    <LocationButton/>
                </div>
            </div>
        </div>
    </>)
}
export default ContactSection
