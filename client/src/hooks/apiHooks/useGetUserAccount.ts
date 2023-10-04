import { getUserAccountInfo } from '@/apis/user/getUserAccountInfo';
import { useQuery } from '@tanstack/react-query';

export const useGetUserAccount = () => {
  const { data } = useQuery(['userAccountInfo'], getUserAccountInfo);

  localStorage.setItem('nickname', data!.nickname);
  localStorage.setItem('accountNumber', data!.accountNumber);
  localStorage.setItem('accountId', String(data!.accountId));
  localStorage.setItem('balance', String(data!.balance));
  localStorage.setItem('userId', String(data!.userId));

  return { getUserAccountInfoData: data! };
};
