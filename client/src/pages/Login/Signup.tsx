import { createAccount } from '@/apis/bank/createAccount';
import { nicknameCheck } from '@/apis/user/nicknameCheck';
import { signupUser } from '@/apis/user/signupUser';
import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { NextButton, SignupTitle } from './Login.style';
import { useRecoilState } from 'recoil';
import { signupIndex } from '@/recoils/atom';
import * as S from './Signup.style';

const Signup = () => {
  const [index, setIndex] = useRecoilState(signupIndex);
  const [isChecked, setIsChecked] = useState(false);
  const navigate = useNavigate();
  const [inputs, setInputs] = useState(['', '', '', '']);

  useEffect(() => {
    if (index === -1) {
      navigate('/login');
    }

    if (index === 4) {
      console.log(inputs);
      const birthday = inputs[2];
      const userInfo = {
        // 'agree': inputs[0],
        nickname: inputs[1],
        birthday: birthday.substring(0, 4) + '-' + birthday.substring(4, 6) + '-' + birthday.substring(6, 8),
        accountName: inputs[3],
      };
      const response = signupUser(userInfo);
      console.log(response);
      navigate('/');
    }
  }, [index]);

  const handleIndexNext = () => {
    if (inputs[index] === '') {
      alert('내용을 입력해주세요.');
    } else {
      if (index === 1 && !isChecked) {
        alert('중복검사를 해주세요.');
      } else {
        setIndex(index + 1);
      }
    }
  };

  const handleInput = (e: React.ChangeEvent<HTMLInputElement>) => {
    let inputValue = e.target.value;

    const allowedCharactersPattern = /^[0-9a-zA-Zㄱ-ㅎ가-힣]*$/;
    console.log(allowedCharactersPattern.test(inputValue));
    if (!allowedCharactersPattern.test(inputValue)) {
      const filteredValue = inputValue.replace(/[^0-9a-zA-Zㄱ-ㅎ가-힣]/g, '');
      inputValue = filteredValue;
      alert('특수문자를 제외한 숫자, 영어, 한글을 적어주세요');
    }
    setInputs(prevInputs => {
      const updatedInputs = [...prevInputs];
      updatedInputs[index] = inputValue;
      return updatedInputs;
    });
  };

  const handleNicknameCheck = async (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    console.log(inputs[index]);
    try {
      const request = {
        nickname: inputs[index],
      };
      const response = await nicknameCheck(request);
      console.log(response);
      if (response.possible) {
        alert('해당 닉네임을 사용할 수 있습니다.');
        setIsChecked(true);
      } else {
        alert('해당 닉네임이 이미 존재합니다.');
      }
    } catch (error) {
      console.log('Error:', error);
    }
  };

  return (
    <>
      {index === 0 && (
        <>
          <input type="checkbox" onChange={handleInput} />
          약관 동의 1
        </>
      )}
      {index === 1 && (
        <>
          <SignupTitle>닉네임을 입력해주세요</SignupTitle>
          <form id="nickname-form" onSubmit={handleNicknameCheck}>
            <S.InputWrapper>
              <S.StyledInput type="text" name="nickname" onChange={handleInput} value={inputs[index]} />
              <S.StyledButton type="submit">중복검사</S.StyledButton>
            </S.InputWrapper>
          </form>
        </>
      )}
      {index === 2 && (
        <>
          <SignupTitle>생일을 입력해주세요</SignupTitle>
          <S.StyledInput
            type="text"
            name="birthday"
            onChange={handleInput}
            value={inputs[index]}
            placeholder="yyyyMMdd"
          />
        </>
      )}
      {index === 3 && (
        <>
          <SignupTitle>계좌 이름을 설정해주세요</SignupTitle>
          <input type="text" name="account" onChange={handleInput} value={inputs[index]} />
        </>
      )}
      <NextButton onClick={handleIndexNext}>다음</NextButton>
    </>
  );
};

export default Signup;
