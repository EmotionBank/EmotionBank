import { createAccount } from '@/apis/bank/createAccount';
import { nicknameCheck } from '@/apis/user/nicknameCheck';
import { signupUser } from '@/apis/user/signupUser';
import { Button } from '@components/common/Button/Button';
import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';

const Signup = () => {
  let [index, setIndex] = useState(0);
  const navigate = useNavigate();
  const [inputs, setInputs] = useState(['', '', '', '']);

  useEffect(() => {
    if (index === -1) {
      navigate('/login');
    }
    if (index === 3) {
      const userInfo = {
        // agree: inputs[0],
        nickname: inputs[1],
        birthday: inputs[2],
      };
      const response = signupUser(userInfo);
      console.log(response);
    }
    if (index === 4) {
      console.log(inputs);
      const response = createAccount(inputs[index - 1]);
      console.log(response);
      navigate('/');
    }
  }, [index]);

  const handleIndexBack = () => {
    setIndex(index - 1);
  };
  const handleIndexNext = () => {
    if (inputs[index] === '') {
      alert('내용을 입력해주세요.');
    } else {
      setIndex(index + 1);
    }
  };

  const handleInput = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;
    setInputs(prevInputs => {
      const updatedInputs = [...prevInputs];
      updatedInputs[index] = value;
      return updatedInputs;
    });
  };

  const handleNicknameCheck = async (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    console.log(inputs[index]);
    try {
      const response = await nicknameCheck(inputs[index]);

      if (response.isPossible) {
        alert('해당 닉네임을 사용할 수 있습니다.');
      } else {
        alert('해당 닉네임이 이미 존재합니다.');
      }
    } catch (error) {
      console.log('Error:', error);
    }
  };

  return (
    <>
      <span className="material-icons-outlined" onClick={handleIndexBack}>
        뒤로
      </span>
      {index === 0 && (
        <>
          <input type="checkbox" onChange={handleInput} />
          약관 동의 1
        </>
      )}
      {index === 1 && (
        <>
          <form onSubmit={handleNicknameCheck}>
            <input type="text" name="nickname" onChange={handleInput} value={inputs[index]} />
            <button type="submit">중복검사</button>
          </form>
        </>
      )}
      {index === 2 && (
        <>
          <input type="text" name="birthday" onChange={handleInput} value={inputs[index]} />
        </>
      )}
      {index === 3 && (
        <>
          <input type="text" name="account" onChange={handleInput} value={inputs[index]} />
        </>
      )}
      <Button onClick={handleIndexNext}>다음</Button>
    </>
  );
};

export default Signup;
