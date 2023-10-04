import { axiosInstance } from '@/apis';
import { GetUserAccountInfoResponse } from '@/types/user';

export const getUserAccountInfo = async () => {
  const { data } = await axiosInstance.get<GetUserAccountInfoResponse>('/users/info/me');
  return data;
};
