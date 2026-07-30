import React, {useState} from 'react'

const OurMission = () => {
    const [ourMission, setOurMission] = useState([
        {
            id: 1,
            head: "The Premium & High-Fashion",
            image: "src/assets/images/mission1.png",
            text: "To elevate the modern woman’s wardrobe through precision tailoring and timeless design. " +
                "Our mission is to deliver flawless, made-to-measure craftsmanship that respects individual style, " +
                "ensuring every client experiences luxury comfort in garments built to last."
        }, {
            id: 2,
            head: "The Size-Inclusive & Solution-Oriented",
            image: "src/assets/images/mission2.png",
            text: "To end the frustration of off-the-rack sizing by making custom-fit clothing accessible to all women. " +
                "We are dedicated to creating garments that adapt perfectly to " +
                "your unique body—not the other way around—delivering comfort without sacrificing style."
        }, {
            id: 5,
            head: "Reimagining the Luxury of Custom Fit.",
            image: "src/assets/images/mission5.png",
            text: "We bring the boutique tailoring experience\n" +
                "directly to your screen.\n" +
                "No more multiple trips for fittings or settling for standard off-the-rack sizes. We design\n" +
                "custom-fit ethnic,\n" +
                "fusion, and western wear tailored exclusively to your unique measurements."
        }
    ]);
    return (<>
        <div className='px-2 sm:px-10 lg:space-y-10'>
            <p className='text-lg lg:text-2xl font-extrabold sm:text-xl sm:text-2xl md:text-2xl'> Our Mission </p>

            {
                ourMission.map((mission, idx) => {
                    return <div key={mission.id} className='flex flex-col items-center justify-center py-3 sm:flex-row sm:gap-20 md:px-15'>
                        <div className={'sm:w-1/2 sm:flex items-center justify-center' + `${idx % 2 !== 0 ? 'order-2 sm:order-1' : ''}`}>
                            <div className='h-50 w-60 lg:w-100 md:w-80 sm:h-1/2 overflow-hidden object-cover rounded shadow-black shadow-md'>
                                <img className='h-50 w-60 sm:h-70 sm:w-70 md:w-80 lg:h-100 lg:w-100 object-cover
                                    grayscale-15 brightness-110 transition-transform duration-500 ease-in-out hover:scale-110 hover:rotate-0 hover:grayscale-0'
                                     src={mission.image} alt="our mission"
                                />
                            </div>
                        </div>

                        <div className={'p-2 h-1/2 text-sm sm:w-200 md:w-1/2 sm:text-lg flex flex-col items-center justify-center' +
                                `${idx % 2 !== 1 ? 'order-1 sm:order-2' : ''}`}>

                            <p className='font-bold'><span>{mission.head}</span> </p>
                            <p className='lg:text-lg lg:leading-7 text-zinc-600'>
                                {mission.text}
                            </p>
                        </div>
                    </div>
                })
            }
        </div>
    </>)
}
export default OurMission