import api from "../api";

//build ettin senro / kullanalım. cçünkü aynı orjinden olacak.
const API_URL = 'tayin-talebi/me';
/// todo : tercihlerimi getir değil, tayintaleplerimi getir var. buna 
//ihtiyacımız olmaz, belki açıklamaları çekmezsek, ayrı çekersek ihtiyaç olabilir..
//buna controller yazmadım sanırım.. sonra bakılacak...
export const getTercihler = async (id) => {
    const response = await api.get(API_URL + '/' + id);
    return response;
};




