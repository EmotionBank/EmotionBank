import { useInput } from '@/hooks/useInput';
import * as S from './AccountNameStep.style';
import { Button } from '@/components/common/Button/Button';

interface IProps {
  onNext: (accountName: string) => void;
}

const AccountNameStep = ({ onNext }: IProps) => {
  const [content, handleContent] = useInput('');

  return (
    <S.AccountNameWrapper>
      <S.AccountNameBody>
        <S.AccountNameTitle>계좌 이름을 설정해주세요</S.AccountNameTitle>
        <S.StyledInput type="text" name="account" onChange={handleContent} value={content} />
      </S.AccountNameBody>
      <Button onClick={() => onNext(content)}>확인</Button>
    </S.AccountNameWrapper>
  );
};

export default AccountNameStep;
