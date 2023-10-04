import { axiosInstance } from '@/apis';
import { GetFollowList } from '@/types/user';

export const getFollowerList = async (userId: string) => {
  const { data } = await axiosInstance.get<GetFollowList>(`/users/follower/${userId}`);
  return data;
};
