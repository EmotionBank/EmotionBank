import * as S from '@/components/UserInfo/UserInfo.style';
import gloomy from '@assets/emotions/gloomy.png';

const UserInfo = () => {
  const dummy = {
    nickname: '닉네임',
    balance: 200000,
    accountNumber: '110-222-123112',
    following: 10,
    follower: 10,
  };
  return (
    <S.UserInfoWrapper>
      <S.InfoContainer>
        <S.profileImage src={gloomy} />
        <S.InfoTextContainer>
          <S.NicknameInfo>{dummy.nickname}</S.NicknameInfo>
          <span>{dummy.balance}</span>
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
    </S.UserInfoWrapper>
  );
};

export default UserInfo;
