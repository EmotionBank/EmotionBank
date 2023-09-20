import * as S from '@/components/transaction/TransactionStep/CommentStep/CommentStep.style';
import { useState } from 'react';
import happy from '@/assets/png/happy.png';
import { useInput } from '@/hooks/useInput';

interface IProps {
  onNext: (amount: number, content: string) => void;
  emotion: string;
}

const CommentStep = ({ onNext, emotion }: IProps) => {
  const [amount, handleAmount] = useInput('0');
  const [content, handleContent] = useInput('');

  return (
    <S.CommentStepWrapper>
      <S.EmotionImageContainer>
        <S.EmotionImage src={happy} />
      </S.EmotionImageContainer>
      <S.BalanceSpan>50,000</S.BalanceSpan>
      <S.LabelContainer>
        <S.Inputlabel>금액</S.Inputlabel>
        <S.AmountInput placeholder="금액을 입력하세요." onChange={handleAmount} />
      </S.LabelContainer>
      <S.LabelContainer>
        <S.Inputlabel>내용</S.Inputlabel>
        <S.CommentTextArea placeholder="내용을 입력하세요." onChange={handleContent} />
      </S.LabelContainer>
      <S.NextButton onClick={() => onNext(Number(amount), content)}>작성 완료</S.NextButton>
    </S.CommentStepWrapper>
  );
};

export default CommentStep;
