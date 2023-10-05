import * as S from '@/components/transaction/TransactionList/TransactionList.style';
import { PATH } from '@/constants/path';
import { TransactionResponse, GetTransactionListResponse } from '@/types/bank';
import { filteredImage } from '@/utils/filterImage';
import { setMoneyRegex } from '@/utils/regex';
import { useCallback } from 'react';
import { useNavigate } from 'react-router-dom';

interface Props {
  transactionDatas: GetTransactionListResponse;
}

const TransactionList = ({ transactionDatas }: Props) => {
  const navigate = useNavigate();
  const handleNavigate = () => {};
  // item.type에 따라 +- 설정하는 함수 필요
  const checkTitle = useCallback((title: string | null) => {
    if (title === null) return '송금했어요';
    return title;
  }, []);

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
          <S.TransactionTitle>{checkTitle(item.title)}</S.TransactionTitle>
          <S.TransactionAmount>{setMoneyRegex(String(item.amount))}</S.TransactionAmount>
        </S.TransactionListContainer>
      ))}
    </S.TransactionListWrapper>
  );
};

export default TransactionList;
