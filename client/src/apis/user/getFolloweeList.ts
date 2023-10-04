import { axiosInstance } from '@/apis';

export const getFolloweeList = async (userId: string) => {
  const { data } = await axiosInstance.get(`/users/followee/${userId}`);
  return data;
};
