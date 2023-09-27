import { axiosInstance } from '@/apis/index';

export const updateMyInfo = async (nickname: string) => {
  const { data } = await axiosInstance.put('/users', nickname);
  return data;
};
