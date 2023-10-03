import { axiosInstance } from '@/apis';
import { PostTransactionOtherResponse } from '@/types/bank';

export interface PostTransactionOtherRequest {
  receiver: string; // userId
  amount: string;
  emoticon: string;
}

export const postTransactionOther = async (postData: PostTransactionOtherRequest) => {
  const { data } = await axiosInstance.post<PostTransactionOtherResponse>('/transactions/transfer', postData);
  return data;
};
