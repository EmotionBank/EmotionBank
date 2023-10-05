import { useState } from 'react';
import { PostTransactionRequest } from '@/apis/bank/postTransaction';
import CommentStep from '@/components/transaction/TransactionStep/CommentStep/CommentStep';
import EmotionStep from '@/components/transaction/TransactionStep/EmotionStep/EmotionStep';
import * as S from '@/pages/Transaction/Transaction.style';
import { usePostTransaction } from '@/hooks/apiHooks/usePostTransaction';
import { positiveEmotion } from '@/constants/emotions';
import CategoryStep from '@/components/transaction/TransactionStep/CategoryStep/CategoryStep';

const Transaction = () => {
  const initRequestdata = {
    transactionType: '', // DEPOSIT | WITHDRAW
    categoryId: 0,
    accountNumber: localStorage.getItem('accountNumber')!,
    amount: 0,
    balance: Number(localStorage.getItem('balance')!),
    emoticon: '',
    content: 'string',
  };
  const [step, setStep] = useState<'emotion' | 'comment' | 'category'>('emotion');
  const [requsetData, setRequestData] = useState<PostTransactionRequest>(initRequestdata);
  const postTransactionMutation = usePostTransaction();

  const isPositiveEmotion = (emoticon: string) => positiveEmotion.includes(emoticon);
  const validateTransaction = (amount: number, content: string, transactionType: string) => {
    if (amount === 0 || content === '' || transactionType === '') return false;
    return true;
  };

  const confirmEmotionStep = (emoticon: string) => {
    setRequestData(prev => ({ ...prev, emoticon }));
    setStep('comment');
  };

  const confirmDiaryStep = (amount: number, content: string, transactionType: string) => {
    if (!validateTransaction(amount, content, transactionType)) return;
    setStep('category');
    setRequestData(prev => ({ ...prev, amount, content, transactionType }));
  };

  const confirmCategoryStep = (categoryId: number) => {
    postTransactionMutation.mutate({ ...requsetData, categoryId });
  };

  return (
    <S.TransactionWrapper>
      {step === 'emotion' && <EmotionStep onNext={confirmEmotionStep} />}
      {step === 'comment' && <CommentStep onNext={confirmDiaryStep} emoticon={requsetData.emoticon} />}
      {step === 'category' && <CategoryStep onNext={confirmCategoryStep} />}
    </S.TransactionWrapper>
  );
};

export default Transaction;
