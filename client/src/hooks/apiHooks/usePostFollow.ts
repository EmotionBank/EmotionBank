import { postFollow } from '@/apis/user/postFollow';
import { useMutation, useQueryClient } from '@tanstack/react-query';

export const usePostFollow = (follow: boolean) => {
  const queryClient = useQueryClient();
  const postFollowMutation = useMutation({
    mutationFn: (userId: string) => postFollow(userId),
    onSuccess: () => {
      if (follow) alert('팔로우 취소 성공');
      else alert('팔로우 성공');
      queryClient.invalidateQueries(['otherAccountInfo']);
    },
  });

  return postFollowMutation;
};
