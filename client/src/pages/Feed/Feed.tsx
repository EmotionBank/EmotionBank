import { useIntersect } from '@/hooks/useIntersect';
import * as S from './Feed.style';
import { useGetUserFeed } from '@/hooks/apiHooks/useGetUserFeed';
import { useInput } from '@/hooks/useInput';
import { searchUser } from '@/apis/user/searchUser';
import useModal from '@/hooks/useModal';
import Modal from '@/components/common/Modal/Modal';
import { useState } from 'react';
import { SearchUser } from '@/types/user';
import { useNavigate } from 'react-router-dom';
import { PATH } from '@/constants/path';
import { filteredImage } from '@/utils/filterImage';
import SearchIcon from '@mui/icons-material/Search'

const Feed = () => {
  // const { data, hasNextPage, isFetching, fetchNextPage } = useGetUserFeed();
  const [content, handleContent] = useInput('');
  const navigate = useNavigate();
  const response = useGetUserFeed(0);
  const users = response.feedList.feeds;
  const [searchResult, setSearchResult] = useState<SearchUser[] | null>([]);
  const { openModal, closeModal } = useModal('searchUser');
  // const users = useMemo(() => (data ? data.pages.flatMap(({ data }) => data.contents) : []), [data]);
  // const ref = useIntersect(async (entry, observer) => {
  //   observer.unobserve(entry.target);
  //   if (hasNextPage && !isFetching) {
  //     fetchNextPage();
  //   }
  //   console.log('끝에 닿음')
  // });
  const handleSearch = async () => {
    const { users } = await searchUser(content);
    console.log(users);
    setSearchResult(users);
    openModal();
  };

  return (
    <>
      <S.FeedWrapper>
        <S.SearchBody>
        <S.SearchInput onChange={handleContent} value={content} placeholder='유저 닉네임으로 검색'/>
        <S.SearchButton onClick={() => handleSearch()}>
          <SearchIcon />
        </S.SearchButton>

        </S.SearchBody>
        <S.GridContainer>
          {users.map((user, index) => (
            <S.FeedBody key={index} onClick={() => navigate(PATH.OTHER_USER(user.userId))}>
              <S.EmotionImageContainer>{filteredImage(user.emoticon)}</S.EmotionImageContainer>
              <S.FeedContent>{user.nickname}</S.FeedContent>
            </S.FeedBody>
          ))}
        </S.GridContainer>
        {/* {isFetching && <span>로딩중입니다...</span>}
      <S.Target ref={ref} /> */}
      </S.FeedWrapper>
      <Modal id="searchUser">
        <div>
          {searchResult?.map((user, index) => (
            <S.SearchResultItem key={index} onClick={() => navigate(PATH.OTHER_USER(user.userId))}>
              {user.nickname}
            </S.SearchResultItem>
          ))}
        </div>
      </Modal>
    </>
  );
};

export default Feed;
