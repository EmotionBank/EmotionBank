import { useState, useEffect } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import * as S from '@/pages/OtherUser/OtherUser.style';
import { DateType } from '@/types/date';
import { getNewDateObj } from '@/utils/getNewDateObj';
import { convertYYYYMMDD } from '@/utils/convertDateToString';
import { useGetTransactionList } from '@/hooks/apiHooks/useGetTransactionList';
import { useGetCalendarInfo } from '@/hooks/apiHooks/useGetCalendarInfo';
import { useGetOtherAccountInfo } from '@/hooks/apiHooks/useGetOtherAccountInfo';
import OtherUserInfo from '@/components/OtherUserInfo/OtherUserInfo';
import Calendar from '@/components/Calendar/Calendar';
import TransactionList from '@/components/transaction/TransactionList/TransactionList';
import { PATH } from '@/constants/path';

const OtherUser = () => {
  const { userId } = useParams();
  const navigate = useNavigate();
  useEffect(() => {
    if (userId === localStorage.getItem('userId')) {
      navigate(PATH.ROOT);
      return;
    }
  }, []);

  const [date, setDate] = useState<DateType>({ ...getNewDateObj(new Date()) });
  const [selectedDate, setSelectedDate] = useState<DateType>(getNewDateObj(new Date())); // 거래내역 조회 시 사용
  const { getOtherAccountInfoData } = useGetOtherAccountInfo(userId!);

  const initTransactionData = {
    accountId: Number(getOtherAccountInfoData?.accountId),
    startDate: convertYYYYMMDD(selectedDate),
    endDate: convertYYYYMMDD(selectedDate),
  };
  const initCalendarData = {
    accountId: Number(getOtherAccountInfoData?.accountId),
    year: date.year,
    month: date.month,
  };

  const { transactionListData } = useGetTransactionList(initTransactionData); // 특정 날짜 조회
  const { getCalendarInfoData } = useGetCalendarInfo(initCalendarData);

  const updateDate = (newDate: DateType) => setDate({ ...newDate });
  const selectCalendarDate = (select: DateType) => setSelectedDate(select);

  return (
    <S.OtherUserWrapper>
      <OtherUserInfo getOtherAccountInfoData={getOtherAccountInfoData} userId={userId!} />
      <Calendar updateDate={updateDate} selectCalendarDate={selectCalendarDate} calendarList={getCalendarInfoData} />
      <TransactionList transactionDatas={transactionListData} />
    </S.OtherUserWrapper>
  );
};

export default OtherUser;
