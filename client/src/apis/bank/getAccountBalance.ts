import { axiosInstance } from '@/apis';
import { GetAccountBalanceTypeResponse } from '@/types/bank';

// 계좌 잔액 조회
export const getAccountBalance = async (accountId: string) => {
  const { data } = await axiosInstance.post<GetAccountBalanceTypeResponse>('/accounts/balance', accountId);
  return data;
};
