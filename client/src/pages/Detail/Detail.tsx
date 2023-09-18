import { useParams } from 'react-router-dom';
import * as S from '@/pages/Detail/Detsil.style';
import { useGetTransactionDetail } from '@/hooks/apiHooks/useGetTransactionDetail';

const Detail = () => {
  const { transactionId } = useParams();
  return <S.DetailWrapper>detail</S.DetailWrapper>;
};

export default Detail;
