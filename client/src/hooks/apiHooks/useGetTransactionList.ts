import { GetTransactionListRequest, getTransactionList } from '@/apis/bank/getTransactionList';
import { useQuery } from '@tanstack/react-query';

export const useGetTransactionList = (inputTransaction: GetTransactionListRequest) => {
  const { data } = useQuery(['transactionList', inputTransaction], () => getTransactionList(inputTransaction), {
    keepPreviousData: true,
  });
  return { transactionListData: data! };
};
