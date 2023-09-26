import { axiosInstance } from '@/apis';
import { DepositTransactionType } from '@/types/bank';

// 계좌 입출금 (본인에게 +/-)
export interface PostDepositTransaction {
  transactionType: string; // DEPOSIT | WITHDRAWL
  categoryId: number;
  accountNumber: string;
  balance: number;
  amount: number;
  emoticon: string;
  content: string;
}
export const postTransaction = async (transactionData: PostDepositTransaction) => {
  const { data } = await axiosInstance.post<DepositTransactionType>('/transactions', transactionData);
  return data;
};
