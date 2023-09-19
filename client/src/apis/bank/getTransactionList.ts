import { axiosInstance } from '@/apis';
import { TransactionListType } from '@/types/bank';

export interface PostTransactionList {
  accountNumber: string;
  date: string; // 2023-09-04
}

// 입/출금내역 조회
export const getTransactionList = async (inputTransaction: PostTransactionList) => {
  const { data } = await axiosInstance.post<TransactionListType>('/transactions', inputTransaction);
  return data;
};
