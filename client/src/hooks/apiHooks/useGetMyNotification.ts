import { getMyNotification } from '@/apis/user/getNotification';
import { useQuery } from '@tanstack/react-query';

export const useGetMyNotification = () => {
  const { data } = useQuery(['myNotification'], getMyNotification);
  return { getMyNotificationData: data! };
};
