import { postCategory } from '@/apis/category/postCategory';
import useModal from '@/hooks/useModal';
import { useMutation } from '@tanstack/react-query';

export const usePostCategory = () => {
  const { closeModal } = useModal('createCategory');

  const postCategoryMutation = useMutation({
    mutationFn: postCategory,
    onSuccess: () => {
      alert('카테고리 생성 완료');
      closeModal();
    },
    onError: () => {},
  });
  return postCategoryMutation;
};
