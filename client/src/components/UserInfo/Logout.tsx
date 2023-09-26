import { logoutUser } from '@/apis/user/logoutUser';
import { useNavigate } from 'react-router-dom';

export const Logout = () => {
  const navigate = useNavigate();
  const handleLogout = async() => {
    await logoutUser();
    localStorage.clear();
    navigate('/login');
  };
  return <a onClick={handleLogout}>로그아웃</a>;
};
