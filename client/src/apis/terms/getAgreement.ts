import { axiosInstance } from '@/apis/index';
import { GetAgreementListResponse } from '@/types/agreement';

export const getAgreement = async () => {
  const { data } = await axiosInstance.get<GetAgreementListResponse>('/agreement');
  return data;
};
