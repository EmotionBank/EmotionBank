import { axiosInstance } from '@/apis/index';
import { LoginType } from '@/types/user';

export const loginUser = async (code: string) => {
  const { data } = await axiosInstance.post<LoginType>(`/login?code=${code}`);
  return data;
};
