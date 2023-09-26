import { axiosInstance } from '..';

export const logoutUser = async () => {
  await axiosInstance.post('/logout');
};
