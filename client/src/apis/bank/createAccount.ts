import { axiosInstance } from '@/apis';

// 계좌 생성
export const createAccount = async (accountName: string) => {
  const { data } = await axiosInstance.post('/account', accountName);
  return data;
};
