import React, { useState, useEffect } from 'react'
import { AppContext } from '../../context/AppContent'
import { useContext } from 'react'
import KullaniciTercihInfoCard from '@/components/v2/KullaniciTercihInfoCard'
import AdliyeAdListesi from '@/components/v2/AdliyeAdListesi'
import { formatDate } from '../../util/tarihformatla'
import YapilanTercihlerTable from '@/components/v2/YapilanTercihlerTable'
import { useNavigate } from 'react-router-dom'

const TercihteBulunmaSayfasi = () => {

  const navigate = useNavigate();

  const { basvuruTemelBilgileriContext } = useContext(AppContext);


  useEffect(() => {
    if(basvuruTemelBilgileriContext.length==0){
      navigate("/calisma-listesi");
    }
  }, []);

  return (
      <div className='    h-full  w-full flex flex-col gap-2  lg:p-4 px-2      '>





      <div className='   '>

        <KullaniciTercihInfoCard />

      </div>


      <div className=' flex-1lg:px-10 lg:py-10 2xl:px-50 2xl:py-10 rounded-lg  p-2'>

        <YapilanTercihlerTable />

      </div>



    </div>

  )
}

export default TercihteBulunmaSayfasi