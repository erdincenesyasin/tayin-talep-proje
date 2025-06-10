import React, { createContext, useState } from 'react';

export const AppContext = createContext();

export const AppProvider = ({ children }) => {
    const [aktifSayfa, setAktifSayfa] = useState("AnaSayfa");
    const [hataMesaji, setHataMesaji] = useState();
    const [uyariMesaji, setUyariMesaji] = useState();
    const [uyariGoster, setUyariGoster] = useState(false);
    const [hataGoster, setHataGoster] = useState(false);
    const [profilBilgileri, setProfilBilgileri] = useState();
    const [profilBilgileriGoster, setProfilBilgileriGoster] = useState(false);
    const [basvuruTemelBilgileriContext, setBasvuruTemelBilgileriContext] = useState([]);
    const [tayinCalismalari, setTayinCalismalari] = useState([]);
    const [eskiTaleplerTablosuVisible, setEskiTaleplerTablosuVisible] = useState(false);

    return (
        <AppContext.Provider value={{
            aktifSayfa, setAktifSayfa,
            hataMesaji, setHataMesaji,
            uyariMesaji, setUyariMesaji,
            uyariGoster, setUyariGoster,
            hataGoster, setHataGoster,
            profilBilgileri, setProfilBilgileri,
            profilBilgileriGoster, setProfilBilgileriGoster,
            basvuruTemelBilgileriContext, setBasvuruTemelBilgileriContext,
            tayinCalismalari, setTayinCalismalari,
            eskiTaleplerTablosuVisible, setEskiTaleplerTablosuVisible
           
        }}>
            {children}
        </AppContext.Provider>
    );
};

export default AppProvider;
