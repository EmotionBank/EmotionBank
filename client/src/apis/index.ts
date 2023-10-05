import axios, { AxiosResponse, InternalAxiosRequestConfig } from 'axios';
import { renewAccessToken } from './user/renewAccessToken';
import { logoutUser } from './user/logoutUser';
import { PATH } from '@/constants/path'
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
      req.headers['Authorization'] = `Bearer ${accessToken}`;
    }
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
      response: { data },
    } = error;
    const originalRequest = config;
    if (data.errorCode === 'J-003') {
      const {accessToken} = await renewAccessToken()
      localStorage.setItem('accessToken', accessToken)
      originalRequest.headers.Authorization = `Bearer ${accessToken}`
      return axiosInstance(originalRequest)
    } else if (data.errorCode === 'A-005'){
      alert('잔액이 부족합니다.')
    } else if (data.errorCode === 'J-002'|| data.errorCode === 'J-001') {
      localStorage.clear();
      alert('로그인 화면으로 돌아갑니다.')
      window.location.replace(PATH.ROOT);
    }
    return Promise.reject(error);
  },
);
