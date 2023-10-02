import * as S from '@/components/transaction/TransactionList/TransactionList.style';
import { PATH } from '@/constants/path';
import { TransactionResponse, GetTransactionListResponse } from '@/types/bank';
import { filteredImage } from '@/utils/filterImage';
import { setMoneyRegex } from '@/utils/regex';
import { useNavigate } from 'react-router-dom';

interface Props {
  transactionDatas: GetTransactionListResponse;
}

const TransactionList = ({ transactionDatas }: Props) => {
  const navigate = useNavigate();
  const handleNavigate = () => {};
  // item.type에 따라 +- 설정하는 함수 필요

  return (
    <S.TransactionListWrapper>
      {transactionDatas.transactionInfoList.map((item: TransactionResponse) => (
        <S.TransactionListContainer
          key={item.transactionId}
          onClick={() => {
            navigate(PATH.DETAIL(item.transactionId));
          }}
        >
          <S.EmotionImage>{filteredImage(item.emoticon)}</S.EmotionImage>
          <span>{item.title}</span>
          <span>{setMoneyRegex(item.amount)}</span>
        </S.TransactionListContainer>
      ))}
    </S.TransactionListWrapper>
  );
};

export default TransactionList;
