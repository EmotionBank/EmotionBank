import { useState } from 'react';
import * as S from '@/components/FollowListModal/FollowListModal.style';
import { useGetFolloweeList } from '@/hooks/apiHooks/useGetFolloweeList';
import { useGetFollowerList } from '@/hooks/apiHooks/useGetFollowerList';
import emotionBank_logo from '@assets/emotionbank_logo.png';
import { useNavigate } from 'react-router-dom';
import { PATH } from '@/constants/path';

interface FollowListModalProps {
  userId: string;
}

const FollowListModal = ({ userId }: FollowListModalProps) => {
  const navigate = useNavigate();
  const [selected, setSelected] = useState('FOLLOWER');
  const { getFollowerListData } = useGetFollowerList(userId);
  const { getFolloweeListData } = useGetFolloweeList(userId);
  const listData = selected === 'FOLLOWER' ? getFollowerListData?.follows : getFolloweeListData?.follows;

  const handleNavigate = (userId: string | number) => {
    navigate(PATH.OTHER_USER(userId));
  };

  return (
    <S.FollowListModalWrapper>
      <S.FollowListModalHeader>
        <S.FollowSelectButton $isSelected={selected === 'FOLLOWING'} onClick={() => setSelected('FOLLOWING')}>
          팔로잉
        </S.FollowSelectButton>
        <S.FollowSelectButton $isSelected={selected === 'FOLLOWER'} onClick={() => setSelected('FOLLOWER')}>
          팔로워
        </S.FollowSelectButton>
      </S.FollowListModalHeader>
      <S.ItemContainer>
        {listData?.map(item => (
          <S.FollowListModalItem key={item.nickname} onClick={() => handleNavigate(item.userId)}>
            {item.image ? item.image : <S.FollowImg src={emotionBank_logo} />}
            <span>{item.nickname}</span>
          </S.FollowListModalItem>
        ))}
      </S.ItemContainer>
    </S.FollowListModalWrapper>
  );
};

export default FollowListModal;
