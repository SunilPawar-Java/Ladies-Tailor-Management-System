import ServiceSection from "../home/ServiceSection.jsx";
import OurMission from "./OurMission.jsx";
import WhyChooseUs from "./WhyChooseUs.jsx";
import OurStory from "./OurStory.jsx";

const About = () => {

    return (<>
        <div className='pt-18 flex flex-col sm:h-auto sm:mt-17 p-5 sm:gap-1 sm:p-10 md:pl-15 md:pr-15 lg:pl-15 lg:pr-15 gap-10 lg:gap-10'>
            <p className='text-blue-950 font-mono font-extrabold text-3xl md:text-5xl'>
                Crafting
                <span className='text-blue-600'> Perfect Fits  </span>
                with
                <span className='text-blue-600'> Precision </span>
                and
                <span className='text-blue-600'> Style </span>
            </p>
            <div className='w-full flex gap-3'>
                <div className='flex gap-5'>
                    <div className='sm:w-2/3'>
                        <p className="text-sm md:text-lg sm:pt-10 text-zinc-600 leading-relaxed font-normal max-w-2xl mx-auto lg:mx-0">
                            Welcome to Elegant Tailors, where every stitch reflects quality, creativity, and attention to detail.
                            We specialize in custom tailoring services for women, offering beautifully crafted kurtis, blouses, dresses,
                            and alterations designed to fit your unique style and measurements.
                        </p>
                    </div>
                    <div className='w-1/2 h-90 hidden rounded-lg sm:flex object-cover overflow-hidden'>
                        <img className='w-full h-70 sm:h-70 lg:h-full object-cover grayscale-15 brightness-110
                        transition-transform duration-500 ease-in-out hover:scale-110 hover:rotate-3 hover:grayscale-0'
                            src="/src/assets/images/owner.png" alt="shop owner image"/>
                    </div>
                </div>
            </div>
        </div>
        <OurStory />
        <OurMission />
        <ServiceSection />
        <WhyChooseUs />
    </>)
}

export default About