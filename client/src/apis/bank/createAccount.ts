import { axiosInstance } from '@/apis';

export interface CreateAccountRequestType {
  accountName: string;
}
// 계좌 생성
export const createAccount = async (request: CreateAccountRequestType) => {
  const { data } = await axiosInstance.post('/account', request);
  return data;
};
