import { axiosInstance } from "@/apis/index";

export const nicknameCheck = async(nickname:string) => {
    const {data} = await axiosInstance.post('/users/check',nickname)
    return data
}