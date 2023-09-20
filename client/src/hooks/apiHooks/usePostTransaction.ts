import { PostDepositTransaction, postTransaction } from '@/apis/bank/postTransaction';
import { PATH } from '@/constants/path';
import { useMutation } from '@tanstack/react-query';
import { useNavigate } from 'react-router-dom';

export const usePostTransaction = () => {
  const navigate = useNavigate();
  const postTransactionMutation = useMutation({
    mutationFn: (requestData: PostDepositTransaction) => postTransaction(requestData),
    onSuccess: () => navigate(PATH.ROOT),
    onError: () => {},
  });

  return postTransactionMutation;
};
