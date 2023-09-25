import { axiosInstance } from '@/apis/index';

export interface UserInfoType {
  nickname: string;
  birthday: string;
  accountNumber: string;
  accountName: string;
}

export const getUserInfo = async () => {
  const { data } = await axiosInstance.get<UserInfoType>('/users/me/info');
  return data;
};
