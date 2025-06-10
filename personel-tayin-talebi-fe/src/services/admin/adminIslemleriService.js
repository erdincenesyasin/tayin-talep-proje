import api from '../api';
const API_URL = 'admin/tercih';

export const getTumTercihIslemleriniGetir = async () => {
    const response = await api.get(API_URL);
    return response.data;
};

export const postAdminIslemleri = async (payload) => {
    const response = await api.post(API_URL, payload);
    return response;
};


export const createAdminIslemleri = async (payload) => {
    const response = await api.post(API_URL, payload);
    return response;
};

export const getTumBasvurular = async (id, page, size) => {
    const response = await api.get(API_URL + '/basvurular-listesi/' + id + '?page=' + page + '&size=' + size);
    return response.data;
};

export const onaylaTercihIslemi = async (id) => {
    const response = await api.patch(API_URL + '/onayla/' + id);
    return response;
};

export const yayinlaTercihIslemi = async (id) => {
    const response = await api.patch(API_URL + '/isActive/' + id);
    return response;
};

