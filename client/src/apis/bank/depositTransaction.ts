import { axiosInstance } from '@/apis';
import { DepositTransactionData } from '@/types/bank';

// 계좌 입금 (본인에게 +/-)
export interface PostDepositTransaction {
  transactionType: string;
  categoryId: string;
  title: string;
  sender: string;
  receiver: string;
  amount: number;
  balance: number;
  emotion: string;
  content: string;
  visibility: string;
  transactionDate: Date;
}
export const depositTransaction = async (transactionData: PostDepositTransaction) => {
  const { data } = await axiosInstance.post<DepositTransactionData>('/accounts/deposit', transactionData);
  return data;
};
