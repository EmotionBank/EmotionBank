import { axiosInstance } from '@/apis';

export const getFollowerList = async (userId: string) => {
  const { data } = await axiosInstance.get(`/users/follower/${userId}`);
  return data;
};
