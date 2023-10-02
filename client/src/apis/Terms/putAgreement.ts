import { AgreementResponse } from '@/types/agreement';
import { axiosInstance } from '..';

interface putAgreementRequest {
  requests: Agreement[];
}

interface Agreement {
  termsId: number;
  state: string;
}

export const putAgreement = async (requests: putAgreementRequest) => {
  const { data } = await axiosInstance.put<AgreementResponse[]>('/agreement', requests);
  return data;
};
