import { PostTransactionOtherRequest, postTransactionOther } from '@/apis/bank/postTransactionOther';
import useModal from '@/hooks/useModal';
import { useMutation } from '@tanstack/react-query';

export const usePostTransactionOther = () => {
  const { closeModal: closeTransferModal } = useModal('transfer');

  const postTransactionOtherMutation = useMutation({
    mutationFn: (requestData: PostTransactionOtherRequest) => postTransactionOther(requestData),
    onSuccess: () => {
      alert('송금이 완료되었습니다.');
      closeTransferModal();
    },
    onError: () => {
      alert('송금이 실패했습니다.');
      closeTransferModal();
    },
  });
  return postTransactionOtherMutation;
};
