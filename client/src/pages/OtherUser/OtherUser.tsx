import { useState } from 'react';
import { useParams } from 'react-router-dom';
import * as S from '@/pages/OtherUser/OtherUser.style';
import { DateType } from '@/types/date';
import { getNewDateObj } from '@/utils/getNewDateObj';
import { convertYYYYMMDD } from '@/utils/convertDateToString';
import { useGetTransactionList } from '@/hooks/apiHooks/useGetTransactionList';
import { useGetCalendarInfo } from '@/hooks/apiHooks/useGetCalendarInfo';
import { useGetOtherAccountInfo } from '@/hooks/apiHooks/useGetOtherAccountInfo';

const OtherUser = () => {
  const { userId } = useParams();

  const [date, setDate] = useState<DateType>({ ...getNewDateObj(new Date()) });
  const [selectedDate, setSelectedDate] = useState<DateType>(getNewDateObj(new Date())); // 거래내역 조회 시 사용
  const { getOtherAccountInfoData } = useGetOtherAccountInfo(userId!);
  console.log(getOtherAccountInfoData);
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

  // const { transactionListData } = useGetTransactionList(initTransactionData); // 특정 날짜 조회
  // const { getCalendarInfoData } = useGetCalendarInfo(initCalendarData);

  return <S.OtherUserWrapper></S.OtherUserWrapper>;
};

export default OtherUser;
