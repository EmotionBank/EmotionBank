import { axiosInstance } from '@/apis';
import { AccountBalanceType } from '@/types/bank';

// 계좌 잔액 조회
export const getAccountBalance = async (accountId: string) => {
  const { data } = await axiosInstance.post<AccountBalanceType>('/accounts/balance', accountId);
  return data;
};
