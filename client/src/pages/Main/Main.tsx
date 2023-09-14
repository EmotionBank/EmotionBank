import Calendar from '@/components/Calendar/Calendar';
import TransactionList from '@/components/transaction/TransactionList/TransactionList';
import { useGetTransactionList } from '@/hooks/apiHooks/useGetTransactionList';
import { MainPageWrapper } from '@/pages/Main/Main.style';
import { DateType } from '@/types/date';
import { getNewDateObj } from '@/utils/getNewDateObj';
import { useEffect, useState } from 'react';

const Main = () => {
  const [date, setDate] = useState<DateType>({ ...getNewDateObj(new Date()) });
  const getTransactionListMutation = useGetTransactionList();

  // useEffect(() => {
  //   getTransactionListMutation.mutate({
  //     year: date.year,
  //     month: date.month,
  //     accountNumber: 'asdf',
  //   });
  // }, [date]);

  const dummy = {
    transactions: [
      {
        transactionId: 1,
        emotion: 'emotion',
        date: new Date(),
        type: 'type',
        title: 'title',
        money: 1000,
        balance: 1000,
      },
      {
        transactionId: 2,
        emotion: 'emotion',
        date: new Date(),
        type: 'type',
        title: 'title2',
        money: 500,
        balance: 100120,
      },
    ],
  };

  const updateDate = (newDate: DateType) => setDate({ ...newDate });

  return (
    <MainPageWrapper>
      <Calendar updateDate={updateDate} />
      <TransactionList transactionDatas={dummy} />
    </MainPageWrapper>
  );
};

export default Main;
