import { useIntersect } from '@/hooks/useIntersect';
import * as S from './Feed.style';
import { useMemo } from 'react';
import { useGetUserFeed } from '@/hooks/apiHooks/useGetUserFeed';
import { useInput } from '@/hooks/useInput';
import { searchUser } from '@/apis/user/searchUser';

const Feed = () => {
  // const { data, hasNextPage, isFetching, fetchNextPage } = useGetUserFeed();
  const [content, handleContent] = useInput('');
  const response = useGetUserFeed(0);
  const users = response.feedList.feeds;
  // const users = useMemo(() => (data ? data.pages.flatMap(({ data }) => data.contents) : []), [data]);
  // const ref = useIntersect(async (entry, observer) => {
  //   observer.unobserve(entry.target);
  //   if (hasNextPage && !isFetching) {
  //     fetchNextPage();
  //   }
  //   console.log('끝에 닿음')
  // });
  const handleSearch = async () => {
    const response = await searchUser(content);
    console.log(response);
  };

  return (
    <S.FeedWrapper>
      <S.SearchInput onChange={handleContent} value={content} />
      <button onClick={() => handleSearch()}>제출</button>
      <S.GridContainer>
        {users.map((user, index) => (
          <div key={index}>
            <S.Content>{user.nickname}</S.Content>
            <S.Content>{user.emoticon}</S.Content>
          </div>
        ))}
      </S.GridContainer>
      {/* {isFetching && <span>로딩중입니다...</span>}
      <S.Target ref={ref} /> */}
    </S.FeedWrapper>
  );
};

export default Feed;
