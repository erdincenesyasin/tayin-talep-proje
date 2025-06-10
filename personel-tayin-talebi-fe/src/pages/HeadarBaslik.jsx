import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import { IoIosArrowForward } from "react-icons/io";
import { logout } from '../services/auth/loginService';


import logo from '../assets/logo.png';
import {
  Breadcrumb,
  BreadcrumbItem,
  BreadcrumbLink,
  BreadcrumbList,
  BreadcrumbPage,
  BreadcrumbSeparator,
} from "@/components/ui/breadcrumb"
import { IoIosLogOut } from "react-icons/io";
import { useNavigate } from 'react-router-dom';
import { Badge } from '@/components/ui/badge';

const Header = () => {
  const navigate = useNavigate();
  const handleLogout = async () => {
    await logout();
    navigate("/login");
  }

  return (
    <div className='lg:pr-8 pr-0.5 py-2 flex flex-row justify-between items-center  w-full bg-[#321f24] '>
      <div className="  flex flex-row items-center rounded-lg mx-4 ">
        <img src={logo} alt="logo" className="w-10 h-10" />
        <span className='text-white font-medium px-1 py-2 rounded-lg  transition-colors'>Personel Genel Müdürlüğü</span>
      </div>

      <div className='   p-2     hidden lg:flex    bg-[#321f24]  justify-end items-end   gap-2'>
        <div className="flex flex-row gap-2 text-2xl cursor-pointer w-1/2 lg:w-auto justify-center items-center">
          <Badge variant="outline" onClick={() => navigate("/calisma-listesi")} className='text-white'>Başvuru Listesi
          </Badge>
        </div>

        {/*çıkış butonu*/}

        <div className="flex flex-row gap-2 text-2xl cursor-pointer w-1/2 lg:w-auto justify-center items-center">
          <Badge variant="outline" onClick={() => handleLogout()} className='text-white'>Çıkış Yap
            <IoIosLogOut className='text-white' /></Badge>


        </div>

      </div>
    </div>

  );
};

export default Header;