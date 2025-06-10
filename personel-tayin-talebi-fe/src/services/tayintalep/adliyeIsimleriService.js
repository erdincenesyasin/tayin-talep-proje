
import api from '../api';
const API_URL = 'adliye';

export const getAdliyeIsimleri = async () => {
    const response = await api.get(API_URL + '/tercih-formu-icin-adliye-listesi');
    return response.data;
};

