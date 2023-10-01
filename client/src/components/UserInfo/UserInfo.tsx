import * as S from '@/components/UserInfo/UserInfo.style';
import { PATH } from '@/constants/path';
import { useGetUserAccount } from '@/hooks/apiHooks/useGetUserAccount';
import { setMoneyRegex } from '@/utils/regex';
import Gloomy from '@assets/emotions/gloomy.svg';
import { useNavigate } from 'react-router-dom';

const UserInfo = () => {
  const navigate = useNavigate();
  const { getUserAccountInfoData } = useGetUserAccount();

  return (
    <S.UserInfoWrapper>
      <S.UserInfoTop>
        <S.InfoContainer>
          {/* <S.profileImage src={gloomy} /> */}
          <Gloomy />
          <S.InfoTextContainer>
            <S.NicknameInfo>{getUserAccountInfoData.nickname}</S.NicknameInfo>
            <span>{getUserAccountInfoData.accountNumber}</span>
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
        <span>{setMoneyRegex(getUserAccountInfoData.balance)}원</span>
        <S.TransactionButton onClick={() => navigate(PATH.TRANSACTION)}>입/출금</S.TransactionButton>
      </S.UserInfoBottom>
    </S.UserInfoWrapper>
  );
};

export default UserInfo;
