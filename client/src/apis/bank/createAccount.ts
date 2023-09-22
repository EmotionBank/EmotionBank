import { axiosInstance } from '@/apis';

export interface createAccountRequestType {
  accountName: string;
}
// 계좌 생성
export const createAccount = async (request: createAccountRequestType) => {
  const { data } = await axiosInstance.post('/account', request);
  return data;
};
