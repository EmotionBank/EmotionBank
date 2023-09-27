import { axiosInstance } from '@/apis';
import { PostTransactionResponse } from '@/types/bank';

// 계좌 입출금 (본인에게 +/-)
export interface PostTransactionRequest {
  transactionType: string; // DEPOSIT | WITHDRAWL
  categoryId: number;
  accountNumber: string;
  balance: number;
  amount: number;
  emoticon: string;
  content: string;
}
export const postTransaction = async (transactionData: PostTransactionRequest) => {
  const { data } = await axiosInstance.post<PostTransactionResponse>('/transactions', transactionData);
  return data;
};
