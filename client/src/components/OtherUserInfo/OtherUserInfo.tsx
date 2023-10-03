import * as S from '@/components/OtherUserInfo/OtherUserInfo.style';
import { GetOtherAccountInfoResponse } from '@/types/user';
import EmotionBankLogo from '@assets/emotionbank_logo.png';

interface OtherUserInfoProps {
  getOtherAccountInfoData: GetOtherAccountInfoResponse;
}

const OtherUserInfo = ({ getOtherAccountInfoData }: OtherUserInfoProps) => {
  return (
    <S.OtherUserInfoWrapper>
      <S.OtherUserInfoTop>
        <S.InfoContainer>
          <S.Logoimage src={EmotionBankLogo} />
          <S.NicknameInfo>{getOtherAccountInfoData.nickname}</S.NicknameInfo>
        </S.InfoContainer>
        <S.FollowContainer>
          <S.FollowingInfo>
            <span>팔로잉</span>
            <span>{getOtherAccountInfoData.following}</span>
          </S.FollowingInfo>
          <S.FollowerInfo>
            <span>팔로워</span>
            <span>{getOtherAccountInfoData.following}</span>
          </S.FollowerInfo>
        </S.FollowContainer>
      </S.OtherUserInfoTop>
      <S.OtherUserInfoBottom>
        <S.TransactionButton>이체</S.TransactionButton>
        <S.TransactionButton>팔로우</S.TransactionButton>
      </S.OtherUserInfoBottom>
    </S.OtherUserInfoWrapper>
  );
};

export default OtherUserInfo;
