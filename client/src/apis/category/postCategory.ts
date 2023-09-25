import { axiosInstance } from '@/apis';

export interface postCategoryRequestType {
  userId: string;
  categoryName: string;
  visibility: string; //“PRIVATE” | “PUBLIC” | “FOLLOWER”
}

export const postCategory = async (requestData: postCategoryRequestType) => {
  const { data } = await axiosInstance.post('/category', requestData);
  return data;
};
