import { useInput } from '@/hooks/useInput';
import * as S from '@/components/UserInfo/SignupStep/BirthdayStep/BirthdayStep.style';
import { Button } from '@/components/common/Button/Button';

import { NextButton } from '../AgreementStep/AgreementStep.style';

interface IProps {
  onNext: (accountName: string) => void;
}

const AccountNameStep = ({ onNext }: IProps) => {
  const [content, handleContent] = useInput('');

  return (
    <S.BirthdayWrapper>
      <S.BirthdayTitle>계좌 이름을 설정해주세요</S.BirthdayTitle>
      <S.BirthdayBody>
        <S.StyledInput style={{ width: '100%' }} type="text" name="account" onChange={handleContent} value={content} />
      </S.BirthdayBody>
      <S.StyledButton onClick={() => onNext(content)}>확인</S.StyledButton>
    </S.BirthdayWrapper>
  );
};

export default AccountNameStep;
