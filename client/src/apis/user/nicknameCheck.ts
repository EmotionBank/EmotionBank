import { axiosInstance } from "@/apis/index";
import { NicknameCheckType } from "@/types/user";
export const nicknameCheck = async(nickname:string) => {
    const {data} = await axiosInstance.post<NicknameCheckType> ('/users/check',nickname)
    return data
}