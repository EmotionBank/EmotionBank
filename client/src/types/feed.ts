export interface UserFeed {
  nickname: string;
  emoticon: string;
  userId: number;
}

interface FeedList {
  feeds: UserFeed[];
}

export interface getUserFeedResponse {
  feedList: FeedList;
}
