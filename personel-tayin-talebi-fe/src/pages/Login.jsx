import React, { useState, useEffect, useContext } from 'react';
import { login, logout } from '../services/auth/loginService';
import { useNavigate } from 'react-router-dom';

import { tokenService } from '../services/auth/tokenService';
import { AppContext } from '../context/AppContent';
import { Button } from '@/components/ui/button';

import {
  Card,
  CardAction,
  CardContent,
  CardDescription,
  CardFooter,
  CardHeader,
  CardTitle,
} from "@/components/ui/card"
import { Input } from "@/components/ui/input"
import { Label } from "@/components/ui/label"
import { Avatar, AvatarFallback, AvatarImage } from "@/components/ui/avatar"


const Login = () => {

    const [sicilNo, setSicilNo] = useState('');
    const [password, setPassword] = useState('');
    const [error, setError] = useState('');
    const [testVerisiniGoster, setTestVerisiniGoster] = useState(false);
    const navigate = useNavigate();
    const { profilBilgileri, setProfilBilgileri } = useContext(AppContext);
  const { uyariMesaji, setUyariMesaji, uyariGoster, setUyariGoster } = useContext(AppContext);


   


    const handleLogin = async (e) => {

        e.preventDefault();
        try {
            const response = await login(sicilNo, password);
          

           
            if(response.role === "ROLE_USER"){
                navigate('/calisma-listesi');
            }
            else{
                navigate('/admin');
            }
           
            
        } catch (error) {
          setUyariMesaji(error.message);
          setUyariGoster(true);
        
            tokenService.removeToken();
           
        }
        finally {
            setSicilNo('');
            setPassword('');
           
        }
    };

    return (
        <div className="bg-[#ecdfe0] shadow shadow-black/20 flex min-h-svh flex-col items-center justify-center gap-6 p-6 md:p-10">
     
        <Card className="w-full max-w-sm">
      <CardHeader>
        <CardTitle className='text-xl font-bold'> Personel Genel Müdürlüğü</CardTitle>
        <CardDescription>
          Sicil ve şifre ile giriş yapınız.
        </CardDescription>
        <CardAction>
        <Avatar>
  <AvatarImage   />
  <AvatarFallback>PGM</AvatarFallback>
</Avatar>
        </CardAction>
      </CardHeader>
      <CardContent>
        <form>
          <div className="flex flex-col gap-6">
            <div className="grid gap-2">
              <Label htmlFor="sicil">Sicil</Label>
          
           
              <Input
                
                id="sicil"
                type="number"
                placeholder=""
                required
                value={sicilNo}
                onChange={e => setSicilNo(e.target.value)}
              />
             
            </div>
            <div className="grid gap-2">
              <div className="flex items-center">
                <Label htmlFor="password">Şifre</Label>
               
              </div>
              <Input
                id="password"
                type="password"
                required
                value={password}
                onChange={e => setPassword(e.target.value)}
              />
            </div>
          </div>
        </form>
      </CardContent>
      <CardFooter className="flex-col gap-2">
        <Button onClick={(e) => handleLogin(e)} type="submit" className="w-full">
          Giriş Yap
        </Button>
      <span>Eğitim amaçlı kodlanmıştır.</span>
      </CardFooter>
    </Card>
    </div>
  )
    
};

export default Login;

