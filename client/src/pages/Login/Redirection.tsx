// 리다이렉트 페이지
import { useNavigate } from 'react-router-dom';
import { useState, useEffect } from 'react';
import axios from 'axios';
import { loginUser } from '@/apis/user/loginUser';
const Redirection = () => {
  const code = window.location.search.substring(6);
  const SERVER_URI = process.env.REACT_APP_SERVER_URI;
  const navigate = useNavigate();

  useEffect(() => {
    // 서버로 post 요청 후 받은 응답에서 닉네임이 없으면 회원가입 페이지로 navigate
    const login = async () => {
      const response = await loginUser(code);
      if (response.role === 'PENDING') {
        // 닉네임 있으면 메인 페이지로 navigate
        navigate('/signup');
      } else {
        navigate('/');
      }
    };
  }, []);
  return <div>로그인 중입니다.</div>;
};

export default Redirection;
