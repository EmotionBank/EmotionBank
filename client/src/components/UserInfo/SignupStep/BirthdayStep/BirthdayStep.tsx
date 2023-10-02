import * as S from '@/components/UserInfo/SignupStep/BirthdayStep/BirthdayStep.style';
import { useInput } from '@/hooks/useInput';

interface IProps {
  onNext: (birthday: string) => void;
}

const BirthdayStep = ({ onNext }: IProps) => {
  const [content, handleContent] = useInput('');

  return (
    <S.BirthdayWrapper>
      <S.BirthdayTitle>생일을 입력해주세요.</S.BirthdayTitle>
      <S.StyledInput onChange={handleContent} value={content} placeholder="yyyy-MM-dd"></S.StyledInput>
      <S.StyledButton onClick={() => onNext(content)}>다음</S.StyledButton>
    </S.BirthdayWrapper>
  );
};

export default BirthdayStep;
