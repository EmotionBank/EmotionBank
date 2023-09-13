import Calendar from '@/components/Calendar/Calendar';
import { useGetTransactionList } from '@/hooks/apiHooks/useGetTransactionList';
import { MainPageWrapper } from '@/pages/Main/Main.style';
import { useEffect, useState } from 'react';

export interface IDate {
  year: number;
  month: number;
}

const Main = () => {
  const [date, setDate] = useState<IDate>({ year: new Date().getFullYear(), month: new Date().getMonth() + 1 });
  const getTransactionListMutation = useGetTransactionList();

  // useEffect(() => {
  //   getTransactionListMutation.mutate({
  //     year: date.year,
  //     month: date.month,
  //     accountNumber: 'asdf',
  //   });
  // }, [date]);

  const updateDate = (newDate: IDate) => setDate({ ...newDate });

  return (
    <MainPageWrapper>
      <Calendar updateDate={updateDate} />
    </MainPageWrapper>
  );
};

export default Main;
