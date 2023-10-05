import FollowListModal from '@/components/FollowListModal/FollowListModal';
import * as S from '@/components/OtherUserInfo/OtherUserInfo.style';
import Modal from '@/components/common/Modal/Modal';
import TransferModal from '@/components/transaction/TransferModal/TransferModal';
import { usePostFollow } from '@/hooks/apiHooks/usePostFollow';
import useModal from '@/hooks/useModal';
import { GetOtherAccountInfoResponse } from '@/types/user';
import EmotionBankLogo from '@assets/emotionbank_logo.png';

interface OtherUserInfoProps {
  getOtherAccountInfoData: GetOtherAccountInfoResponse;
  userId: string;
}

const OtherUserInfo = ({ getOtherAccountInfoData, userId }: OtherUserInfoProps) => {
  const { openModal: openTransferModal } = useModal('transfer');
  const { openModal: openFollowModal } = useModal('follow');
  const postFollowMutation = usePostFollow(getOtherAccountInfoData.follow);

  const handleFollowUser = () => {
    postFollowMutation.mutate(String(getOtherAccountInfoData.userId));
  };
  return (
    <>
      <S.OtherUserInfoWrapper>
        <S.OtherUserInfoTop>
          <S.InfoContainer>
            <S.Logoimage src={EmotionBankLogo} />
            <S.NicknameInfo>{getOtherAccountInfoData.nickname}</S.NicknameInfo>
          </S.InfoContainer>
          <S.FollowContainer onClick={openFollowModal}>
            <S.FollowingInfo>
              <span>팔로잉</span>
              <span>{getOtherAccountInfoData.following}</span>
            </S.FollowingInfo>
            <S.FollowerInfo>
              <span>팔로워</span>
              <span>{getOtherAccountInfoData.follower}</span>
            </S.FollowerInfo>
          </S.FollowContainer>
        </S.OtherUserInfoTop>
        <S.OtherUserInfoBottom>
          <S.TransactionButton onClick={openTransferModal}>이체</S.TransactionButton>
          {getOtherAccountInfoData.follow ? (
            <S.TransactionButton onClick={handleFollowUser}>팔로우 취소</S.TransactionButton>
          ) : (
            <S.TransactionButton onClick={handleFollowUser}>팔로우</S.TransactionButton>
          )}
        </S.OtherUserInfoBottom>
      </S.OtherUserInfoWrapper>
      <Modal id="transfer">
        <TransferModal userId={userId} />
      </Modal>
      <Modal id="follow">
        <FollowListModal userId={userId} />
      </Modal>
    </>
  );
};

export default OtherUserInfo;
