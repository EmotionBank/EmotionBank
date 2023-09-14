import * as S from '@/components/transaction/TransactionList/TransactionList.style';
import { TransactionType, TransactionListType } from '@/types/bank';

const TransactionList = ({ arr }: { arr: TransactionListType }) => {
  return (
    <S.TransactionListWrapper>
      {arr?.transactions.map((item: TransactionType) => (
        <S.TransactionListContainer key={item.transactionId}>{item.title}</S.TransactionListContainer>
      ))}
    </S.TransactionListWrapper>
  );
};

export default TransactionList;
