import axios from 'axios';
import api from '../api';


const API_URL = 'admin/tercih/aktif-basvurular';

export const getBasvuruTemelBilgileri = async () => {
    const response = await api.get(API_URL    );
    return response.data;
}
