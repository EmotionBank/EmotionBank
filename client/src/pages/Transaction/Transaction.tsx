import * as S from '@/pages/Transaction/Transaction.style';
import { useState } from 'react';

const Transaction = () => {
  const [step, setStep] = useState<'emotion' | 'input'>('emotion');
  return <S.TransactionWrapper></S.TransactionWrapper>;
};

export default Transaction;
