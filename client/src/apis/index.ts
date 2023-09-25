import axios, { AxiosResponse, InternalAxiosRequestConfig } from 'axios';
import { renewAccessToken } from './user/renewAccessToken';

export const axiosInstance = axios.create({
  baseURL: process.env.REACT_APP_SERVER_URL,
  headers: {
    'Content-Type': 'application/json',
  },
  withCredentials: true,
});

axiosInstance.interceptors.request.use(
  (req: InternalAxiosRequestConfig): InternalAxiosRequestConfig => {
    const accessToken = localStorage.getItem('accessToken');
    if (accessToken) {
      try {
        req.headers['Authorization'] = `Bearer ${accessToken}`;
      } catch (error) {
        throw error;
      }
    }
    console.log(req, req.headers['Authorization']);
    return req;
  },
  error => {
    return Promise.reject(error);
  },
);

axiosInstance.interceptors.response.use(
  (response: AxiosResponse): AxiosResponse => {
    return response;
  },
  async (error: any) => {
    console.log('여긴 response error' + error);
    const {
      config,
      response: { status },
    } = error;
    const originalRequest = config;
    if (status === 401) {
      try {
        const response = await renewAccessToken();
        localStorage.setItem('accessToken', response.accessToken);
      } catch (error) {
        console.log('토큰만료시 재요청 보냈을 때의 ' + error);
      }
    } else if (status === 500) {
      console.log('여긴 500 에러', error);
    }
    return Promise.reject(error);
  },
);
