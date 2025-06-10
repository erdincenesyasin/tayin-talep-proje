import React, { useState, useEffect, useContext } from "react";
import { getProfil } from "../services/auth/profilService";
import { AppContext } from "../context/AppContent";
import { useNavigate } from "react-router-dom";
import ProfilCard from "../components/ProfilCard";
import { tokenService } from "../services/auth/tokenService";

const Profil = () => {

    //user ve admin ayrımını bu sayfada yapıp
    //admin yetkili ise yani user değilse admin sayfasına
    // yöndirme yapacağım. rol yetkileri zaten javada tanımlı.

    const { profilBilgileri, setProfilBilgileri } = useContext(AppContext);
    const [istekBekleniyor, setIstekBekleniyor] = useState(false);
    const navigate = useNavigate();


    useEffect(() => {
        const handleProfil = async () => {
            setIstekBekleniyor(true);
            try {
                const profil = await getProfil();
                
                setProfilBilgileri(profil);

                if(profil.role!="USER"){
                    navigate("/admin");
                    return;
                }
            } catch (error) {
                setProfilBilgileri(null);
                tokenService.removeToken(); 
                navigate("/login");
            } finally {
                setIstekBekleniyor(false);
            }
        };
        handleProfil();
      
    }, []);

    return (
        <>
            {istekBekleniyor ? (
                <div className="bg-white-200 rounded-md font-medium text-center">
                    Kimlik bilgileriniz kontrol ediliyor. Lütfen bekleyiniz
                </div>
            ) : (
                <div className="flex w-full  justify-center items-start">
                    {profilBilgileri ? (
                        <ProfilCard user={profilBilgileri} />
                    ) : (
                        <div className="bg-red-100 text-red-800 font-medium me-2 px-2.5 py-0.5 rounded-sm dark:bg-red-900 dark:text-red-300">
                            Hata: Personel Bilgileri Alınamadı. Sistem yöneticinize başvurunuz.
                        </div>
                    )}
                </div>
            )}
        </>

    )

}

export default Profil;