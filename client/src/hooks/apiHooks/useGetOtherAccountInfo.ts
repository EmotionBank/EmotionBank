import { getOtherAccountInfo } from '@/apis/user/gerOtherAccountInfo';
import { useQuery } from '@tanstack/react-query';

export const useGetOtherAccountInfo = (userId: string) => {
  const { data } = useQuery(['otherAccountInfo', userId], () => getOtherAccountInfo(userId));
  return { getOtherAccountInfoData: data! };
};
