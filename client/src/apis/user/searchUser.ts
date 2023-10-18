import { GetSearchUserResponse } from '@/types/user';
import { axiosInstance } from '..';

export const searchUser = async (nickname: string) => {
  const { data } = await axiosInstance.get<GetSearchUserResponse>('/users/search', { params: { nickname: nickname } });
  return data;
};
