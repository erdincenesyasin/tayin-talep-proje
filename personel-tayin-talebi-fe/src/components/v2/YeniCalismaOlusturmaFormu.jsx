import { useState } from "react";

const YeniCalismaOlusturmaFormu = () => {
  const [form, setForm] = useState({
    tercihDonemiAciklamaMetniBasligi: "",
    duyuruMetniLinki: "",
    tercihMaxSayisi: 1,
    tercihMinSayisi: 1,
    talepTuru: "",
    talepHakSayisi: 0,
    basvuruBaslangicTarihi: "",
    basvuruBitisTarihi: "",
    sonucAciklamaTarihi: "",
  });

  const handleChange = (e) => {
    
  };

  const handleSubmit = (e) => {
   
  };


///bu safya yapılmamıştır. işleyişi izah için konulmuştur.



  return (
<div className="flex flex-col bg-amber-200 items-center justify-center h-full w-full">
    <div> <h3 className="text-xl font-bold mb-2">BU SAYFA PROJEYE DAHİL DEĞİLDİR. İŞLEYİŞİ İZAH İÇİN KONULMUŞTUR.</h3></div>
    <div className="flex flex-row bg-amber-200 items-center justify-center h-full w-full">
<form className="max-w-lg mx-auto p-6 bg-white rounded shadow space-y-4">
    <h2 className="text-xl font-bold mb-2">YENİ TALEP TÜRÜ OLUŞTUR</h2>

    <input
        type="text"
        name="tercihDonemiAciklamaMetniBasligi"
        placeholder="Açıklama Başlığı"
        value={form.tercihDonemiAciklamaMetniBasligi}
        onChange={handleChange}
        className="w-full border p-2 rounded"
        required
      />

      <button className="w-full bg-blue-600 text-white p-2 rounded hover:bg-blue-700">
        TALEP TÜRÜ OLUŞTUR
      </button>

<div className="text-sm text-gray-500">  
     ÖRNEKLER  : <br/>
  <li>- ISTEGE_BAGLI_CALISMA_KAPSAMINDA</li>
  <li>- ISTEGE_BAGLI_CALISMA_DISI</li>
  <li>- MAZERET_SAGLIK</li>
  <li>- MAZERET_CAN</li>
  <li>- MAZERET_AILE</li>
  </div>

</form>

    <form
      onSubmit={handleSubmit}
      className="max-w-lg mx-auto p-6 bg-white rounded shadow space-y-4"
    >
      <h2 className="text-xl font-bold mb-2">Yeni Tayin Talebi Çalışması Oluştur</h2>
      <input
        type="text"
        name="tercihDonemiAciklamaMetniBasligi"
        placeholder="Açıklama Başlığı"
        value={form.tercihDonemiAciklamaMetniBasligi}
        onChange={handleChange}
        className="w-full border p-2 rounded"
        required
      />
      <input
        type="text"
        name="duyuruMetniLinki"
        placeholder="Duyuru Linki"
        value={form.duyuruMetniLinki}
        onChange={handleChange}
        className="w-full border p-2 rounded"
        required
      />
      <div className="flex gap-2">
        <input
          type="number"
          name="tercihMaxSayisi"
          placeholder="Tercih Max"
          min={1}
          max={50}
          value={form.tercihMaxSayisi}
          onChange={handleChange}
          className="w-1/2 border p-2 rounded"
          required
        />
        <input
          type="number"
          name="tercihMinSayisi"
          placeholder="Tercih Min"
          min={1}
          max={10}
          value={form.tercihMinSayisi}
          onChange={handleChange}
          className="w-1/2 border p-2 rounded"
          required
        />
      </div>
      <select
  name="talepTuru"
  value={form.talepTuru}
  onChange={handleChange}
  className="w-full border p-2 rounded"
  required
>
  <option value="">Talep Türü Seçiniz</option>
  <option value="ISTEGE_BAGLI_CALISMA_KAPSAMINDA">İsteğe Bağlı Çalışma Kapsamında</option>
  <option value="ISTEGE_BAGLI_CALISMA_DISI">İsteğe Bağlı Çalışma Dışı</option>
  <option value="MAZERET_SAGLIK">Mazeret Sağlık</option>
  <option value="MAZERET_CAN">Mazeret Can</option>
  <option value="MAZERET_AILE">Mazeret Aile</option>
</select>

<span>Talep Hak Sayısı- Sınırsız vermek için -1</span>
      <input
        type="number"
        name="talepHakSayisi"
        placeholder=""
        min={-1}
        max={100}
        value="Talep Hak Sayısı-SÜREKLİLİK ARZ EDENLER -1, BİR KERELİKOLANLAR 1"
     
        className="w-full border p-2 rounded"
        required
      />
      <div className="flex gap-2">
        <input
          type="date"
          name="basvuruBaslangicTarihi"
          value={form.basvuruBaslangicTarihi}
          
          className="w-1/3 border p-2 rounded"
          required
        />
        <input
          type="date"
          name="basvuruBitisTarihi"
          value={form.basvuruBitisTarihi}
          onChange={handleChange}
          className="w-1/3 border p-2 rounded"
          required
        />
        <input
          type="date"
          name="sonucAciklamaTarihi"
          value={form.sonucAciklamaTarihi}
          onChange={handleChange}
          className="w-1/3 border p-2 rounded"
          required
        />
      </div>
      <button
      disabled={true}
        type="submit"
        className="w-full bg-gray-400 text-white p-2 rounded hover:bg-blue-700"
      >
        Kaydet
      </button>
    </form>
    </div>
    </div>
  );
};

export default YeniCalismaOlusturmaFormu;