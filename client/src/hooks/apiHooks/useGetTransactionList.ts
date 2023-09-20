import { PostTransactionList, getTransactionList } from '@/apis/bank/getTransactionList';
import { useQuery } from '@tanstack/react-query';

export const useGetTransactionList = (inputTransaction: PostTransactionList) => {
  const getTransactionListMutation = useQuery(['transactionList'], () => getTransactionList(inputTransaction));
  return getTransactionListMutation;
};
