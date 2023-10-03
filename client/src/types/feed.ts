export interface UserFeed {
  nickname: string;
  emoticon: string;
  userId: number;
}

export interface getUserFeedResponse {
    userFeedList : UserFeed[]
}