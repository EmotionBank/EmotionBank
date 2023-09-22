import { useState } from 'react';
import { PostDepositTransaction } from '@/apis/bank/postTransaction';
import CommentStep from '@/components/transaction/TransactionStep/CommentStep/CommentStep';
import EmotionStep from '@/components/transaction/TransactionStep/EmotionStep/EmotionStep';
import * as S from '@/pages/Transaction/Transaction.style';
import { usePostTransaction } from '@/hooks/apiHooks/usePostTransaction';

const Transaction = () => {
  const initRequestdata = {
    transactionType: 'string', // DEPOSIT | WITHDRAWL
    categoryId: '',
    accountNumber: '110-315-123456',
    amount: 0,
    balance: 0,
    emotion: '',
    content: 'string',
  };
  const [step, setStep] = useState<'emotion' | 'comment'>('emotion');
  const [requsetData, setRequestData] = useState<PostDepositTransaction>(initRequestdata);
  const postTransactionMutation = usePostTransaction();

  const confirmEmotionStep = (newData: string) => {
    setRequestData(prev => ({ ...prev, emotion: newData }));
    setStep('comment');
  };
  const confirmDiaryStep = (amount: number, content: string) => {
    postTransactionMutation.mutate({ ...requsetData, amount, content });
  };

  return (
    <S.TransactionWrapper>
      {step === 'emotion' && <EmotionStep onNext={confirmEmotionStep} />}
      {step === 'comment' && <CommentStep onNext={confirmDiaryStep} emotion={requsetData.emotion} />}
    </S.TransactionWrapper>
  );
};

export default Transaction;
