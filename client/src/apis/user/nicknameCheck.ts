import { axiosInstance } from '@/apis/index';
import { PostNicknameCheckResponse } from '@/types/user';

export const nicknameCheck = async (nickname: string) => {
  const { data } = await axiosInstance.post<PostNicknameCheckResponse>('/users/check', nickname);
  return data;
};
