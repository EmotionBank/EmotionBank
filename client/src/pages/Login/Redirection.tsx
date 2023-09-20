// 리다이렉트 페이지
import { useNavigate } from 'react-router-dom';
import { useEffect } from 'react';
import { loginUser } from '@/apis/user/loginUser';
const Redirection = () => {
  const code = window.location.search.substring(6);
  const navigate = useNavigate();

  useEffect(() => {
    // 서버로 post 요청 후 받은 응답에서 닉네임이 없으면 회원가입 페이지로 navigate
    const login = async () => {
      const response = await loginUser(code);
      console.log(response);
      localStorage.setItem('accessToken', response.accessToken);
      if (response.role === 'PENDING') {
        // 닉네임 있으면 메인 페이지로 navigate
        navigate('/signup');
      } else {
        navigate('/');
      }
    };
    login();
  }, []);
  return <div>로그인 중입니다.</div>;
};

export default Redirection;
