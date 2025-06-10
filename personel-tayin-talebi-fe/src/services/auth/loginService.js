
import { tokenService } from '../auth/tokenService';
import api from '../api';

const API_URL = 'auth/';

export const login = async (sicilNo, password) => {
   
      const response = await api.post(API_URL + 'login', { sicilNo, password });
      if (response.data.token) {
        tokenService.setToken(response.data.token);
    }
  
      return response.data;
    
  };
  



export const logout = async () => {
    const response = await api.post(API_URL + 'logout');
    tokenService.removeToken("auth_token");
  
    return response.data;
};
