import { axiosInstance } from '@/apis/index';
import { LoginType } from '@/types/user';

export const loginUser = async (code: string) => {
  const { data } = await axiosInstance.get<LoginType>(`/auth/login/kakao/callback?code=${code}`);
  return data;
};
