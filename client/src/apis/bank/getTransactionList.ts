import { axiosInstance } from '@/apis';
import { TransactionListType } from '@/types/bank';

export interface PostTransactionList {
  accountId: number;
  startDate: string; // 2023-09-04
  endDate: string; // 2023-09-04
}

// 입/출금내역 조회
export const getTransactionList = async (inputTransaction: PostTransactionList) => {
  const { data } = await axiosInstance.get<TransactionListType>('/transactions', { params: { ...inputTransaction } });
  return data;
};
