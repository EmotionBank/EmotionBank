import { GetCalendarInfoRequest, getCalendarInfo } from '@/apis/bank/getCalendarInfo';
import { useQuery } from '@tanstack/react-query';

export const useGetCalendarInfo = (calendarParams: GetCalendarInfoRequest) => {
  const { data } = useQuery(['calendarInfo'], () => getCalendarInfo(calendarParams));
  return { getCalendarInfoData: data! };
};
