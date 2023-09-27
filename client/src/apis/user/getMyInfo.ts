import { axiosInstance } from '@/apis/index';
import { GetMyInfoResponse } from '@/types/user';

export const getMyInfo = async () => {
  const { data } = await axiosInstance.get<GetMyInfoResponse>('/users/me/info');
  return data;
};
