import { axiosInstance } from "@/apis/index"

export const loginUser = async (code:string) => {
    const {data} = await axiosInstance.post(`/login?code=${code}`)
    return data
}