export interface NicknameResponseType {
  possible: boolean;
}

export interface SignupType {
  grantType: string;
  accessToken: string;
  accessTokenExpireTime: string;
  nickname: string;
}

export interface LoginType {
  grantType: string;
  accessToken: string;
  accessTokenExpireTime: string;
  userId?: number;
  role?: string;
  nickname?: string;
}
