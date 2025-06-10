import { useState, useEffect, useContext } from "react";
import { Table, TableBody, TableCell, TableHead, TableHeader, TableRow } from "@/components/ui/table"
import { Badge } from "@/components/ui/badge"
import { Button } from "@/components/ui/button"
import AdminKlavuzModal from "@/components/AdminKlavuzModal";
import { AppContext } from "@/context/AppContent";
import { useNavigate } from "react-router-dom";
import { getTumTercihIslemleriniGetir, onaylaTercihIslemi, yayinlaTercihIslemi } from "@/services/admin/adminIslemleriService";
import { formatDate } from '../../util/tarihformatla'
import { getTumBasvurular } from "@/services/admin/adminIslemleriService";
import { Drawer, DrawerContent, DrawerHeader, DrawerTitle, DrawerDescription, DrawerFooter, DrawerTrigger, DrawerClose } from "@/components/ui/drawer";


import {
  AlertDialog,
  AlertDialogAction,
  AlertDialogCancel,
  AlertDialogContent,
  AlertDialogDescription,
  AlertDialogFooter,
  AlertDialogHeader,
  AlertDialogTitle,
  AlertDialogTrigger,
} from "@/components/ui/alert-dialog"




const Admin = () => {

  const [open, setOpen] = useState(false);
  const onClose = () => setOpen(false);
  const [sayfa, setSayfa] = useState(1);
  const [yenile, setYenile] = useState(false); // veriyi hep güncel tutmak için create yapınca vt den tüm listeyi tekrar getirecem.
  const { profilBilgileri } = useContext(AppContext);
  const [adminYetkiliMi, setAdminYetkiliMi] = useState(false);
  const [tumTercihIslemleriListesi, setTumTercihIslemleriListesi] = useState([]);
  const [cekilecekBasvuruID, setCekilecekBasvuruID] = useState(null);
  const [cekilecekBasvuruTalepTuru, setCekilecekBasvuruTalepTuru] = useState(0);
  const [basvurularModalOpen, setBasvurularModalOpen] = useState(false);
  const { uyariMesaji, setUyariMesaji, uyariGoster, setUyariGoster } = useContext(AppContext);


  const navigate = useNavigate();
  useEffect(() => {
   
    if (!profilBilgileri || profilBilgileri.role == "USER") {
      navigate("/");
      setAdminYetkiliMi(false);
      return;
    }
    setAdminYetkiliMi(true);
    const tumTercihIslemleriniGetir = async () => {
      const response = await getTumTercihIslemleriniGetir();
      
      setTumTercihIslemleriListesi(response);
    };
    tumTercihIslemleriniGetir();
  }, []);


  useEffect(() => {
    const tumTercihIslemleriniGetir = async () => {
      const response = await getTumTercihIslemleriniGetir();
   
      setTumTercihIslemleriListesi(response);
    }
    tumTercihIslemleriniGetir();
  }, [yenile]);


  const tumBasvurulariGetir = (row) => {
 
    setCekilecekBasvuruID(row.id);
    setCekilecekBasvuruTalepTuru(row.tercihDonemiAciklamaMetniBasligi);
    setBasvurularModalOpen(true);
    setOpen(true);
  }

  const handleTercihIsleminiOnayla = async (row) => {
  

    try {
      const response = await onaylaTercihIslemi(row.id);
    
      setYenile(!yenile);
    } catch (error) {
    
      setUyariMesaji("Onay işleminde hata oluştu");
      setUyariGoster(true);
    }
  }

  const handleTercihIsleminiYayinla = async (row) => {

    try {
      const response = await yayinlaTercihIslemi(row.id);
    
      setYenile(!yenile);
    } catch (error) {
      
      setUyariMesaji("Yayın işleminde hata oluştu");
      setUyariGoster(true);
    }
  }



  const yayinlaTableRow = (tercih) => {
    if (tercih.isActive) {
      return <Badge size="sm" variant="green">Yayında</Badge>
    } else {
      return (<Badge size="sm" variant="red">Yayında Değil</Badge>
      )
    }
  }

  const onaylaTableRow = (tercih) => {
    if (tercih.isOnaylandi) {
      return <Badge size="sm" variant="green">Onaylandı</Badge>
    } else {
      return <Badge size="sm" variant="red">Onay Bekliyor</Badge>
    }
  }


  const islemlerRowTable = (tercih) => {


    if (tercih.isActive) {
      return (

        <Button variant="table_icinde_kucuk" size="sm" onClick={() => tumBasvurulariGetir(tercih)}> BAŞVURULARI  GÖRÜNTÜLE</Button>
      )
    }
    else if (tercih.isOnaylandi && !tercih.isActive) {
      return (


        <AlertDialog>
          <AlertDialogTrigger > <Badge variant="gulrengi" size="sm" >YAYINA AL</Badge></AlertDialogTrigger>
          <AlertDialogContent>
            <AlertDialogHeader>
              <AlertDialogTitle> YAYINLAMAK İSTEDİĞİNİZİZ EMİN MİSİNİZ? </AlertDialogTitle>
              <AlertDialogDescription>
                Personel tercihte bulunmaya başlayacaktır.

              </AlertDialogDescription>
            </AlertDialogHeader>
            <AlertDialogFooter>
              <AlertDialogCancel>İptal</AlertDialogCancel>
              <AlertDialogAction onClick={() => handleTercihIsleminiYayinla(tercih)}>YAYINA AL</AlertDialogAction>
            </AlertDialogFooter>
          </AlertDialogContent>
        </AlertDialog>



      )
    } else if (!tercih.isOnaylandi && (profilBilgileri.role == "GNLMDR" || profilBilgileri.role == "GNLMDRYRD" || profilBilgileri.role == "TESTADMIN")) {
      return (

        <AlertDialog>
          <AlertDialogTrigger > <Badge variant="gulrengi" size="sm" >ONAYLA</Badge></AlertDialogTrigger>
          <AlertDialogContent>
            <AlertDialogHeader>
              <AlertDialogTitle> NAKİL ÇALIŞMASI İÇİN ONAY vermek istediğinize  emin misiniz? </AlertDialogTitle>
              <AlertDialogDescription>
                Onay işleminde sonra herkes yayına alabilir?

              </AlertDialogDescription>
            </AlertDialogHeader>
            <AlertDialogFooter>
              <AlertDialogCancel>İptal</AlertDialogCancel>
              <AlertDialogAction onClick={() => handleTercihIsleminiOnayla(tercih)}>ONAYLA</AlertDialogAction>
            </AlertDialogFooter>
          </AlertDialogContent>
        </AlertDialog>




      )
    } else {
      return (

        <Badge variant="red" size="sm"> Amir Onayı Bekliyor</Badge>

      )

    }

  }



  //*************************başvuraları çekmek için */



  const [requestPage, setRequestPage] = useState(0);
  const [requestSize, setRequestSize] = useState(100);// backende kontrol var. 100 den büyük sayı 100 çekiliyor.



  const [content, setContent] = useState([]);
  const [totalPages, setTotalPages] = useState(1);
  const [sayfaNumarasi, setSayfaNumarasi] = useState(1);
  const [vericekiliyor, setVericekiliyor] = useState(false);

  const [responsePageSize, setResponsePageSize] = useState();
  const [responsePageNumber, setResponsePageNumber] = useState(0);
  const [gelenVeri, setGelenVeri] = useState([]);

  useEffect(() => {
    const tumBasvurulariGetir = async () => {
      setVericekiliyor(true);
      if (totalPages < requestPage || cekilecekBasvuruID < 1) {

        return;
      }
      const response = await getTumBasvurular(cekilecekBasvuruID, requestPage, requestSize);
    
      setGelenVeri(response.content);
      setTotalPages(response.totalPages);
      setVericekiliyor(false);

    }
    tumBasvurulariGetir();

  }, [requestPage, cekilecekBasvuruID]);


  const sayfaSeciciSelect = () => {
    return (
      <select className="border-2 border-gray-300 rounded-md text-sm p-1 max-w-xs  " onChange={(e) => {
        setRequestPage(e.target.value)
        setResponsePageNumber(e.target.value)
      }}>
        {Array.from({ length: totalPages }, (_, i) => (
          <option key={i} value={i}>{i + 1}</option>
        ))}
      </select>
    )
  }




  return (
    <>
      {!adminYetkiliMi ? (<div> Admin yetkisi gereken bir sayfadasınız. Yetkiniz yok.</div>) : (<div className="lg:px-6 px-2"> <h1 className='text-2xl font-bold'><AdminKlavuzModal /></h1>

        <Table className="lg:table-fixed">
          <TableHeader>
            <TableRow className="bg-gray-200 ">
              <TableHead className="w-1/48 " >#</TableHead>
              <TableHead className="w-2/12">Nakil Çalışması Adı</TableHead>
              <TableHead className="w-1/16"> Talep Hakkı</TableHead>
              <TableHead className="w-1/48 p-0 whitespace-normal">Min Max Tercih Sayısı</TableHead>
             
              <TableHead className="w-1/18">Başlangıç</TableHead>
              <TableHead className="w-1/18">Bitiş</TableHead>
              <TableHead className="w-1/18"> Sonuç</TableHead>
              <TableHead className="w-1/18">ONAYLANDI MI?</TableHead>
              <TableHead className="w-1/18">YAYINDA MI?</TableHead>

              <TableHead className="w-1/18">İşlemler</TableHead>

            </TableRow>
          </TableHeader>
          <TableBody>
            {tumTercihIslemleriListesi.map((tercih, index) => (
              <TableRow className="h-14 " key={index}>
                <TableCell>{index + 1}</TableCell>
                <TableCell >{tercih.tercihDonemiAciklamaMetniBasligi}                 </TableCell>
                <TableCell> {tercih.talepHakSayisi<0 ? "SINIRSIZ" : tercih.talepHakSayisi}</TableCell>
                <TableCell>{tercih.tercihMinSayisi}-{tercih.tercihMaxSayisi}</TableCell>

                <TableCell> {formatDate(tercih.basvuruBaslangicTarihi)}</TableCell>
                <TableCell> {formatDate(tercih.basvuruBitisTarihi)}</TableCell>
                <TableCell> {formatDate(tercih.sonucAciklamaTarihi)}</TableCell>
                <TableCell >{onaylaTableRow(tercih)}</TableCell>
                <TableCell>{yayinlaTableRow(tercih)}</TableCell>
                <TableCell className="text-center"> {islemlerRowTable(tercih)}</TableCell>

              </TableRow>
            ))}



          </TableBody>
        </Table>

      </div>)}


      <div>

        <Drawer open={open} onOpenChange={onClose}>

          <DrawerContent>
            <DrawerHeader>
              <DrawerTitle>{cekilecekBasvuruTalepTuru}</DrawerTitle>
              <DrawerDescription>  Bu çalışma için yapılan tüm basvurular     {sayfaSeciciSelect()}   </DrawerDescription>
            </DrawerHeader>
            <DrawerFooter>
              <div className="flex flex-col items-center justify-center">
                <div className="h-[400px]   flex lg:w-3/4 w-full justify-center overflow-y-auto">
                  <Table>
                    <TableHeader >
                      <TableRow>
                        <TableHead className="w-1/49">#</TableHead>
                        <TableHead className="w-4/49">Sicil No</TableHead>

                        <TableHead className="w-10/49">Adı Soyadı</TableHead>
                        <TableHead className="w-10/49">Ünvanı</TableHead>
                        <TableHead className="w-5/49">Talep Tarihi</TableHead>
                        <TableHead >Tercih Listesi</TableHead>

                      </TableRow>
                    </TableHeader>


                    <TableBody>
                      {gelenVeri.length > 0 ? gelenVeri.map((item, index) => (
                        <TableRow key={index}>
                          <TableCell>{index + 1}</TableCell>
                          <TableCell>{item.personel.sicilNo}</TableCell>
                          <TableCell>{item.personel.ad} {item.personel.soyad}</TableCell>
                          <TableCell>{item.personel.unvan}</TableCell>

                          <TableCell>{formatDate(item.tayinTalebi.createdAt)}</TableCell>
                          <TableCell>  <div className="grid grid-flow-col  grid-rows-5 gap-2 w-full ">

                            {item.tercihListesi.length > 10 ? <span className="text-red-500 w-max-20 break-words">Tercih listesi 11'den fazla tercih içeriyor. TAmamını görmek için tıklayınız </span> : item.tercihListesi.map((t, i) => <p variant="outline" className="mr-2" key={i}>{t.sira}.  {t.adliye.ad}</p>)}
                          </div>
                          </TableCell>
                          <TableCell>

                          </TableCell>
                        </TableRow>
                      ))
                        : (<TableRow>
                          <TableCell colSpan={6} className="text-center">
                            <span className="text-red-500">Bu çalışma için yapılan basvurular bulunamadı</span>
                          </TableCell>
                        </TableRow>)
                      }
                    </TableBody>
                  </Table>
                </div>
              </div>



              <DrawerClose asChild>
                <Button onClick={onClose} variant="outline">Kapat</Button>
              </DrawerClose>
            </DrawerFooter>
          </DrawerContent>
        </Drawer>

        <div className=" fixed hidden lg:flex bottom-10 right-10  flex justify-center items-center">
          <Button onClick={() => {
            navigate("/admin-yeni-tayin");
          }}>YENİ KAYIT GİRİŞ</Button>
        </div>

      </div>






    </>
  )
}

export default Admin;