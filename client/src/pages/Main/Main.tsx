import { useState } from 'react';
import Calendar from '@/components/Calendar/Calendar';
import TransactionList from '@/components/transaction/TransactionList/TransactionList';
import { useGetCalendarInfo } from '@/hooks/apiHooks/useGetCalendarInfo';
import { useGetTransactionList } from '@/hooks/apiHooks/useGetTransactionList';
import { MainPageWrapper } from '@/pages/Main/Main.style';
import { DateType } from '@/types/date';
import { convertYYYYMMDD } from '@/utils/convertDateToString';
import { getNewDateObj } from '@/utils/getNewDateObj';
import UserInfo from '@/components/UserInfo/UserInfo';

const Main = () => {
  const [date, setDate] = useState<DateType>({ ...getNewDateObj(new Date()) });
  const [selectedDate, setSelectedDate] = useState<DateType>(getNewDateObj(new Date())); // 거래내역 조회 시 사용
  const initTransactionData = {
    accountId: 1,
    startDate: convertYYYYMMDD(selectedDate),
    endDate: convertYYYYMMDD(selectedDate),
  };
  const initCalendarData = {
    accountId: 1,
    year: date.year,
    month: date.month,
  };

  const { transactionListData } = useGetTransactionList(initTransactionData); // 특정 날짜 조회
  const { getCalendarInfoData } = useGetCalendarInfo(initCalendarData);

  const dummy = {
    transactionInfoList: [
      {
        transactionId: 1,
        emoticon: 'emoticon',
        date: '2023-09-04 09:00:00',
        transactionType: 'type',
        title: 'title',
        amount: 1000,
      },
      {
        transactionId: 2,
        emoticon: 'emoticon',
        date: '2023-09-05 09:00:00',
        transactionType: 'type',
        title: 'title',
        amount: 3000,
      },
    ],
  };

  const updateDate = (newDate: DateType) => setDate({ ...newDate });
  const selectCalendarDate = (select: DateType) => setSelectedDate(select);

  return (
    <MainPageWrapper>
      <UserInfo />
      <Calendar updateDate={updateDate} selectCalendarDate={selectCalendarDate} />
      <TransactionList transactionDatas={transactionListData} />
    </MainPageWrapper>
  );
};

export default Main;
