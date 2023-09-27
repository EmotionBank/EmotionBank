import { axiosInstance } from '@/apis';
import { GetCategoryListResponse } from '@/types/bank';

export const getCategotyList = async () => {
  const { data } = await axiosInstance.get<GetCategoryListResponse>('/category');
  return data;
};
