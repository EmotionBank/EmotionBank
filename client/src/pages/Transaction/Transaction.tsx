import { useState } from 'react';
import { PostDepositTransaction } from '@/apis/bank/postTransaction';
import CommentStep from '@/components/transaction/TransactionStep/CommentStep/CommentStep';
import EmotionStep from '@/components/transaction/TransactionStep/EmotionStep/EmotionStep';
import * as S from '@/pages/Transaction/Transaction.style';

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
  const [step, setStep] = useState<'emotion' | 'comment'>('comment');
  const [requsetData, setRequestData] = useState<PostDepositTransaction>(initRequestdata);

  const confirmEmotionStep = (newData: string) => {
    setRequestData(prev => ({ ...prev, emotion: newData }));
    setStep('comment');
  };
  const confirmDiaryStep = (amount: number, content: string) => {
    setRequestData(prev => ({ ...prev, amount, content }));
  };

  return (
    <S.TransactionWrapper>
      {step === 'emotion' && <EmotionStep onNext={confirmEmotionStep} />}
      {step === 'comment' && <CommentStep onNext={confirmDiaryStep} emotion={requsetData.emotion} />}
    </S.TransactionWrapper>
  );
};

export default Transaction;
