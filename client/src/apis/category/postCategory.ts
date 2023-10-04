import { axiosInstance } from '@/apis';

export interface postCategoryRequestType {
  categoryName: string;
  visibility: string; //“PRIVATE” | “PUBLIC”
}

export const postCategory = async (requestData: postCategoryRequestType) => {
  const { data } = await axiosInstance.post('/category', requestData);
  return data;
};
