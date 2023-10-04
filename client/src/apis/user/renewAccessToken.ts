import { axiosInstance } from '..';
import { GetRenewAccessTokenResponse } from '@/types/user';

export const renewAccessToken = async () => {
  const { data } = await axiosInstance.get<GetRenewAccessTokenResponse>('/auth/token');
  return data;
};
