import React, { useEffect, useState } from 'react'
import AdliyeAdListesi from './AdliyeAdListesi'
import { Button } from '@/components/ui/button'
import { MdDelete } from "react-icons/md";
import Profil from '../../pages/Profil'

import { logout } from '../../services/auth/loginService';
import { useNavigate } from 'react-router-dom'





import {
  Table,
  TableBody,
  TableCaption,
  TableCell,
  TableHead,
  TableHeader,
  TableRow,
} from "@/components/ui/table"

const YapilanTercihlerTable = () => {

  const [tercihListesi, setTercihListesi] = useState([]);


  const navigate = useNavigate();


  const handleSil = (id) => {
    setTercihListesi(prev => prev.filter(tercih => tercih.id !== id));
  }


  const handleLogout = async () => {
    await logout();
    navigate("/login");
  }





  return (


    <div className=' lg:border-2 border-gray-300 flex md:flex-row flex-col gap-4 h-full lg:min-h-[400px] p-2 rounded-lg   '>

      <div className='relative items-center w-full'>
        <div className='lg:absolute  h-full w-full lg:top-[-40px] lg: left-[-60px]'>
          <Profil />
        </div>


      </div>

      <div className=' items-center py-2 w-full '>

        <AdliyeAdListesi tercihListesi={tercihListesi} setTercihListesi={setTercihListesi} />



      </div>




      <div className='  w-full overflow-y-auto border-2  border-gray-300 rounded-md '>
        <div className='max-h-[300px] w-full '>
          <Table className='w-full'>

            <TableHeader className='bg-gray-300 '>
              <TableRow>
                <TableHead className="w-1/16" > SIRA</TableHead>
                <TableHead className='w-1/2'>  ADLİYE </TableHead>
                <TableHead className='w-1/16'>..</TableHead>
              </TableRow>
            </TableHeader>
            <TableBody >
              {tercihListesi.map((tercih, index) => (
                <TableRow key={index}>
                  <TableCell className="font-medium">{index + 1}</TableCell>
                  <TableCell >{tercih.ad}</TableCell>
                  <TableCell className='w-1/16 '>
                    <MdDelete className='text-xl hover:text-red-500' onClick={() => handleSil(tercih.id)} />
                  </TableCell>

                </TableRow>
              ))}
            </TableBody>
          </Table>
        </div>
        <div className='fixed bg-amber-300 bottom-0 left-0 w-full flex flex-row lg:hidden items-center justify-center  z-50  '>
          <div>

          </div>
          <div className='flex flex-row w-full items-center cursor-pointer bg-[#321f24] justify-center text-center'>
            <div className=' border-r-2 border-gray-300 text-white    w-full ' onClick={() => navigate("/calisma-listesi")}>Başvuru Listesi</div>
            <div className=' w-full text-white' onClick={() => handleLogout()}>Çıkış</div>
          </div>
        </div>
      </div>
    </div>

  )
}

export default YapilanTercihlerTable;


