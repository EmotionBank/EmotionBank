import { getUserAccountInfo } from '@/apis/user/getUserAccountInfo';
import { useQuery } from '@tanstack/react-query';

export const useGetUserAccount = () => {
  const { data } = useQuery(['userAccountInfo'], getUserAccountInfo);
  return { getUserAccountInfoData: data };
};
