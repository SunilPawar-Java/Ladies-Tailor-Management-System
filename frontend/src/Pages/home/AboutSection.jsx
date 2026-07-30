const AboutSection = () => {
    return (
        <div className='w-screen px-5 py-5 md:px-15 lg:px-15 flex flex-col gap-10 bg-white'>
            <h1 className='font-extrabold text-xl sm:text-2xl md:text-2xl'>About us</h1>

            <div className='font-mono'>
                <p>We believe fashion should be as kind to the planet as it is to your wardrobe.
                    Our curated collections are ethically made for the conscious woman.
                </p><br/>

                <p>Founded in a small home studio, [Ladies Tailor] was born from a search for the perfect linen dress.
                    Today, we bring that same love for quality to you.
                </p><br/>

                <p className='font-bold text-sm text-blue-950'>Trend-led designs, premium fabrics, and a fit that feels like it was made for you.
                    Discover the [Brand Name] difference.
                </p> <br/>
            </div>
        </div>
    )
}
export default AboutSection