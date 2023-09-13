import { useCallback, useEffect, useState } from 'react';
import * as S from '@/components/Calendar/Calendar.style';
import { DAY } from '@/constants/calendar';
import { DateInterface } from '@/types/date';
import { getMonthDate } from '@/utils/getMonthDate';
import { getNewDateObj } from '@/utils/getNewDateObj';
import { IDate } from '@/pages/Main/Main';

interface currnetDateInterface {
  year: number;
  month: number;
  date: DateInterface[][];
}
interface Props {
  updateDate: (newDate: IDate) => void;
}

const Calendar = ({ updateDate }: Props) => {
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
    updateDate({
      year: MonthDate.year,
      month: MonthDate.month,
    });
  }, [monthState]);

  const handleIncreseMonth = () => setMonthState(monthState + 1);
  const handleDecreseMonth = () => setMonthState(monthState - 1);

  const convertDateFormat = useCallback((date: number) => {
    const str = String(date);
    if (str.length === 1) return '0' + str;
    return str;
  }, []);

  return (
    <S.CalendarWrapper>
      <S.CalendarControllerWrapper>
        <S.CalendarController onClick={handleDecreseMonth}>&lt;</S.CalendarController>
        <S.CalendarController>
          {currentDate.year}. {convertDateFormat(currentDate.month)}
        </S.CalendarController>
        <S.CalendarController onClick={handleIncreseMonth}>&gt;</S.CalendarController>
      </S.CalendarControllerWrapper>
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
                <S.DayContainer key={i} $thisMonth={currentDate.month === day.month}>
                  <span>{day.date}</span>
                  {/* <S.EmotionImage src=""/> */}
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
