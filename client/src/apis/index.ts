import axios, { AxiosResponse, InternalAxiosRequestConfig } from 'axios';
import { renewAccessToken } from './user/renewAccessToken';
import { useNavigate } from 'react-router-dom';

export const axiosInstance = axios.create({
  baseURL: process.env.REACT_APP_SERVER_URL,
  headers: {
    'Content-Type': 'application/json',
  },
  withCredentials: true,
});

let isRefreshing = false;
let failedRequests: InternalAxiosRequestConfig[] = [];

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
      if (!isRefreshing) {
        isRefreshing = true;
        try {
          const response = await renewAccessToken();
          // console.log(response);
          localStorage.setItem('accessToken', response.accessToken);
          isRefreshing = false;
          // 다시 시도하지 못한 요청 처리
          failedRequests.forEach(cb => cb(response.accessToken));
          failedRequests = [];
          return axiosInstance(originalRequest);
        } catch (error) {
          console.log('토큰만료시 재요청 보냈을 때의 ' + error);
        }
      } else {
        return new Promise((resolve, reject) => {
          failedRequests.push(newAccessToken => {
            originalRequest.headers['Authorization'] = `Bearer ${newAccessToken}`;
            resolve(axiosInstance(originalRequest));
          });
        });
      }
    } else if (data.errorCode === 'J-002') {
      const navigate = useNavigate();
      const goToLogin = () => navigate('/login');
      console.log('유효하지 않은 refresh token');
      alert('로그인 화면으로 이동합니다.');
      goToLogin();
    }
    // } else if (status === 500) {
    //   console.log('여긴 500 에러', error);
    // }
    return Promise.reject(error);
  },
);
