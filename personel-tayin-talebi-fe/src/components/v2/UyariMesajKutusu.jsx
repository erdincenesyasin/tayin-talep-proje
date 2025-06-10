import React, { useContext } from 'react'
import {
  AlertDialog,
  AlertDialogCancel,
  AlertDialogContent,
  AlertDialogDescription,
  AlertDialogFooter,
  AlertDialogHeader,
  AlertDialogTitle,
} from "@/components/ui/alert-dialog"
import { AppContext } from '../../context/AppContent'

const UyariMesajKutusu = () => { /// TÜM MESAJLARI BUNUNLA VERECEĞİZ.
  const {  uyariGoster, setUyariGoster, uyariMesaji, setUyariMesaji } = useContext(AppContext);

  return (
    <div className='flex flex-col items-center justify-center'>
      {uyariGoster && (
        <AlertDialog open={uyariGoster} onOpenChange={setUyariGoster}>
          <AlertDialogContent>
            <AlertDialogHeader>
              <AlertDialogTitle>Bilgilendirme</AlertDialogTitle>
              <AlertDialogDescription>
                {uyariMesaji}
              </AlertDialogDescription>
            </AlertDialogHeader>
            <AlertDialogFooter>
              <AlertDialogCancel >Kapat</AlertDialogCancel>
            </AlertDialogFooter>
          </AlertDialogContent>
        </AlertDialog>
      )}
    </div>
  )
}

export default UyariMesajKutusu;