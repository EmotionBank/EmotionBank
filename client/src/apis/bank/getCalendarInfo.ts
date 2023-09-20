import axios from 'axios';

export interface GetCalendar {
  accountId: string;
  date: string; // 2023-09
}

// 달력에 뿌릴 데이터
export const getCalendarInfo = async (calendarParams: GetCalendar) => {
  const { data } = await axios.get('/calendar', { params: { ...calendarParams } });
  return data;
};
