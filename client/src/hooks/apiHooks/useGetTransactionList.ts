import { GetTransactionListRequest, getTransactionList } from '@/apis/bank/getTransactionList';
import { useQuery } from '@tanstack/react-query';

export const useGetTransactionList = (inputTransaction: GetTransactionListRequest) => {
  const { data } = useQuery(['transactionList'], () => getTransactionList(inputTransaction));
  return { transactionListData: data! };
};
