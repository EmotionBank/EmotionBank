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
    // console.log(req, req.headers['Authorization']);
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
      try {
        const response = await renewAccessToken();
        // console.log(response);
        localStorage.setItem('accessToken', response.accessToken);
        return axiosInstance(originalRequest);
      } catch (error) {
        console.log('토큰만료시 재요청 보냈을 때의 ' + error);
      }
    }
    // } else if (status === 409){
    //   alert('중복된 닉네임입니다.')
    //   throw(error)
    // } else if (status === 500) {
    //   console.log('여긴 500 에러', error);
    // }
    return Promise.reject(error);
  },
);
