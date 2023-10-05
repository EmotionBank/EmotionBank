import { signupUser } from '@/apis/user/signupUser';
import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { useRecoilState } from 'recoil';
import { signupStep } from '@/recoils/atom';
import AgreementStep from '@/components/UserInfo/SignupStep/AgreementStep/AgreementStep';
import NicknameStep from '@/components/UserInfo/SignupStep/NicknameStep/NicknameStep';
import BirthdayStep from '@/components/UserInfo/SignupStep/BirthdayStep/BirthdayStep';
import AccountNameStep from '@/components/UserInfo/SignupStep/AccountNameStep/AccountNameStep';
import { putAgreement } from '@/apis/Terms/putAgreement';

interface Agreement {
  termsId: number;
  state: string;
}

const Signup = () => {
  const [step, setStep] = useRecoilState(signupStep);
  const initSignupRequest = {
    nickname: '',
    birthday: '',
    accountName: '',
  };
  const [request, setRequest] = useState(initSignupRequest);

  const navigate = useNavigate();

  useEffect(() => {
    // if (index === 4) {
    //   console.log(inputs);
    //   const birthday = inputs[2];
    //   const userInfo = {
    //     // 'agree': inputs[0],
    //     nickname: inputs[1],
    //     birthday: birthday.substring(0, 4) + '-' + birthday.substring(4, 6) + '-' + birthday.substring(6, 8),
    //     accountName: inputs[3],
    //   };
    //   const response = signupUser(userInfo);
    //   console.log(response);
    //   navigate('/');
    // }
  }, [step]);

  // const handleInput = (e: React.ChangeEvent<HTMLInputElement>) => {
  //   let inputValue = e.target.value;

  //   const allowedCharactersPattern = /^[0-9a-zA-Zㄱ-ㅎ가-힣]*$/;
  //   console.log(allowedCharactersPattern.test(inputValue));
  //   if (!allowedCharactersPattern.test(inputValue)) {
  //     const filteredValue = inputValue.replace(/[^0-9a-zA-Zㄱ-ㅎ가-힣]/g, '');
  //     inputValue = filteredValue;
  //     alert('특수문자를 제외한 숫자, 영어, 한글을 적어주세요');
  //   }
  //   setInputs(prevInputs => {
  //     const updatedInputs = [...prevInputs];
  //     updatedInputs[index] = inputValue;
  //     return updatedInputs;
  //   });
  // };

  const confirmAgreementStep = (content: boolean[]) => {
    console.log(content);
    const newAgreementRequest: Agreement[] = [];
    content.map((value, index) => {
      if (value) {
        newAgreementRequest.push({
          termsId: index + 1,
          state: 'ACTIVE',
        });
      } else {
        newAgreementRequest.push({
          termsId: index + 1,
          state: 'REJECT',
        });
      }
    });
    console.log(newAgreementRequest);
    const requestBody = { requests: newAgreementRequest };
    const response = putAgreement(requestBody);
    console.log(response);
    setStep('nickname');
  };
  const confirmNicknameStep = (nickname: string, isChecked: boolean) => {
    if (!isChecked) {
      alert('중복 확인해주세요.');
    } else if (nickname === '') {
      alert('내용을 입력해주세요.');
    } else {
      setRequest(prev => ({ ...prev, nickname }));
      console.log(request);
      setStep('birthday');
    }
  };

  const confirmBirthdayStep = day => {
    const originalDate = new Date(day);
    const birthday = `${originalDate.getFullYear()}-${(originalDate.getMonth() + 1)
      .toString()
      .padStart(2, '0')}-${originalDate.getDate().toString().padStart(2, '0')}`;
    console.log(birthday);
    const birthdayPattern = /^(19|20)\d\d-(0[1-9]|1[0-2])-([0-2][1-9]|3[0-1])$/;
    if (birthdayPattern.test(birthday)) {
      setRequest(prev => ({ ...prev, birthday }));
      setStep('accountName');
    } else {
      alert('올바른 생일 형식이 아닙니다. (예: 2000-01-01)');
    }
  };

  const confirmAccountNameStep = async (accountName: string) => {
    if (accountName === '') {
      alert('내용을 입력해주세요.');
    } else {
      const updatedRequest = { ...request, accountName };
      try {
        const response = await signupUser(updatedRequest);
        console.log(response);
        navigate('/');
      } catch (error) {
        console.log('회원가입 요청 중 오류 발생:', error);
      }
    }
  };
  return (
    <>
      {step === 'agreement' && <AgreementStep onNext={confirmAgreementStep} />}
      {step === 'nickname' && <NicknameStep onNext={confirmNicknameStep} />}
      {step === 'birthday' && <BirthdayStep onNext={confirmBirthdayStep} />}
      {step === 'accountName' && <AccountNameStep onNext={confirmAccountNameStep} />}
    </>
  );
};

export default Signup;
