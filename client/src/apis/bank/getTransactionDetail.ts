import { axiosInstance } from '@/apis';
import { GetTransactionDetailResponse } from '@/types/bank';

// 계좌내역 상세조회
export const getTransactionDetail = async (transactionId: number) => {
  const { data } = await axiosInstance.get<GetTransactionDetailResponse>(`/transactions/${transactionId}`);
  return data;
};
