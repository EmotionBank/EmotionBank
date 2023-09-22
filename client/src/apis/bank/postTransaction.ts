import { axiosInstance } from '@/apis';
import { DepositTransactionType } from '@/types/bank';

// 계좌 입출금 (본인에게 +/-)
export interface PostDepositTransaction {
  transactionType: string; // DEPOSIT | WITHDRAWL
  categoryId: string;
  amount: number;
  balance: number;
  emotion: string;
  content: string;
  visibility: string; // PRIVATE | PUBLIC | FOLLOWER
  transactionDate: Date;
}
export const postTransaction = async (transactionData: PostDepositTransaction) => {
  const { data } = await axiosInstance.post<DepositTransactionType>('/transactions', transactionData);
  return data;
};