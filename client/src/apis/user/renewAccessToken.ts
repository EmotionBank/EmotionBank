import { axiosInstance } from '..';

export interface RenewAccessTokenType {
  tokenType: string;
  accessToken: string;
}

export const renewAccessToken = async () => {
  const { data } = await axiosInstance.get<RenewAccessTokenType>('/auth/token');
  return data;
};
