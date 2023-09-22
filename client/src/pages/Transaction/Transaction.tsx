import { useState } from 'react';
import { PostDepositTransaction } from '@/apis/bank/postTransaction';
import CommentStep from '@/components/transaction/TransactionStep/CommentStep/CommentStep';
import EmotionStep from '@/components/transaction/TransactionStep/EmotionStep/EmotionStep';
import * as S from '@/pages/Transaction/Transaction.style';
import { usePostTransaction } from '@/hooks/apiHooks/usePostTransaction';
import { positiveEmotion } from '@/constants/emotions';

const Transaction = () => {
  const initRequestdata = {
    transactionType: 'WITHDRAW', // DEPOSIT | WITHDRAW
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

  const isPositiveEmotion = (emotion: string) => positiveEmotion.includes(emotion);
  const validateTransaction = (amount: number, content: string) => {
    if (amount === 0 || content === '') return false;
    return true;
  };

  const confirmEmotionStep = (emotion: string) => {
    if (isPositiveEmotion(emotion)) {
      setRequestData(prev => ({ ...prev, transactionType: 'DEPOSIT' }));
    }
    setRequestData(prev => ({ ...prev, emotion: emotion }));
    setStep('comment');
  };
  const confirmDiaryStep = (amount: number, content: string) => {
    if (!validateTransaction(amount, content)) return;
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
