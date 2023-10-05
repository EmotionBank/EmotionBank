import { axiosInstance } from '@/apis';

export const getNotification = async () => {
  const { data } = await axiosInstance.get('/notification/me');
  return data;
};
