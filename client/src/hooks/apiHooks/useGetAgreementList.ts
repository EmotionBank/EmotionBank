import { getAgreement } from '@/apis/terms/getAgreement';
import { useQuery } from '@tanstack/react-query';

export const useGetTermList = () => {
  const { data } = useQuery(['termList'], getAgreement);
  return { getTermsListData: data! };
};
