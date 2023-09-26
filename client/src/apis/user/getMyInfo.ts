import { axiosInstance } from '@/apis/index';

export interface UserInfoType {
  userId: number;
  nickname: string;
  birthday: string;
  accountNumber: string;
  accountName: string;
}

export const getMyInfo = async () => {
  const { data } = await axiosInstance.get<UserInfoType>('/users/me/info');
  return data;
};
