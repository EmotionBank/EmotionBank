import * as S from '@/components/transaction/TransactionList/TransactionList.style';
import { TransactionType, TransactionListType } from '@/types/bank';

interface Props {
  transactionDatas: TransactionListType;
}

const TransactionList = ({ transactionDatas }: Props) => {
  // item.type에 따라 +- 설정하는 함수 필요
  return (
    <S.TransactionListWrapper>
      {transactionDatas?.transactions.map((item: TransactionType) => (
        <S.TransactionListContainer key={item.transactionId}>
          <S.EmotionImage src="" />
          <span>{item.title}</span>
          <span>{item.money}</span>
        </S.TransactionListContainer>
      ))}
    </S.TransactionListWrapper>
  );
};

export default TransactionList;
