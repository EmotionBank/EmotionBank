import * as S from '@/components/transaction/TransactionList/TransactionList.style';
import { PATH } from '@/constants/path';
import { TransactionType, TransactionListType } from '@/types/bank';
import { useNavigate } from 'react-router-dom';

interface Props {
  transactionDatas: TransactionListType;
}

const TransactionList = ({ transactionDatas }: Props) => {
  const navigate = useNavigate();

  const handleNavigate = () => {};
  // item.type에 따라 +- 설정하는 함수 필요
  return (
    <S.TransactionListWrapper>
      {transactionDatas?.transactions.map((item: TransactionType) => (
        <S.TransactionListContainer
          key={item.transactionId}
          onClick={() => {
            navigate(PATH.DETAIL(item.transactionId));
          }}
        >
          <S.EmotionImage src="" />
          <span>{item.title}</span>
          <span>{item.amount}</span>
        </S.TransactionListContainer>
      ))}
    </S.TransactionListWrapper>
  );
};

export default TransactionList;
