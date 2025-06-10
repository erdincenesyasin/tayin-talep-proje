import React, { useContext, useEffect, useState } from 'react'
import { AppContext } from '../../context/AppContent'
import { getBasvuruTemelBilgileri } from '../../services/tayintalep/basvuruTemelBilgilerService'

import { getTercihimVarMi } from '../../services/tayintalep/tayinTalebiService'
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

import { Badge } from "@/components/ui/badge"

import { Button } from '@/components/ui/button'

import Profil from '../Profil'
import {
    Collapsible,
    CollapsibleContent,
    CollapsibleTrigger,
} from "@/components/ui/collapsible"

import {
    ResizableHandle,
    ResizablePanel,
    ResizablePanelGroup,
} from "@/components/ui/resizable"

const YayinlanmisCalismalar = () => {

    const { tayinCalismalari, setTayinCalismalari, profilBilgileri, setProfilBilgileri} = useContext(AppContext);
    const {setBasvuruTemelBilgileriContext} = useContext(AppContext);
    const [calismSayisi, setCalismSayisi] = useState(0);
    const { uyariGoster, setUyariGoster,  uyariMesaji, setUyariMesaji} = useContext(AppContext);

    const navigate = useNavigate();


    useEffect(() => {
     

        getBasvuruTemelBilgileri().then(response => {
            setTayinCalismalari(response);
            setCalismSayisi(response.length);
        });
    }, []);


    function formatDate(dateStr) {
        if (!dateStr) return "";
        const d = new Date(dateStr);
        const gun = String(d.getDate()).padStart(2, "0");
        const ay = String(d.getMonth() + 1).padStart(2, "0");
        const yil = d.getFullYear();
        return `${gun}.${ay}.${yil}`;
    }




    const handleBasvuruYap = async (item) => {//item seçilen çalışma objesi
        // daha önceden bu çalışma için talepte bulunmuş mu? 
        //bunu ilk sayfa açılışınca kontrol edersek, başvur butonu yerine başvurunuz zaten var deriz.
        //burada kontrol edersek, butona bastıktan sonra başvurunuz var diye burada uyarı veririz.
        // aşırı trafik almayacağı için ilk sayfa açılışınca kontrol edebilir ??
        //ama şimdilik kolay olan butona basınca kontrol yapayım, vakit kalırsa sayfa açılışına taşırım.

        const response = await getTercihimVarMi(item.id);
        if (response.data) {

            setUyariMesaji("Bu çalışma için daha önceden tayin talebiniz bulunmaktadır. Tekrar talepte bulunmak için önceki talebi silmeniz gerekmektedir. ");
            setUyariGoster(true);
            
        } else {
           
            setBasvuruTemelBilgileriContext(item);
            navigate(`/yeni-talep`);
        }


    }

    return (
       


         <div className='py-2 px-12  m-0 w-full '>
                <h3 className='text-2xl font-bold'>Aktif Olan Başvuru Yapılabilecek Tayin Çalışmaları</h3>
                <Table>
                    <TableHeader>
                        <TableRow>
                            <TableHead >#</TableHead>
                            <TableHead>Çalışma Adı</TableHead>
                            <TableHead>Duyuru Linki</TableHead>
                            <TableHead >Tercih Sayısı</TableHead>
                            <TableHead > Başlangıç</TableHead>
                            <TableHead >  Bitiş</TableHead>
                            <TableHead >Tahmini Sonuç</TableHead>
                            <TableHead >İşlemler</TableHead>
                        </TableRow>
                    </TableHeader>
                    <TableBody>

                        {tayinCalismalari.map((item, index) => (
                            <TableRow key={index}>

                                <TableCell >{index + 1}</TableCell>
                                <TableCell>{item.tercihDonemiAciklamaMetniBasligi}</TableCell>
                                <TableCell>{item.duyuruMetniLinki}</TableCell>
                                <TableCell >{item.tercihMaxSayisi}</TableCell>
                                <TableCell >{formatDate(item.basvuruBaslangicTarihi)}</TableCell>
                                <TableCell ><Badge className="animate-pulse" variant="destructive">{formatDate(item.basvuruBitisTarihi)}</Badge></TableCell>
                                <TableCell >{formatDate(item.sonucAciklamaTarihi)}</TableCell>
                                <TableCell >  <Button onClick={() => handleBasvuruYap(item)} variant="destructive">Başvuru Yap</Button></TableCell>

                            </TableRow>


                        ))}

                      
                    </TableBody>
                </Table>
            </div>
        
    )
}

export default YayinlanmisCalismalar;