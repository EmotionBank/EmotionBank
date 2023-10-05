import { useParams } from 'react-router-dom';
import * as S from '@/pages/Detail/Detsil.style';
import { useGetTransactionDetail } from '@/hooks/apiHooks/useGetTransactionDetail';
import { filteredImage } from '@/utils/filterImage';
import { setMoneyRegex } from '@/utils/regex';
import { useRef } from "react";
import { toPng } from "html-to-image";

const Detail = () => {
  const { transactionId } = useParams();
  const { transactionDetailData } = useGetTransactionDetail(Number(transactionId));

  const ref = useRef<HTMLDivElement>(null);
  const htmlToImageConvert = () => {
    if (ref.current === null) {
      return
    }
    toPng(ref.current, { cacheBust: false })
      .then((dataUrl) => {
        const link = document.createElement("a");
        link.download = String(Date.now());
        link.href = dataUrl;
        link.click();
      })
      .catch((err) => {
        console.log(err);
      });
  };

  return (
    <S.DetailWrapper>
      <S.ReceiptWrapper ref={ref}>
        <S.TransactionTitle>{transactionDetailData.title}</S.TransactionTitle>
        <S.EmotionImageContainer>{filteredImage(transactionDetailData.emoticon)}</S.EmotionImageContainer>
        <S.Money>{setMoneyRegex(String(transactionDetailData.amount))}</S.Money>
        <S.EmotionContentContainer>
          <p>{transactionDetailData.content}</p>
        </S.EmotionContentContainer>
        <S.EmotionInfoWrapper>
          <S.EmotionInfo>날짜</S.EmotionInfo>
          <S.EmotionInfo>{transactionDetailData.date}</S.EmotionInfo>
        </S.EmotionInfoWrapper>
        <S.EmotionInfoWrapper>
          <S.EmotionInfo>계좌 이름</S.EmotionInfo>
          <S.EmotionInfo>{transactionDetailData.accountName}</S.EmotionInfo>
        </S.EmotionInfoWrapper>
      </S.ReceiptWrapper>
      <S.SavePhotoButton onClick={() => htmlToImageConvert()}>사진으로 저장하기</S.SavePhotoButton>
    </S.DetailWrapper>
  );
};

export default Detail;
