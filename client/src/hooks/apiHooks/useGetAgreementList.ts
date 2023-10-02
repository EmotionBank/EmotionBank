import { getAgreement } from '@/apis/Terms/getAgreement';
import { useQuery } from '@tanstack/react-query';

export const useGetTermList = () => {
  const { data } = useQuery(['termList'], getAgreement);
  return { getTermsListData: data! };
};
