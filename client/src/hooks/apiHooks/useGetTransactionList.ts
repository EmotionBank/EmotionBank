import { PostTransactionList, getTransactionList } from '@/apis/bank/getTransactionList';
import { useQuery } from '@tanstack/react-query';

export const useGetTransactionList = (inputTransaction: PostTransactionList) => {
  const { data } = useQuery(['transactionList'], () => getTransactionList(inputTransaction));
  return { transactionListData: data! };
};
