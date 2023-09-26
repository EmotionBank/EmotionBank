import { postCategory } from '@/apis/category/postCategory';
import { useMutation } from '@tanstack/react-query';

export const usePostCategory = () => {
  const postCategoryMutation = useMutation({
    mutationFn: postCategory,
    onSuccess: () => {
      alert('카테고리 생성 완료');
    },
    onError: () => {},
  });
  return postCategoryMutation;
};
