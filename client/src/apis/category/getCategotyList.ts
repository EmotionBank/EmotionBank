import { axiosInstance } from '@/apis';
import { CategoryListType } from '@/types/bank';

export const getCategotyList = async () => {
  const { data } = await axiosInstance.get<CategoryListType>('/category');
  return data;
};
