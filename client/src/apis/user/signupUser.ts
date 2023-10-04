import { axiosInstance } from '@/apis/index';
import { PostSignupResponse } from '@/types/user';

export interface PostSignupRequest {
  nickname: string;
  birthday: string;
  accountName: string;
}

export const signupUser = async (userInfo: PostSignupRequest) => {
  const { data } = await axiosInstance.post<PostSignupResponse>('/signup', userInfo);
  return data;
};
