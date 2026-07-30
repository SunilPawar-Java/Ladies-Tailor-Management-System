import React from 'react'
import notFoundImg from '../../assets/images/404.png';

const NotFound = () => {
    return (
        <div className='h-screen w-screen overflow-hidden'>
            <img className='h-full w-full object-cover'
                src={notFoundImg} alt="404" />
        </div>
    )
}
export default NotFound
