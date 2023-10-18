import * as S from '@/pages/Login/Login.style';
import { KakaoLoginButton } from './Login.style';
import Logo from '@assets/emotions/exciting.svg';

const Login = () => {
  const REST_API_KEY = process.env.REACT_APP_REST_API_KEY;
  const REDIRECT_URI = process.env.REACT_APP_REDIRECT;
  const link = `https://kauth.kakao.com/oauth/authorize?client_id=${REST_API_KEY}&redirect_uri=${REDIRECT_URI}&response_type=code`;

  const handleLogin = () => {
    window.location.href = link;
  };

  return (
    <S.LoginWrapper>
      <S.MainImg>
        <Logo />
        Emotion Bank
      </S.MainImg>
      <KakaoLoginButton onClick={handleLogin}>카카오로 로그인 하기</KakaoLoginButton>
    </S.LoginWrapper>
  );
};

export default Login;
