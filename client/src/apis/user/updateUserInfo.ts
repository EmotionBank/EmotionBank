import { axiosInstance } from '@/apis/index';

export interface UpdateUserType {
  nickname: string;
}

export const updateUser = async (updateUser: UpdateUserType) => {
  const { data } = await axiosInstance.patch('/users', updateUser);
  return data;
};
