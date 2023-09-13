import Calendar from '@/components/Calendar/Calendar';
import { useGetTransactionList } from '@/hooks/apiHooks/useGetTransactionList';
import { MainPageWrapper } from '@/pages/Main/Main.style';
import { DateInterface } from '@/types/date';
import { getNewDateObj } from '@/utils/getNewDateObj';
import { useEffect, useState } from 'react';

const Main = () => {
  const [date, setDate] = useState<DateInterface>({ ...getNewDateObj(new Date()) });
  const getTransactionListMutation = useGetTransactionList();

  // useEffect(() => {
  //   getTransactionListMutation.mutate({
  //     year: date.year,
  //     month: date.month,
  //     accountNumber: 'asdf',
  //   });
  // }, [date]);

  const updateDate = (newDate: DateInterface) => setDate({ ...newDate });

  return (
    <MainPageWrapper>
      <Calendar updateDate={updateDate} />
    </MainPageWrapper>
  );
};

export default Main;
