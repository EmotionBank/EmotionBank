import { axiosInstance } from '@/apis';
import { GetMyNotificationResponse } from '@/types/user';

export const getMyNotification = async () => {
  const { data } = await axiosInstance.get<GetMyNotificationResponse>('/notification/me');
  return data;
};

export const getNotification = async () => {
  const { data } = await axiosInstance.get('/notification/public');
  return data;
};
