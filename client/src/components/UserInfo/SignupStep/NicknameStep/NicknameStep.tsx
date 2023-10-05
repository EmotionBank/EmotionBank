// import * as S from './NicknameStep.style';
import { useState } from 'react';
import { nicknameCheck } from '@/apis/user/nicknameCheck';
import { Button } from '@/components/common/Button/Button';
import { useInput } from '@/hooks/useInput';
import * as S from '@/components/UserInfo/SignupStep/NicknameStep/NicknameStep.style';

interface IProps {
  onNext: (nickname: string, isChecked: boolean) => void;
}
const NicknameStep = ({ onNext }: IProps) => {
  const [content, handleContent] = useInput('');
  const [check, setCheck] = useState(false);

  const handleNicknameCheck = async () => {
    if (content === '') {
      alert('내용을 입력해주세요.');
      return;
    }
    try {
      const response = await nicknameCheck(content);
      if (response.possible) {
        alert('해당 닉네임을 사용할 수 있습니다.');
        setCheck(true);
      } else {
        alert('해당 닉네임이 이미 존재합니다.');
      }
    } catch (error) {
      console.log('Error:', error);
    }
  };

  return (
    <S.NicknameWrapper>
      <S.NicknameTitle>닉네임을 입력해주세요</S.NicknameTitle>
      <S.InputWrapper>
        <S.StyledInput type="text" name="nickname" onChange={handleContent} value={content} />
        <S.StyledButton onClick={handleNicknameCheck}>중복검사</S.StyledButton>
      </S.InputWrapper>
      <S.NextButton onClick={() => onNext(content, check)}>다음</S.NextButton>
    </S.NicknameWrapper>
  );
};

export default NicknameStep;
