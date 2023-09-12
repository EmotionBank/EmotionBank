import { Button } from "@/components/common/Button/Button";
const Login = () => {
  const REST_API_KEY = 'a940fabccd5638b1030355abd7d38b20'
  const REDIRECT_URI = 'http://localhost:3000/redirection'
  const link = `https://kauth.kakao.com/oauth/authorize?client_id=${REST_API_KEY}&redirect_uri=${REDIRECT_URI}&response_type=code`;

  const handleLogin = () => {
    window.location.href = link;
  };

  return (
    <Button onClick={handleLogin}>
      카카오로 로그인 하기
    </Button>
  );
};

export default Login;
