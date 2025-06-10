import React, { useEffect, useState } from "react";
import { logout } from "@/services/auth/loginService";
import { useNavigate } from "react-router-dom";

const Logout = () => {

    const navigate = useNavigate();
    useEffect(() => {
       
            logout();
          
            navigate("/giris");
   
    }, []);

    return (
        <div className="flex flex-col items-center justify-center h-screen">
            <button onClick={() => setCikisYap(true)}>Çıkış Yap</button>
        </div>
    )
}

export default Logout;  