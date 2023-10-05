import { axiosInstance } from '@/apis';

export const getMyNotification = async () => {
  const { data } = await axiosInstance.get('/notification/me');
  return data;
};

export const getNotification = async () => {
  const { data } = await axiosInstance.get('/notification/public');
  return data;
};
