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
      const {accessToken} = await renewAccessToken();
      localStorage.setItem('accessToken', accessToken)
      originalRequest.headers.Authorization = `Bearer ${accessToken}`
      console.log(originalRequest)
      return axiosInstance(originalRequest)
    }
    return Promise.reject(error);
  },
);
