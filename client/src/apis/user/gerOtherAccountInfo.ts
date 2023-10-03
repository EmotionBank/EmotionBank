import { axiosInstance } from '@/apis';
import { GetOtherAccountInfoResponse } from '@/types/user';

export const getOtherAccountInfo = async (userId: string) => {
  const { data } = await axiosInstance.get<GetOtherAccountInfoResponse>(`/users/info/${userId}`);
  return data;
};
