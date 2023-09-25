import { axiosInstance } from '@/apis/index';
import { NicknameResponseType } from '@/types/user';

export interface NicknameRequestType {
  nickname: string;
}
export const nicknameCheck = async (request: NicknameRequestType) => {
  const { data } = await axiosInstance.post<NicknameResponseType>('/users/check', request);
  return data;
};
