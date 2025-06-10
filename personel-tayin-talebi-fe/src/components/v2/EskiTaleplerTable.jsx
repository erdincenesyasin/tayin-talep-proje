import { useState, useEffect } from "react";
import { Table, TableBody, TableCell, TableHead, TableHeader, TableRow } from "@/components/ui/table"
import { getTayinTalebi } from "@/services/tayintalep/tayinTalebiService";
import {
    Tooltip,
    TooltipContent,
    TooltipTrigger,
} from "@/components/ui/tooltip"

const EskiTaleplerTable = () => {


    const [eskiTalepler, setEskiTalepler] = useState([]);

    useEffect(() => {
        const getEskiTalepler = async () => {
            const response = await getTayinTalebi();
            setEskiTalepler(response.data);
        };

        getEskiTalepler();

    }, []);


    return (
        <div className='max-h-[300px] w-3/4 overflow-y-auto'>
            <Table>
                <TableHeader>
                    <TableRow>
                        <TableHead className="w-1/24">Sicil No</TableHead>
                        <TableHead className="w-1/24">Talep No</TableHead>

                        <TableHead className="w-1/24">Oluşturulma Tarihi</TableHead>
                        <TableHead className="w-4/24">Açıklama</TableHead>
                        <TableHead className="w-4/24">Tercihler</TableHead>
                    </TableRow>

                    {eskiTalepler.map((eskiTalebi, index) => (
                        <TableRow key={index}>
                            <TableCell>{eskiTalebi.personel.sicilNo}</TableCell>
                            <TableCell>{eskiTalebi.id}</TableCell>

                            <TableCell>{new Date(eskiTalebi.createdAt).toLocaleDateString("tr-TR")}</TableCell>
                            <TableCell>
                                <Tooltip>
                                    <TooltipTrigger>
                                        <span>{eskiTalebi.aciklama?.slice(0, 20)}..</span>
                                    </TooltipTrigger>
                                    <TooltipContent>
                                    {eskiTalebi.aciklama
  .split(" ")
  .reduce((arr, kelime, i) => {
    const grupIndex = Math.floor(i / 10);
    arr[grupIndex] = arr[grupIndex] ? arr[grupIndex] + " " + kelime : kelime;
    return arr;
  }, [])
  .map((parca, i) => <p key={i}>{parca}</p>)
}
                                    </TooltipContent>
                                </Tooltip>
                            </TableCell>



                            <TableCell>
                                <Tooltip>
                                    <TooltipTrigger>
                                        <span>Tercihleri Görmek için Mouse ile üzerine geliniz</span>
                                    </TooltipTrigger>
                                    <TooltipContent>
                                        <span>{eskiTalebi.id} nolu talebin tercih listesi</span>
                                        <span>{eskiTalebi.tercihList.map((t, i) => <p key={i}>{t.sira}. tercih {t.adliye.ad}</p>)}</span>
                                    </TooltipContent>
                                </Tooltip>
                            </TableCell>




                        </TableRow>
                    ))}
                </TableHeader>
            </Table>
        </div>
    )
}

export default EskiTaleplerTable;