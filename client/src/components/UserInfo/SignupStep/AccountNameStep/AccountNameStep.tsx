import { useInput } from '@/hooks/useInput';
import * as S from '@/components/transaction/TransferModal/TransferModal.style';
import { Button } from '@/components/common/Button/Button';

import { NextButton } from '../AgreementStep/AgreementStep.style';

interface IProps {
  onNext: (accountName: string) => void;
}

const AccountNameStep = ({ onNext }: IProps) => {
  const [content, handleContent] = useInput('');

  return (
    <>
      <S.TransferModalWrapper>
        <S.TransferModalTitle>계좌 이름을 설정해주세요</S.TransferModalTitle>
        <S.AmountInput style={{ width: '100%' }} type="text" name="account" onChange={handleContent} value={content} />
        <S.SubmitButton onClick={() => onNext(content)}>확인</S.SubmitButton>
      </S.TransferModalWrapper>
    </>
  );
};

export default AccountNameStep;
