import { axiosInstance } from '@/apis';
import { WithdrawTransactionData } from '@/types/bank';

// 계좌 출금
export interface PostWithdrawTransaction {
  title: string;
  emotion: string;
  money: number;
  date: Date;
  comment: string;
  accountNumber: string;
}
export const withdrawTransaction = async (transactionData: PostWithdrawTransaction) => {
  const { data } = await axiosInstance.post<WithdrawTransactionData>('/accounts/withdraw', transactionData);
  return data;
};
