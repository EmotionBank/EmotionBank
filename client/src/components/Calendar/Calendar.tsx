import { useCallback, useEffect, useState } from 'react';
import * as S from '@/components/Calendar/Calendar.style';
import { DAY } from '@/constants/calendar';
import { DateType } from '@/types/date';
import { getMonthDate } from '@/utils/getMonthDate';
import { getNewDateObj } from '@/utils/getNewDateObj';
import Happy from '@assets/emotions/happy.svg';
interface currnetDateInterface extends DateType {
  weekList: DateType[][];
}
interface Props {
  updateDate: (newDate: DateType) => void;
  selectCalendarDate: (select: DateType) => void;
  calendarList: CalendarInfoListResponse;
}

const Calendar = ({ updateDate, selectCalendarDate, calendarList }: Props) => {
  const [currentDate, setCurrentData] = useState<currnetDateInterface>({
    date: 0,
    day: 0,
    year: 0,
    month: 0,
    weekList: [],
  });
  const [monthState, setMonthState] = useState<number>(0);

  useEffect(() => {
    const today = getNewDateObj(new Date());
    const MonthDate = getMonthDate(today, monthState);
    setCurrentData(MonthDate);
    updateDate({ ...MonthDate });
  }, [monthState]);

  const handleIncreseMonth = () => setMonthState(monthState + 1);
  const handleDecreseMonth = () => setMonthState(monthState - 1);

  const convertDateFormat = useCallback((date: number) => {
    const str = String(date);
    if (str.length === 1) return '0' + str;
    return str;
  }, []);

  const checkIsPaid = (curDate: DateType) => {
    let isSame = false;
    const curDateString = [curDate.year, curDate.month, curDate.date];
    calendarList.calendarInfoList.forEach(item => {
      const resultDate = item.date.split('-').map(e => Number(e));
      const result = resultDate.every((item, idx) => item === curDateString[idx]);
      if (result) isSame = result;
    });
    return isSame;
  };

  return (
    <S.CalendarWrapper>
      <S.CalendarControllerWrapper>
        <S.CalendarController>
          {currentDate.year}. {convertDateFormat(currentDate.month)}
        </S.CalendarController>
        <S.CalendarControllerContainer>
          <S.CalendarController onClick={handleDecreseMonth}>&lt;</S.CalendarController>
          <S.CalendarController onClick={handleIncreseMonth}>&gt;</S.CalendarController>
        </S.CalendarControllerContainer>
      </S.CalendarControllerWrapper>
      <S.CalendarHeader>
        {DAY.map(item => (
          <span key={item}>{item}</span>
        ))}
      </S.CalendarHeader>
      <S.CalendarBody>
        {currentDate.weekList.map((week, idx) => (
          <S.WeekContainer key={idx}>
            {week.map((day, i) => {
              return (
                <S.DayContainer
                  key={i}
                  $thisMonth={currentDate.month === day.month}
                  onClick={() => selectCalendarDate(day)}
                >
                  {checkIsPaid(day) ? <Happy /> : <span>{day.date}</span>}
                </S.DayContainer>
              );
            })}
          </S.WeekContainer>
        ))}
      </S.CalendarBody>
    </S.CalendarWrapper>
  );
};

export default Calendar;
