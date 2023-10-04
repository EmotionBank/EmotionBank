import { useQuery } from '@tanstack/react-query';
import { getFolloweeList } from '@/apis/user/getFolloweeList';

export const useGetFolloweeList = (userId: string) => {
  const { data } = useQuery(['followeeList'], () => getFolloweeList(userId));
  return { getFolloweeListData: data };
};
