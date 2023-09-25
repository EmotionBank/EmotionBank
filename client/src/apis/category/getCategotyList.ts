import { axiosInstance } from '@/apis';

export const getCategotyList = async (userId: string) => {
  const { data } = await axiosInstance.get(`/categories/${userId}`);
  return data;
};
