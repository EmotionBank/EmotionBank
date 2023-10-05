import { useIntersect } from '@/hooks/useIntersect';
import * as S from './Feed.style';
import { useGetUserFeed } from '@/hooks/apiHooks/useGetUserFeed';
import { useInput } from '@/hooks/useInput';
import { searchUser } from '@/apis/user/searchUser';
import useModal from '@/hooks/useModal';
import Modal from '@/components/common/Modal/Modal';
import { useState, useEffect } from 'react';
import { SearchUser } from '@/types/user';
import { useNavigate } from 'react-router-dom';
import { PATH } from '@/constants/path';
import { filteredImage } from '@/utils/filterImage';
import SearchIcon from '@mui/icons-material/Search';
import { setEmotionName } from '@/utils/setEmotionName';
import emotionLogo from '@assets/emotionbank_logo.png';
const Feed = () => {
  const [content, handleContent] = useInput('');
  const navigate = useNavigate();
  const response = useGetUserFeed(0);
  const users = response.feedList.feeds;
  const [emptyState, setEmptyState] = useState(true);
  const [searchResult, setSearchResult] = useState<SearchUser[] | null>([]);
  const { openModal, closeModal } = useModal('searchUser');
  const handleSearch = async () => {
    const { users } = await searchUser(content);
    console.log(users);
    setSearchResult(users);
    openModal();
  };
  useEffect(() => {
    if (content === '') {
      setEmptyState(true);
    } else {
      setEmptyState(false);
    }
  }, [content]);

  const handleOtherProfile = (id: number) => {
    closeModal();
    navigate(PATH.OTHER_USER(id));
  };
  const createRandomNumber = () => Math.floor(Math.random() * (4 - 2 + 1)) + 2;
  return (
    <>
      <S.FeedWrapper>
        <S.SearchBody>
          <S.SearchInput onChange={handleContent} value={content} placeholder="유저 닉네임으로 검색" />
          <S.SearchButton onClick={() => handleSearch()} disabled={emptyState}>
            <SearchIcon fontSize="large" />
          </S.SearchButton>
        </S.SearchBody>
        <S.GridContainer>
          {users.map((user, index) => (
            <S.FeedBody key={index} onClick={() => navigate(PATH.OTHER_USER(user.userId))}>
              <S.EmotionImageContainer $speed={createRandomNumber()}>
                {filteredImage(user.emoticon)}
              </S.EmotionImageContainer>
              <span>{setEmotionName(user.emoticon)}</span>
              <S.FeedContent>{user.nickname}</S.FeedContent>
            </S.FeedBody>
          ))}
        </S.GridContainer>
      </S.FeedWrapper>
      <Modal id="searchUser">
        <S.SearchResultBody>
          {searchResult?.length === 0 ? (
            <div>검색 결과가 없어요.</div>
          ) : (
            <div>
              <div>{searchResult?.length}개의 검색 결과</div>
              {searchResult?.map((user, index) => (
                <S.SearchResultItem key={index} onClick={() => handleOtherProfile(user.userId)}>
                  <S.SearchResultImg src={emotionLogo} />
                  <span>{user.nickname}</span>
                </S.SearchResultItem>
              ))}
            </div>
          )}
        </S.SearchResultBody>
      </Modal>
    </>
  );
};

export default Feed;
