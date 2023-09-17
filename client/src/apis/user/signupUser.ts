import { axiosInstance } from "@/apis/index";

export interface UserInfo {
    agree: string;
    nickname: string;
    birthday: string;
    account: string;
}

export const signupUser =async (userInfo:UserInfo) => {
    const {data} = await axiosInstance.post('/signup',userInfo)
    return data
}