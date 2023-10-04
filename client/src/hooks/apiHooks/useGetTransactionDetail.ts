import { useQuery } from '@tanstack/react-query';
import { getTransactionDetail } from '@/apis/bank/getTransactionDetail';

export const useGetTransactionDetail = (transactionId: number) => {
  const { data } = useQuery(['transactionDetail', transactionId], () => getTransactionDetail(transactionId));
  return { transactionDetailData: data! };
};
