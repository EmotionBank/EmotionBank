import { axiosInstance } from '@/apis';

export interface getUserAccountInfoType {
  nickname: string;
  accountId: number;
  accountNumber: string;
  balance: number;
  following: number;
  follower: number;
}

export const getUserAccountInfo = async () => {
  const { data } = await axiosInstance.get<getUserAccountInfoType>('/users/info/me');
  return data;
};
