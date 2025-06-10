import axios from 'axios';


const api = axios.create({ // BACKENDE KONULANCA API_URL DEĞİŞTİRİLECEK /
  baseURL: 'http://localhost:8080/', //build alırken "/" olarak buil alınacak. resource/static klasörüne konulacak.
});


api.interceptors.request.use(
  (config) => {
    const token = localStorage.getItem('auth_token'); 
    if (token) {
      config.headers.Authorization = `Bearer ${token}`;
    }
    return config;
  },
  (error) => Promise.reject(error)
);


api.interceptors.response.use(
  (response) => response,
  (error) => {
    if (error.response) {
   
      const msg = error.response.data.message || 'SİSTEM HATASI YÖNETİCİYE BAŞVURUN.';
      return Promise.reject(new Error(error.response.data.message));
    } else if (error.request) {
      return Promise.reject(new Error('Sunucuya ulaşılamadı.'));
    } else {
      return Promise.reject(new Error('Beklenmeyen bir hata oluştu.'));
    }
  }
);

export default api;
