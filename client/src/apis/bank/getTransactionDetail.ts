import { axiosInstance } from '@/apis';
import { TransactionDetailData } from '@/types/bank';

export const getTransactionDetail = async (transactionId: number) => {
  const { data } = await axiosInstance.get<TransactionDetailData>(`/transactions/${transactionId}`);
  return data;
};
