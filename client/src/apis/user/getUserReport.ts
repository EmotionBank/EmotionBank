import { axiosInstance } from '@/apis/index';
import { GetUserReportResponse } from '@/types/user';

export const getUserReport = async () => {
  const { data } = await axiosInstance.get<GetUserReportResponse>('/users/report');
  return data;
};
