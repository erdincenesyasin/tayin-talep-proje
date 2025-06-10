import api from "../api";

const API_URL = "tayin-talebi/me";

export const getTayinTalebi = async () => {
    const response = await api.get(API_URL);
    return response;
};

export const getTalebinTercihleri = async (id) => {
    const response = await api.get(API_URL + '/' + id + '/tercih-listesi');
    return response;
};



export const createTayinTalebi = async (tayinTalebi) => {
    const response = await api.post(API_URL, tayinTalebi);
    return response;
};

export const getTercihimVarMi = async (id) => {
    const response = await api.get(API_URL + '/tercihim-var-mi/' + id);
    return response;
};