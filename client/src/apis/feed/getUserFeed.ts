import { getUserFeedResponse } from '@/types/feed';
import { axiosInstance } from '..';

export const getUserFeed = async (pageParam: number, size: number) => {
  const { data } = await axiosInstance.get('/users/feed', { params: { page: pageParam, size } });
  return data;
};
