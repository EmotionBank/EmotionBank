import { postFollow } from '@/apis/user/postFollow';
import { useMutation, useQueryClient } from '@tanstack/react-query';

export const usePostFollow = () => {
  const queryClient = useQueryClient();
  const postFollowMutation = useMutation({
    mutationFn: (userId: string) => postFollow(userId),
    onSuccess: () => {
      alert('팔로우 성공');
      queryClient.invalidateQueries(['otherAccountInfo']);
    },
  });

  return postFollowMutation;
};
