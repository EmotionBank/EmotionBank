import { axiosInstance } from '@/apis';
import { GetFollowList } from '@/types/user';

export const getFolloweeList = async (userId: string) => {
  const { data } = await axiosInstance.get<GetFollowList>(`/users/followee/${userId}`);
  return data;
};
