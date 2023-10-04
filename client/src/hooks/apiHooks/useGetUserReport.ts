import { getUserReport } from '@/apis/user/getUserReport';
import { useQuery } from '@tanstack/react-query';

export const useGetUserReport = () => {
  const { data } = useQuery(['report'], getUserReport);
  return { getUserReportData: data! };
};
