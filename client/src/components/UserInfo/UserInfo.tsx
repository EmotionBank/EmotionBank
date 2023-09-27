import * as S from '@/components/UserInfo/UserInfo.style';
import { PATH } from '@/constants/path';
import { useGetUserAccount } from '@/hooks/apiHooks/useGetUserAccount';
import gloomy from '@assets/emotions/gloomy.png';
import { useNavigate } from 'react-router-dom';

const UserInfo = () => {
  const navigate = useNavigate();
  // const getUserAccountInfoData = useGetUserAccount();

  const dummy = {
    nickname: '닉네임',
    balance: 200000,
    accountId: 1,
    accountNumber: '110-222-123112',
    following: 10,
    follower: 10,
  };
  return (
    <S.UserInfoWrapper>
      <S.UserInfoTop>
        <S.InfoContainer>
          <S.profileImage src={gloomy} />
          <S.InfoTextContainer>
            <S.NicknameInfo>{dummy.nickname}</S.NicknameInfo>
            <span>{dummy.accountNumber}</span>
          </S.InfoTextContainer>
        </S.InfoContainer>
        <S.FollowContainer>
          <S.FollowingInfo>
            <span>팔로잉</span>
            <span>{dummy.following}</span>
          </S.FollowingInfo>
          <S.FollowerInfo>
            <span>팔로워</span>
            <span>{dummy.follower}</span>
          </S.FollowerInfo>
        </S.FollowContainer>
      </S.UserInfoTop>
      <S.UserInfoBottom>
        <span>{dummy.balance}원</span>
        <S.TransactionButton onClick={() => navigate(PATH.TRANSACTION)}>입/출금</S.TransactionButton>
      </S.UserInfoBottom>
    </S.UserInfoWrapper>
  );
};

export default UserInfo;
