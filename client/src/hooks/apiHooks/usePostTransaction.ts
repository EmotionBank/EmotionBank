import { PostDepositTransaction, postTransaction } from '@/apis/bank/postTransaction';
import { PATH } from '@/constants/path';
import { useMutation } from '@tanstack/react-query';

export const usePostTransaction = () => {
  const postTransactionMutation = useMutation({
    mutationFn: (requestData: PostDepositTransaction) => postTransaction(requestData),
    onSuccess: () => {
      console.log('FINISH');
      window.location.replace(PATH.ROOT);
    },
    onError: () => {},
  });

  return postTransactionMutation;
};
