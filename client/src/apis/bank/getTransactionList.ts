import { axiosInstance } from '@/apis';
import { GetTransactionListResponse } from '@/types/bank';

export interface GetTransactionListRequest {
  accountId: number;
  startDate: string; // 2023-09-04
  endDate: string; // 2023-09-04
}

// 입/출금내역 조회
export const getTransactionList = async (inputTransaction: GetTransactionListRequest) => {
  const { data } = await axiosInstance.get<GetTransactionListResponse>('/transactions', {
    params: { ...inputTransaction },
  });
  return data;
};
