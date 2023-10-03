import { useState } from 'react';
import * as S from '@/components/transaction/TransferModal/TransferModal.style';
import { positiveEmotionImageList } from '@/constants/emotions';
import { setEmotionName } from '@/utils/setEmotionName';
import { replaceDash, setMoneyRegex } from '@/utils/regex';

const TransferModal = () => {
  const [selectedEmotion, setSelectedEmotion] = useState('0');
  const [amount, setAmount] = useState('');

  const handleSubmit = () => {};

  return (
    <S.TransferModalWrapper>
      <S.TransferModalTitle>전달할 감정을 선택하세요!</S.TransferModalTitle>
      <S.EmotionContainer>
        {Object.entries(positiveEmotionImageList).map(([key, value]) => (
          <S.EmotionImageContainer key={key} onClick={() => setSelectedEmotion(key)} $clicked={key === selectedEmotion}>
            {value}
            {setEmotionName(key)}
          </S.EmotionImageContainer>
        ))}
      </S.EmotionContainer>
      <S.AmountInput
        type="text"
        placeholder="전달할 금액을 입력하세요!"
        value={setMoneyRegex(amount)}
        onChange={e => setAmount(setMoneyRegex(e.target.value))}
      />
      <S.SubmitButton onClick={handleSubmit}>송금하기</S.SubmitButton>
    </S.TransferModalWrapper>
  );
};

export default TransferModal;
