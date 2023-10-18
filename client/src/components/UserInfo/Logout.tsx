import { logoutUser } from '@/apis/user/logoutUser';
import { Button } from '@/components/common/Button/Button';
import { PATH } from '@/constants/path';
import { useNavigate } from 'react-router-dom';
import styled from 'styled-components';

export const Logout = () => {
  const handleLogout = async () => {
    await logoutUser();
    localStorage.clear();
    window.location.replace(PATH.ROOT);
  };
  return <LogoutButton onClick={handleLogout}>로그아웃</LogoutButton>;
};

const LogoutButton = styled(Button)`
  width: 80%;
  background-color: ${({ theme }) => theme.color.red};
  color: white;
  font-weight: bold;
`;
