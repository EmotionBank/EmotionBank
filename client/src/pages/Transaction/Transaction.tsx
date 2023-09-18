import { PostDepositTransaction } from '@/apis/bank/postTransaction';
import * as S from '@/pages/Transaction/Transaction.style';
import { useState } from 'react';

const Transaction = () => {
  const initRequestdata = {
    transactionType: 'string', // DEPOSIT | WITHDRAWL
    categoryId: '',
    amount: 0,
    balance: 0,
    emotion: 'happy',
    content: 'string',
    visibility: 'PUBLIC',
    transactionDate: new Date(),
  };
  const [step, setStep] = useState<'emotion' | 'diary'>('emotion');
  const [requsetData, setRequestData] = useState<PostDepositTransaction>(initRequestdata);

  return (
    <S.TransactionWrapper>
      {step === 'emotion' && <></>}
      {step === 'diary' && <></>}
    </S.TransactionWrapper>
  );
};

export default Transaction;
