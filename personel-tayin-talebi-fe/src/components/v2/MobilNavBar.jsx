import React from 'react'
import { useNavigate } from 'react-router-dom'
import { logout } from '../../services/auth/loginService';




const MobilNavBar = () => {

    const navigate = useNavigate();

    const handleLogout = async () => {
        await logout();
        navigate("/login");
      }
    


    return (
        <div className='fixed bottom-0 left-0 flex flex-row lg:hidden w-full items-center cursor-pointer bg-[#321f24] justify-center text-center'>
            <div className=' border-r-2 border-gray-300 text-white    w-full ' onClick={() => navigate("/calisma-listesi")}>Başvuru Listesi</div>
            <div className=' w-full text-white' onClick={() => handleLogout()}>Çıkış</div>
        </div>

    )

}
export default MobilNavBar;