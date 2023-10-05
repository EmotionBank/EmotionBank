import { axiosInstance } from '@/apis/index';

export const updateMyInfo = async (nickname: string) => {
  const { data } = await axiosInstance.patch('/users/me', { nickname });
  return data;
};
