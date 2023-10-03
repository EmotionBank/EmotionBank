import { useIntersect } from '@/hooks/useIntersect';
import * as S from './Feed.style';
import {useMemo} from 'react'
import { useGetUserFeed } from '@/hooks/apiHooks/useGetUserFeed';

const Feed = () => {
  const PAGE_SIZE = Math.ceil((visualViewport) ? visualViewport.width / 10 : 0) // 73개
  const { data, hasNextPage, isFetching, fetchNextPage } = useGetUserFeed(PAGE_SIZE);

  const users = useMemo(() => (data ? data.pages.flatMap(({ data }) => data.contents) : []), [data]);
  const ref = useIntersect(async (entry, observer) => {
    observer.unobserve(entry.target);
    if (hasNextPage && !isFetching) {
      fetchNextPage();
    }
    console.log('끝에 닿음')
  });
  return (
    <S.FeedWrapper>
      <S.GridContainer>
        {users.map(user => (
          <div>
            <S.Content>{user.nickname}</S.Content>
            <S.Content>{user.emoticon}</S.Content>
          </div>
        ))}
      </S.GridContainer>
      {isFetching && <span>로딩중입니다...</span>}
      <S.Target ref={ref} />
    </S.FeedWrapper>
  );
};

export default Feed;