import { PATH } from '@/constants/path';
import { isLoginState } from '@/recoils/atom';
import { ReactNode, useLayoutEffect } from 'react';
import { useSetRecoilState } from 'recoil';

interface LogInProps {
  children: ReactNode;
}

const LoginCheck = ({ children }: LogInProps) => {
  const setIsLoginState = useSetRecoilState(isLoginState);

  useLayoutEffect(() => {
    if (localStorage.getItem('accessToken')) {
      setIsLoginState(true);
    }
  }, [setIsLoginState]);

  return children;
};

export default LoginCheck;
