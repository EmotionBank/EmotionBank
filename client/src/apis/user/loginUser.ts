import { axiosInstance } from '@/apis/index';
import { GetLoginResponse } from '@/types/user';

export const loginUser = async (code: string) => {
  const { data } = await axiosInstance.get<GetLoginResponse>(`/auth/login/kakao/callback?code=${code}`);
  return data;
};
