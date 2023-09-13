import { axiosInstance } from '@/apis';
import { TransactionListData } from '@/types/bank';

export interface PostTransactionList {
  accountNumber: string;
  year: number;
  month: number;
}

// 입/출금내역 조회
export const getTransactionList = async (inputTransaction: PostTransactionList) => {
  const { data } = await axiosInstance.post<TransactionListData>('/transactions', inputTransaction);
  return data;
};
