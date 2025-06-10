import React, { useContext, useEffect, useState } from 'react'
import { getAdliyeIsimleri } from '../../services/tayintalep/adliyeIsimleriService'
import { AppContext } from '../../context/AppContent'
import { Input } from '@/components/ui/input'
import { Textarea } from "@/components/ui/textarea"
import { Badge } from "@/components/ui/badge"
import { Button } from "@/components/ui/button"
import { createTayinTalebi } from '../../services/tayintalep/tayinTalebiService'
import { Table, TableBody, TableCell, TableHead, TableHeader, TableRow } from "@/components/ui/table"
import EskiTaleplerTable from './EskiTaleplerTable'


import {
    Drawer,
    DrawerClose,
    DrawerContent,
    DrawerDescription,
    DrawerFooter,
    DrawerHeader,
    DrawerTitle,
    DrawerTrigger,
} from "@/components/ui/drawer"

import { toast } from "sonner"

const AdliyeAdListesi = ({ tercihListesi, setTercihListesi }) => {


    const [adliyeIsimleri, setAdliyeIsimleri] = useState([]);
    const [secilenAdliye, setSecilenAdliye] = useState({ id: "", ad: "" });
    const [aciklamaUzunlugu, setAciklamaUzunlugu] = useState(0);
    const [aciklama, setAciklama] = useState("");

    const { basvuruTemelBilgileriContext } = useContext(AppContext);
    const { uyariGoster, setUyariGoster, uyariMesaji, setUyariMesaji } = useContext(AppContext);

    useEffect(() => {
        const loadAdliyeIsimleri = async () => {
            const isimler = await getAdliyeIsimleri();
            setAdliyeIsimleri(isimler);
        };
        loadAdliyeIsimleri();
    }, []);

    const handleChange = (e) => {
        if (e.target.value === "") {
            return;
        }
       
        const secilenId = e.target.value;

        if (tercihListesi.length >= basvuruTemelBilgileriContext.tercihMaxSayisi) {
            setUyariMesaji("En fazla " + basvuruTemelBilgileriContext.tercihMaxSayisi + " adliye seçebilirsiniz");
            setUyariGoster(true);
            return;
        }

        const listedeVarMi = tercihListesi.find(a => String(a.id) === String(secilenId));
        if (listedeVarMi) {
            setUyariMesaji("Bu adliye zaten listede var");
            setUyariGoster(true);
            return;
        }
        const arananAdliye = adliyeIsimleri.find(a => String(a.id) === String(secilenId));
       
        setTercihListesi(prev => [...prev, arananAdliye]);
        setSecilenAdliye(arananAdliye);
    };





    const handleTercihListesiniKaydet = async () => {

        //javada da kontrol var zaten.. 
        if (tercihListesi.length === 0) {
          
            setUyariMesaji("En az bir adliye seçiniz");
            setUyariGoster(true);
            return;
        }
        if (aciklama.length === 0) {
            setUyariMesaji("Açıklama alanını boş bırakamazsınız");
            setUyariGoster(true);
            return;
        }

        const tercihList = tercihListesi.map((tercih, index) => ({
            adliye: { id: tercih.id },
            sira: index + 1
        }));

        const yeniTercih = {
            aciklama: aciklama,
            tercihList: tercihList,

            adminTercihIslemleri: { id: basvuruTemelBilgileriContext.id }
        };


        try {
            const response = await createTayinTalebi(yeniTercih);
            setUyariMesaji("Başvurunuz başarıyla kaydedildi");
            setUyariGoster(true);


        } catch (error) {

            setUyariMesaji(error.message);
            setUyariGoster(true);

        }





    };




    return (
        <div className='flex flex-col gap-2 items-center justify-center  w-full h-full '>
            <div className=''>
                <select className=' border-2 border-gray-300 rounded-md text-sm p-1 max-w-xs ' onChange={handleChange}>
                    <option value="">Adliye Seçiniz</option>
                    {adliyeIsimleri && adliyeIsimleri.map((adliye) => (
                        <option key={adliye.id} value={adliye.id}>{adliye.ad}</option>
                    ))}
                </select>
            </div>

            <div className='   flex flex-col w-full h-full   justify-end items-end'>
                <Textarea className='w-full h-full flex-1 bg-[#faf6f6] ' onChange={(e) => { setAciklamaUzunlugu(e.target.value.length); setAciklama(e.target.value); }} type="text" placeholder='Açıklama giriniz. En fazla 500 karakter' />

                <Badge variant="secondary" className='text-xs    text-gray-700'>Kalan Karakter {500 - aciklamaUzunlugu}</Badge>
            </div>
            <div className=' mt-4 w-full'>
                <div className='text-xs h-full  bg-[#ecdfe0] rounded-md p-2 text-black'>{basvuruTemelBilgileriContext.tercihDonemiAciklamaMetniBasligi} kapsamında başvuruda bulunmaktasınız.</div>
            </div>
            <div className='flex flex-row gap-2'>

                <Drawer>
                    <DrawerTrigger className='bg-black text-xs lg:text-sm   text-white rounded-md lg:px-2 lg:py-1 p-0.5 lg:px-4  hover:bg-[#5e3d47] '>Önceki Taleplerim</DrawerTrigger>
                    <DrawerContent>
                        <DrawerHeader>
                            <DrawerTitle >Önceki Taleplerim</DrawerTitle>
                            <DrawerDescription></DrawerDescription>
                        </DrawerHeader>
                        <div className=' flex flex-col items-center lg:justify-center max-h-[300px]   overflow-y-auto'>
                            <EskiTaleplerTable />
                        </div>

                        <DrawerFooter>

                            <DrawerClose asChild>
                                <div className='flex flex-row items-center justify-center gap-2'>
                                    <Button variant="outline">Kapat</Button>
                                </div>
                            </DrawerClose>
                        </DrawerFooter>
                    </DrawerContent>
                </Drawer>

                <Drawer>
                    <DrawerTrigger className='bg-black text-white rounded-md lg:px-2 lg:py-1 lg:px-4  text-xs lg:text-sm px-2  hover:bg-[#5e3d47] '>Kaydet</DrawerTrigger>
                    <DrawerContent>
                        <DrawerHeader>
                            <DrawerTitle>Tercihlerinizi onaylıyor musunuz?</DrawerTitle>
                            <DrawerTitle>{basvuruTemelBilgileriContext.tercihDonemiAciklamaMetniBasligi} kapsamında başvuruda bulunmaktasınız.</DrawerTitle>
                            <DrawerDescription>

                            </DrawerDescription>
                            <div className='flex flex-col items-center justify-center'>
                                <div className='max-h-[300px] flex  lg:w-1/4 w-full  overflow-y-auto  '>
                                    <Table className='w-full'>
                                        <TableHeader>
                                            <TableRow>
                                                <TableHead >Sıra</TableHead>
                                                <TableHead >Adliye</TableHead>

                                            </TableRow>
                                            {tercihListesi.map((tercih, i) => (
                                                <TableRow key={i}>

                                                    <TableCell className='text-left'>{i + 1}</TableCell>
                                                    <TableCell className='text-left'>{tercih.ad}</TableCell>

                                                </TableRow>
                                            ))}
                                        </TableHeader>
                                    </Table>
                                </div>
                            </div>
                        </DrawerHeader>
                        <DrawerFooter >
                            <div className='flex flex-row items-center justify-center gap-2'>
                                <Button onClick={handleTercihListesiniKaydet} >Tercihlerimi Onaylıyorum Gönder</Button>
                                <DrawerClose asChild>
                                    <Button variant="outline">İptal</Button>
                                </DrawerClose>
                            </div>


                        </DrawerFooter>
                    </DrawerContent>
                </Drawer>


            </div>
        </div>
    )
}

export default AdliyeAdListesi