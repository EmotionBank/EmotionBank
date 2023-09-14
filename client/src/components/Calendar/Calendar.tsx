import { useCallback, useEffect, useState } from 'react';
import * as S from '@/components/Calendar/Calendar.style';
import { DAY } from '@/constants/calendar';
import { DateInterface } from '@/types/date';
import { getMonthDate } from '@/utils/getMonthDate';
import { getNewDateObj } from '@/utils/getNewDateObj';

interface currnetDateInterface extends DateInterface {
  weeokList: DateInterface[][];
}
interface Props {
  updateDate: (newDate: DateInterface) => void;
}

const Calendar = ({ updateDate }: Props) => {
  const [currentDate, setCurrentData] = useState<currnetDateInterface>({
    date: 0,
    day: 0,
    year: 0,
    month: 0,
    weeokList: [],
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
        {currentDate.weeokList.map((week, idx) => (
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
