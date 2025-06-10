import { useState, useEffect } from 'react'
import { BrowserRouter as Router, Routes, Route, Navigate } from 'react-router-dom'


import './App.css'

import Main from './components/Main'
import AppProvider from './context/AppContent'
import { tokenService } from './services/auth/tokenService'
import Login from './pages/Login'

import Admin from './pages/v2/Admin'
import UyariMesajKutusu from './components/v2/UyariMesajKutusu'
import YeniCalismaOlusturmaFormu from './components/v2/YeniCalismaOlusturmaFormu'





import HeaderBaslik from './pages/HeadarBaslik'
import YayinlanmisCalismalar from './pages/v2/YayinlanmisCalismalar'
import TercihteBulunmaSayfasi from './pages/v2/TercihteBulunmaSayfasi'
import MobilNavBar from './components/v2/MobilNavBar'
import Logout from './pages/v2/Logout'

function App() {



  useEffect(() => {

  }, []);

  return (
    <div className=' lg:h-full h-full w-full flex flex-col bg-[#f5eeee]   '>

      <AppProvider>
        <UyariMesajKutusu />
        <Router>
          <Routes>
            <Route path="/login" element={<Login />} />
            <Route path="/*" element={<ProtectedRoutes />} />
          </Routes>
        </Router>
      </AppProvider>
    </div>
  )
}

function ProtectedRoutes() {

  const isAuthenticated = tokenService.isAuthenticated();

  if (!isAuthenticated) {
    return <Navigate to="/login" replace />;
  }

  return (
    <div className='  flex flex-col  h-full w-full  '>


      <div className=' p-2 '>
        <HeaderBaslik />
      </div>


      <div className='flex-1 '>
        <Routes>
          <Route path="/" element={<Main />} />

          <Route path="/calisma-listesi" element={<YayinlanmisCalismalar />} />
          <Route path="/yeni-talep" element={<TercihteBulunmaSayfasi />} />
          <Route path="/admin" element={<Admin />} />
          <Route path="/cikis" element={<Logout />} />
          <Route path="/admin-yeni-tayin" element={<YeniCalismaOlusturmaFormu />} />

        </Routes>
      </div>
      <MobilNavBar />
    </div>
  )
}

export default App