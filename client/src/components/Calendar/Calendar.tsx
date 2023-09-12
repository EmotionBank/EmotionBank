import * as S from '@/components/Calendar/Calendar.style';
import { DAY } from '@/constants/calendar';
import { getMonthDate } from '@/utils/getMonthDate';
import { getNewDateObj } from '@/utils/getNewDateObj';
import { useEffect, useState } from 'react';

interface newDateInterface {
  year: number;
  month: number;
  date: number;
  day: number;
}
interface currnetDateInterface {
  year: number;
  month: number;
  date: newDateInterface[][];
}

const Calendar = () => {
  const [currentDate, setCurrentData] = useState<currnetDateInterface>({
    year: 0,
    month: 0,
    date: [],
  });
  const [monthState, setMonthState] = useState<number>(0);

  useEffect(() => {
    const today = getNewDateObj(new Date());
    const MonthDate = getMonthDate(today, monthState);
    setCurrentData(MonthDate);
  }, [monthState]);
  console.log(currentDate);

  return (
    <S.CalendarWrapper>
      <S.CalendarHeader>
        {DAY.map(item => (
          <span key={item}>{item}</span>
        ))}
      </S.CalendarHeader>
      <S.CalendarBody>
        {currentDate.date.map((week, idx) => (
          <S.WeekContainer key={idx}>
            {week.map((day, i) => {
              return (
                <S.DayContainer key={i}>
                  <span>{day.date}</span>
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
