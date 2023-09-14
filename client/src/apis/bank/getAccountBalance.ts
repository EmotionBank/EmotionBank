import { axiosInstance } from '@/apis';
import { AccountBalanceType } from '@/types/bank';

// 계좌 잔액 조회
export const getAccountBalance = async (accountNumber: string) => {
  const { data } = await axiosInstance.post<AccountBalanceType>('/accounts/balance', accountNumber);
  return data;
};
