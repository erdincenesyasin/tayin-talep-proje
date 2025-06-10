import api from '../api';

const API_URL = 'auth/profile';

export const getProfil = async () => {
    const response = await api.get(API_URL);
   
    return response.data;
}

