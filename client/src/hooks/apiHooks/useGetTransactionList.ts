import { getTransactionList } from '@/apis/bank/getTransactionList';
import { useMutation } from '@tanstack/react-query';

export const useGetTransactionList = () => {
  const getTransactionListMutation = useMutation({
    mutationFn: getTransactionList,
    onSuccess: () => {},
    onError: () => {},
  });
  return getTransactionListMutation;
};
