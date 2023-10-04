import { axiosInstance } from '@/apis';

export const postFollow = async (userId: string) => {
  const { data } = await axiosInstance.post(`/users/follow/${userId}`);
  return data;
};
