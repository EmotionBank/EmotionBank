import { useState } from 'react';
import * as S from '@/components/transaction/TransferModal/TransferModal.style';
import { positiveEmotionImageList } from '@/constants/emotions';

const TransferModal = () => {
  const [selectedEmotion, setSelectedEmotion] = useState('');

  return (
    <S.TransferModalWrapper>
      <S.EmotionContainer>
        {Object.entries(positiveEmotionImageList).map(([key, value]) => (
          <S.EmotionImageContainer key={key} onClick={() => setSelectedEmotion(key)} $clicked={key === selectedEmotion}>
            {value}
            {key}
          </S.EmotionImageContainer>
        ))}
      </S.EmotionContainer>
    </S.TransferModalWrapper>
  );
};

export default TransferModal;
