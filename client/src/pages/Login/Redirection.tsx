// 리다이렉트 페이지
import { useLocation, useNavigate } from 'react-router-dom';
import { useEffect } from 'react';
import { loginUser } from '@/apis/user/loginUser';
import { PATH } from '@/constants/path';
import { postFCMToken } from '@/apis/user/postFCMToken';
const Redirection = () => {
  const { search } = useLocation();
  const code = search.substring(6);
  const navigate = useNavigate();

  useEffect(() => {
    const postoken = async (token: string) => {
      const response = await postFCMToken(token);
    };
    // 서버로 post 요청 후 받은 응답에서 닉네임이 없으면 회원가입 페이지로 navigate
    const login = async () => {
      const response = await loginUser(code);
      localStorage.setItem('accessToken', response.accessToken);
      postoken(localStorage.getItem('FCM_token')!);
      if (response.role === 'PENDING') {
        window.location.replace(PATH.SIGNUP);
        return;
      }
      window.location.replace(PATH.ROOT);
    };
    login();
  }, []);
  return <div>로그인 중입니다.</div>;
};

export default Redirection;
