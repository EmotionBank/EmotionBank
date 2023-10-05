import { getNotification } from '@/apis/user/getNotification';
import { useQuery } from '@tanstack/react-query';

export const useGetNotification = () => {
  const { data } = useQuery(['myNotification'], getNotification);
  return { getNotificationData: data };
};
