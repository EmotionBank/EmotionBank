import { getUserFeedResponse } from '@/types/feed';
import { axiosInstance } from '..';

export const getUserFeed = async (pageParam: number) => {
  const { data } = await axiosInstance.get<getUserFeedResponse>('/users/feed', { params: { page: pageParam } });
  return data;
};
