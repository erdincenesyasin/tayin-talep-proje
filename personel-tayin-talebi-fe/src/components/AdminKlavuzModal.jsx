import React, { useState, useEffect } from 'react';
import { Card, CardHeader, CardTitle, CardContent, CardAction } from '@/components/ui/card';
import { Badge } from '@/components/ui/badge';


const AdminKlavuzModal = () => {

    const [gosterCard1, setGosterCard1] = useState(true);
    const [gosterCard2, setGosterCard2] = useState(false);
    const [gosterCard3, setGosterCard3] = useState(false);

useEffect(() => {
    setGosterCard1(true);
    setGosterCard2(false);
    setGosterCard3(false);
}, []);

    const handleGosterCard1 = () => {
        setGosterCard1(true);
        setGosterCard2(false);
        setGosterCard3(false);
    }

    const handleGosterCard2 = () => {
        setGosterCard2(true);
        setGosterCard1(false);
        setGosterCard3(false);
    }

    const handleGosterCard3 = () => {
        setGosterCard3(true);
        setGosterCard1(false);
        setGosterCard2(false);
    }
  return (
    <div className='flex flex-col items-center justify-center py-2 px-6'>
        <div>YENİ BİR NAKİL ÇALIŞMASI BAŞLATMA KILAVUZU</div>


<div className='flex flex-wrap gap-2 px-4 lg:gap-8 items-center justify-center py-2 px-6'>
        

       
        <Card className= {      ` ${gosterCard1 ? '' : 'hidden'} 2xl:block relative  w-full max-w-sm text-[#321f24]  bg-white shadow shadow-black/20   `} onClick={handleGosterCard2}>
          <CardHeader>
            <CardTitle> Yeni  Çalışma Oluştur</CardTitle>
            <CardAction > <span className="flex items-center justify-center w-8 h-8 border border-gray-600 rounded-full shrink-0 dark:border-blue-500">1</span></CardAction>
          </CardHeader>
          <CardContent className="text-md">
          
            Tarihler ve diğer kriterleri  belirleyiniz. 
            <Badge variant="outline"  size="sm" >YETKİ : SBMEMUR ve daha üst düzey yönetici rollerindedir.</Badge>
       
          </CardContent>
          <Badge className='absolute top-0 right-1/2 lg:hidden' variant="outline"  size="sm" >Sonraki Adım için Tıklayınız</Badge>
        </Card>
        
    <Card className ={ ` ${gosterCard2 ? '' : 'hidden'}  2xl:block w-full max-w-sm   text-[#321f24]  bg-white shadow shadow-black/20`}  onClick={handleGosterCard3}>
          <CardHeader>
            <CardTitle> Onay</CardTitle>
            <CardAction > <span className="flex items-center justify-center w-8 h-8 border border-gray-600 rounded-full shrink-0 dark:border-blue-500">2</span></CardAction>
          </CardHeader>
          <CardContent>
          
            Kayıttan sonra onayı gerekmektedir.
            <Badge variant="outline"  size="sm" >YETKİ : GNLMDRYRD ve daha üst düzey yönetici rollerinde</Badge>
          </CardContent>

        </Card>

    <Card className={ ` ${gosterCard3 ? '' : 'hidden'}  2xl:block w-full max-w-sm   text-[#321f24]  bg-white shadow shadow-black/20`} onClick={handleGosterCard1}>
          <CardHeader>
            <CardTitle>Personele Açılması</CardTitle>
            <CardAction > <span className="flex items-center justify-center w-8 h-8 border border-gray-600 rounded-full shrink-0 dark:border-blue-500">3</span></CardAction>
          </CardHeader>
          <CardContent>
          
            Onay sonrası aktif edilmesi gerekmektedir.
            <Badge variant="outline"  size="sm" >YETKİ : Şube Müdürü ve daha üst düzey yönetici rollerinde</Badge>
          </CardContent>

        </Card>

</div>


    </div>
  );
};

export default AdminKlavuzModal;
