import { PostTransactionOtherRequest, postTransactionOther } from '@/apis/bank/postTransactionOther';
import { useMutation } from '@tanstack/react-query';

export const usePostTransactionOther = () => {
  const postTransactionOtherMutation = useMutation({
    mutationFn: (requestData: PostTransactionOtherRequest) => postTransactionOther(requestData),
    onSuccess: () => {
      alert('송금이 완료되었습니다.');
    },
    onError: () => {
      alert('송금이 실패했습니다.');
    },
  });
  return postTransactionOtherMutation;
};
