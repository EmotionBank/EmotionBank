import { axiosInstance } from '@/apis';

export const postFCMToken = async (token: string) => {
  const { data } = await axiosInstance.post('/fcmtoken', { token });
  return data;
};
