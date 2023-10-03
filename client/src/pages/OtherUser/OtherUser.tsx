import * as S from '@/pages/OtherUser/OtherUser.style';
import { useParams } from 'react-router-dom';

const OtherUser = () => {
  const { userId } = useParams();
  return <S.OtherUserWrapper></S.OtherUserWrapper>;
};

export default OtherUser;
