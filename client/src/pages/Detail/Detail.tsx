import * as S from '@/pages/Detail/Detsil.style';
import { useParams } from 'react-router-dom';

const Detail = () => {
  const { transactionId } = useParams();
  return <S.DetailWrapper>detail</S.DetailWrapper>;
};

export default Detail;
