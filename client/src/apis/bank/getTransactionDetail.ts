import { axiosInstance } from '@/apis';
import { TransactionDetailType } from '@/types/bank';

export const getTransactionDetail = async (transactionId: number) => {
  const { data } = await axiosInstance.get<TransactionDetailType>(`/transactions/${transactionId}`);
  return data;
};
