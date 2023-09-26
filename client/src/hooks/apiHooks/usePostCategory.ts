import { postCategory } from '@/apis/category/postCategory';
import { useMutation } from '@tanstack/react-query';

export const usePostCategory = () => {
  const postCategoryMutation = useMutation({
    mutationFn: postCategory,
    onSuccess: () => {},
    onError: () => {},
  });
  return postCategoryMutation;
};
