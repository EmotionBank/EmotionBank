import { PostDepositTransaction } from '@/apis/bank/postTransaction';
import EmotionStep from '@/components/transaction/TransactionStep/EmotionStep/EmotionStep';
import * as S from '@/pages/Transaction/Transaction.style';
import { useState } from 'react';

const Transaction = () => {
  const initRequestdata = {
    transactionType: 'string', // DEPOSIT | WITHDRAWL
    categoryId: '',
    amount: 0,
    balance: 0,
    emotion: '',
    content: 'string',
    visibility: 'PUBLIC',
    transactionDate: new Date(),
  };
  const [step, setStep] = useState<'emotion' | 'comment'>('emotion');
  const [requsetData, setRequestData] = useState<PostDepositTransaction>(initRequestdata);

  const confirmEmotionStep = (newData: string) => {
    setRequestData(prev => ({ ...prev, emotion: newData }));
    setStep('comment');
  };

  return (
    <S.TransactionWrapper>
      {step === 'emotion' && <EmotionStep onNext={confirmEmotionStep} />}
      {step === 'comment' && <h1>dd</h1>}
    </S.TransactionWrapper>
  );
};

export default Transaction;
