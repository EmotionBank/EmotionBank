import * as S from '@/components/UserInfo/UserInfo.style';
import { PATH } from '@/constants/path';
import { GetUserAccountInfoResponse } from '@/types/user';
import { setMoneyRegex } from '@/utils/regex';
import { useNavigate } from 'react-router-dom';
import EmotionBankLogo from '@assets/emotionbank_logo.png';

interface UserInfoProps {
  getUserAccountInfoData: GetUserAccountInfoResponse;
}

const UserInfo = ({ getUserAccountInfoData }: UserInfoProps) => {
  const navigate = useNavigate();

  return (
    <S.UserInfoWrapper>
      <S.UserInfoTop>
        <S.InfoContainer>
          <S.Logoimage src={EmotionBankLogo} />
          <S.InfoTextContainer>
            <S.NicknameInfo>{getUserAccountInfoData.nickname}</S.NicknameInfo>
            <S.AccountNumberInfo>{getUserAccountInfoData.accountNumber}</S.AccountNumberInfo>
          </S.InfoTextContainer>
        </S.InfoContainer>
        <S.FollowContainer>
          <S.FollowingInfo>
            <span>팔로잉</span>
            <span>{getUserAccountInfoData.following}</span>
          </S.FollowingInfo>
          <S.FollowerInfo>
            <span>팔로워</span>
            <span>{getUserAccountInfoData.follower}</span>
          </S.FollowerInfo>
        </S.FollowContainer>
      </S.UserInfoTop>
      <S.UserInfoBottom>
        <span>{setMoneyRegex(String(getUserAccountInfoData.balance))}원</span>
        <S.TransactionButton onClick={() => navigate(PATH.TRANSACTION)}>입/출금</S.TransactionButton>
      </S.UserInfoBottom>
    </S.UserInfoWrapper>
  );
};

export default UserInfo;
