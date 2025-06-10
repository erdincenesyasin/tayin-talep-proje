import React from 'react'
import {
    Card,
    CardAction,
    CardContent,
    CardDescription,
    CardFooter,
    CardHeader,
    CardTitle,
  } from "@/components/ui/card"
import { Avatar, AvatarFallback, AvatarImage } from "@/components/ui/avatar"
import { Button } from "@/components/ui/button"
import { Separator } from "@/components/ui/separator"
import { Label } from "@/components/ui/label"

import avatar from "@/assets/avatar.png"

  const ProfilCard = ({ user }) => {
    return (
         <Card className="w-[350px] border-2 border-gray-300">
        <CardHeader>
          <div className="flex items-center space-x-4 ">
            <Avatar >
                            <AvatarImage  src={avatar} alt={user.ad} />
              <AvatarFallback>
                <img src={avatar} alt="avatar" className='w-full h-full' />
              </AvatarFallback>
            </Avatar>
            <div>
              <CardTitle>{user.ad} {user.soyad}</CardTitle>
              <CardDescription>Sicil No: {user.sicilNo}</CardDescription>
            </div>
          </div>
        </CardHeader>
        <CardContent>
          <div className="space-y-2">
            <div className="flex items-center gap-2">
              <span className="font-semibold">Ünvan:</span>
              <span>{user.unvan}</span>
            </div>
            <div className="flex items-center gap-2">
              <span className="font-semibold">Çalıştığı Adliye:</span>
              <span>{user.calistigiAdliye}</span>
            </div>
            <div className="flex items-center gap-2">
              <span className="font-semibold">Telefon:</span>
              <span>{user.telefon || "Belirtilmemiş"}</span>
            </div>
          </div>
          <Separator className="my-4" />
        </CardContent>
        <CardFooter>
        <Label className='text-sm  bg-red-100 text-gray-800 p-2 rounded-md'>
            <p className='text-xs'>
               Bilgilerinizde değişiklik varsa UYAP üzerinden talep göndermeniz gerekmektedir.
            </p>
        </Label>
        </CardFooter>
      </Card>
    )
  }

  export default ProfilCard;