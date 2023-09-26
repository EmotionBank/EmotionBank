import * as S from '@/components/transaction/TransactionStep/CommentStep/CommentStep.style';
import { useInput } from '@/hooks/useInput';
import { emotionImageList } from '@/constants/emotions';

interface IProps {
  onNext: (amount: number, content: string) => void;
  emoticon: string;
}

const CommentStep = ({ onNext, emoticon }: IProps) => {
  const [amount, handleAmount] = useInput('0');
  const [content, handleContent] = useInput('');
  console.log(emoticon);
  const filteredImage = Object.entries(emotionImageList).filter(([key, value]) => key === emoticon)[0][1];

  return (
    <S.CommentStepWrapper>
      <S.EmotionImageContainer>
        <S.EmotionImage src={filteredImage} />
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
      <S.NextButton onClick={() => onNext(Number(amount), content)}>카테고리 설정</S.NextButton>
    </S.CommentStepWrapper>
  );
};

export default CommentStep;
