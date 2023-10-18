import { useState } from 'react';
import * as S from '@/components/transaction/TransactionStep/CommentStep/CommentStep.style';
import { useInput } from '@/hooks/useInput';
import { filteredImage } from '@/utils/filterImage';
import { replaceDash, setMoneyRegex } from '@/utils/regex';

interface IProps {
  onNext: (amount: number, content: string) => void;
  emoticon: string;
}

const CommentStep = ({ onNext, emoticon }: IProps) => {
  const [amount, handleAmount] = useInput('0');
  const [content, handleContent] = useInput('');
  const [transactionType, setTransactionType] = useState(''); // DEPOSIT | WITHDRAWL

  return (
    <S.CommentStepWrapper>
      <S.EmotionImageContainer>{filteredImage(emoticon)}</S.EmotionImageContainer>
      <S.LabelContainer>
        <S.Inputlabel>금액</S.Inputlabel>
        <S.AmountInput placeholder="금액을 입력하세요." onChange={handleAmount} value={setMoneyRegex(amount)} />
      </S.LabelContainer>
      <S.LabelContainer>
        <S.Inputlabel>내용</S.Inputlabel>
        <S.CommentTextArea placeholder="내용을 입력하세요." onChange={handleContent} />
      </S.LabelContainer>
      <S.NextButton onClick={() => onNext(Number(replaceDash(amount)), content)}>카테고리 설정</S.NextButton>
    </S.CommentStepWrapper>
  );
};

export default CommentStep;
