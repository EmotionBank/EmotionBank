import { GetCalendar, getCalendarInfo } from '@/apis/bank/getCalendarInfo';
import { useQuery } from '@tanstack/react-query';

export const useGetCalendarInfo = (calendarParams: GetCalendar) => {
  const { data } = useQuery(['calendarInfo'], () => getCalendarInfo(calendarParams));
  return { getCalendarInfoData: data! };
};
