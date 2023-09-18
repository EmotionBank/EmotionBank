import { useParams } from 'react-router-dom';
import * as S from '@/pages/Detail/Detsil.style';
import { useGetTransactionDetail } from '@/hooks/apiHooks/useGetTransactionDetail';

const Detail = () => {
  const { transactionId } = useParams();
  // const transactionDetailData = useGetTransactionDetail(Number(transactionId));
  const transactionDetailData = {
    title: '제목',
    emotion: '감정',
    money: 10000,
    date: '2023-09-16',
    comment: '아무말이나 일단 써',
    accountNumber: '111-111-111',
    type: 'DEPOSIT',
  };
  return (
    <S.DetailWrapper>
      <S.ReceiptWrapper>
        <S.TransactionTitle>{transactionDetailData.title}</S.TransactionTitle>
        <S.EmotionImageContainer>
          <S.EmotionImage src="" alt="감정" />
        </S.EmotionImageContainer>
        <S.Money>{transactionDetailData.money}</S.Money>
        <S.EmotionContentContainer>
          <p>{transactionDetailData.comment}</p>
        </S.EmotionContentContainer>
        <S.EmotionInfoWrapper>
          <S.EmotionInfo>날짜</S.EmotionInfo>
          <S.EmotionInfo>{transactionDetailData.date}</S.EmotionInfo>
        </S.EmotionInfoWrapper>
        <S.EmotionInfoWrapper>
          <S.EmotionInfo>계좌 번호</S.EmotionInfo>
          <S.EmotionInfo>{transactionDetailData.accountNumber}</S.EmotionInfo>
        </S.EmotionInfoWrapper>
      </S.ReceiptWrapper>
      <S.SavePhotoButton>사진으로 저장하기</S.SavePhotoButton>
    </S.DetailWrapper>
  );
};

export default Detail;
