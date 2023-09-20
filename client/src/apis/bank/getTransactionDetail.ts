import { axiosInstance } from '@/apis';
import { TransactionDetailType } from '@/types/bank';

// 계좌내역 상세조회
export const getTransactionDetail = async (transactionId: number) => {
  const { data } = await axiosInstance.get<TransactionDetailType>(`/transactions/${transactionId}`);
  return data;
};
