import { getMyInfo } from '@/apis/user/getMyInfo';
import { useQuery } from '@tanstack/react-query';

export const useGetMyInfo = () => {
  const { data } = useQuery(['myInfo'], getMyInfo);
  return { getMyInfoData: data! };
};
