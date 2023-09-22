import * as S from '@/components/transaction/TransactionStep/EmotionStep/EmotionStep.style';
import { emotionImageList, emotionList } from '@/constants/emotions';
import { useState } from 'react';

interface IProps {
  onNext: (newData: string) => void;
}

const EmotionStep = ({ onNext }: IProps) => {
  const [selectedEmotion, setSelectedEmotion] = useState('');

  return (
    <S.EmotionStepWrapper>
      <S.EmotionHeader>오늘의 감정을 선택하세요!</S.EmotionHeader>
      <S.EmotionGrid>
        {Object.entries(emotionImageList).map(([key, value]) => (
          <S.EmotionImageContainer key={key} onClick={() => setSelectedEmotion(key)} $clicked={key === selectedEmotion}>
            <S.EmotionImage src={value} />
          </S.EmotionImageContainer>
        ))}
      </S.EmotionGrid>
      <S.NextButton onClick={() => onNext(selectedEmotion)}>다음</S.NextButton>
    </S.EmotionStepWrapper>
  );
};

export default EmotionStep;
