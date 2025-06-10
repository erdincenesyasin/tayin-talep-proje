import React from 'react'
import { Badge } from '@/components/ui/badge'
import { AppContext } from '../../context/AppContent'
import { useContext } from 'react'
import { formatDate } from '../../util/tarihformatla'
import {
  Card,
  CardAction,
  CardContent,
  CardDescription,
  CardFooter,
  CardHeader,
  CardTitle,
} from "@/components/ui/card"
import {
  Table,
  TableBody,
  TableCaption,
  TableCell,
  TableHead,
  TableHeader,
  TableRow,
} from "@/components/ui/table"
const KullaniciTercihInfoCard = () => {
  const { basvuruTemelBilgileriContext } = useContext(AppContext);

  return (
    <div className='lg:p-2  w-full h-full'>
      <div className='flex flex-row   lg:hidden rounded-lg  gap-4'>

        <Table>

          <TableBody>
            <TableRow>
              <TableCell>Başlama </TableCell>
              <TableCell>{formatDate(basvuruTemelBilgileriContext.basvuruBaslangicTarihi)}</TableCell>
              <TableCell>  {basvuruTemelBilgileriContext.tercihMaxSayisi}  tercih yapılabilir.</TableCell>
            </TableRow>
            <TableRow>
              <TableCell>Bitiş </TableCell>
              <TableCell>{formatDate(basvuruTemelBilgileriContext.basvuruBitisTarihi)}</TableCell>
              <TableCell>UYAP dışı başvuru yapılamaz.</TableCell>
            </TableRow>
            <TableRow>
              <TableCell>Açıklama </TableCell>
              <TableCell>{formatDate(basvuruTemelBilgileriContext.sonucAciklamaTarihi)}</TableCell>
              <TableCell>Tahmini  açıklama tarihidir.</TableCell>
            </TableRow>
          </TableBody>
        </Table>
      </div>



      <div className=' hidden  lg:flex lg:items-stretch  lg:justify-center lg:gap-4  w-full   '>
      
          <Card className='w-full  max-w-sm text-[#321f24]  bg-white shadow shadow-black/20  '>
            <CardHeader>
              <CardTitle> Başlama Tarihi</CardTitle>
              <CardAction>{formatDate(basvuruTemelBilgileriContext.basvuruBaslangicTarihi)}</CardAction>

            </CardHeader>
            <CardContent>
              <p>En fazla {basvuruTemelBilgileriContext.tercihMaxSayisi}  tercih yapabilirsiniz.</p>
            </CardContent>

          </Card>
        

        <Card className='w-full max-w-sm  text-[#321f24]  bg-white shadow shadow-black/20  '>
          <CardHeader>
            <CardTitle> Bitiş Tarihi</CardTitle>
            <CardAction > <Badge  className=" bg-[#c9a3a8] text-[#321f24] animate-pulse px-2  ">Son Gün: {formatDate(basvuruTemelBilgileriContext.basvuruBitisTarihi)}</Badge></CardAction>
          </CardHeader>
          <CardContent>
            <p>UYAP dışı başvuru yapılamaz.</p>
          </CardContent>

        </Card>
        <Card className='w-full max-w-sm  text-[#321f24]  bg-white  shadow shadow-black/20 '>
          <CardHeader>
            <CardTitle>Açıklanma Tarihi</CardTitle>
            <CardAction>{formatDate(basvuruTemelBilgileriContext.sonucAciklamaTarihi)}</CardAction>
          </CardHeader>
          <CardContent>
            <p>Tahmini  açıklama tarihidir.</p>
          </CardContent>

        </Card>
      </div>
    </div>

  )
}

export default KullaniciTercihInfoCard