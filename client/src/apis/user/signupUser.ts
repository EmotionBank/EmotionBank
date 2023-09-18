import { axiosInstance } from '@/apis/index';
import { SignupType } from '@/types/user';

export interface UserInfo {
  // agree: string;
  nickname: string;
  birthday: string;
}

export const signupUser = async (userInfo: UserInfo) => {
  const { data } = await axiosInstance.post<SignupType>('/signup', userInfo);
  return data;
};
