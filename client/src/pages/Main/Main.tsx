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
import { useGetUserAccount } from '@/hooks/apiHooks/useGetUserAccount';

const Main = () => {
  const [date, setDate] = useState<DateType>({ ...getNewDateObj(new Date()) });
  const [selectedDate, setSelectedDate] = useState<DateType>(getNewDateObj(new Date())); // 거래내역 조회 시 사용
  const { getUserAccountInfoData } = useGetUserAccount();
  const initTransactionData = {
    accountId: Number(localStorage.getItem('accountId')),
    startDate: convertYYYYMMDD(selectedDate),
    endDate: convertYYYYMMDD(selectedDate),
  };
  const initCalendarData = {
    accountId: Number(localStorage.getItem('accountId')),
    year: date.year,
    month: date.month,
  };
  const { transactionListData } = useGetTransactionList(initTransactionData); // 특정 날짜 조회
  const { getCalendarInfoData } = useGetCalendarInfo(initCalendarData);

  const updateDate = (newDate: DateType) => setDate({ ...newDate });
  const selectCalendarDate = (select: DateType) => setSelectedDate(select);

  return (
    <MainPageWrapper>
      <UserInfo getUserAccountInfoData={getUserAccountInfoData} />
      <Calendar updateDate={updateDate} selectCalendarDate={selectCalendarDate} calendarList={getCalendarInfoData} />
      <TransactionList transactionDatas={transactionListData} />
    </MainPageWrapper>
  );
};

export default Main;
