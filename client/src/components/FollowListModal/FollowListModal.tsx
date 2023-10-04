import * as S from '@/components/FollowListModal/FollowListModal.style';
import { useGetFolloweeList } from '@/hooks/apiHooks/useGetFolloweeList';
import { useGetFollowerList } from '@/hooks/apiHooks/useGetFollowerList';
import { useState } from 'react';

interface FollowListModalProps {
  userId: string;
}

const FollowListModal = ({ userId }: FollowListModalProps) => {
  const [selected, setSelected] = useState('FOLLOWER');
  const { getFollowerListData } = useGetFollowerList(userId);
  const { getFolloweeListData } = useGetFolloweeList(userId);
  console.log(getFolloweeListData?.follows);
  const listData = selected === 'FOLLOWER' ? getFollowerListData?.follows : getFolloweeListData?.follows;

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
          <S.FollowListModalItem key={item.nickname}>
            <span>{item.nickname}</span>
          </S.FollowListModalItem>
        ))}
      </S.ItemContainer>
    </S.FollowListModalWrapper>
  );
};

export default FollowListModal;
