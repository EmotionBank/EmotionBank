import * as S from '@/components/transaction/TransactionStep/EmotionStep/EmotionStep.style';
import { emotionImageList, emotionList } from '@/constants/emotions';
import { useState } from 'react';
import happy from '@assets/emotions/happy.png';

interface IProps {
  onNext: (newData: string) => void;
}

const EmotionStep = ({ onNext }: IProps) => {
  const [selectedEmotion, setSelectedEmotion] = useState('');
  console.log(selectedEmotion);
  return (
    <S.EmotionStepWrapper>
      <S.EmotionHeader>오늘의 감정을 선택하세요!</S.EmotionHeader>
      <S.EmotionGrid>
        {emotionImageList.map(emotion => {
          const emotionKey = Object.keys(emotion)[0];
          const emotionValue = Object.values(emotion)[0];
          return (
            <S.EmotionImageContainer key={emotionKey} onClick={() => setSelectedEmotion(emotionKey)}>
              <S.EmotionImage src={emotionValue} />
            </S.EmotionImageContainer>
          );
        })}
      </S.EmotionGrid>
      <S.NextButton onClick={() => onNext(selectedEmotion)}>다음</S.NextButton>
    </S.EmotionStepWrapper>
  );
};

export default EmotionStep;
