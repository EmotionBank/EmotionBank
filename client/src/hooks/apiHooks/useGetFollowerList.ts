import { useQuery } from '@tanstack/react-query';
import { getFollowerList } from '@/apis/user/getFollowerList';

export const useGetFollowerList = (userId: string) => {
  const { data } = useQuery(['followerList'], () => getFollowerList(userId));
  return { getFollowerListData: data };
};
