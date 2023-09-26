import { HeaderWrapper } from '@/components/Layout/Header/Header.style';
import { signupIndex } from '@/recoils/atom';
import ArrowBackIosNewIcon from '@mui/icons-material/ArrowBackIosNew';
import { useLocation } from 'react-router-dom';
import { useRecoilState } from 'recoil';
import { Button } from '@/components/common/Button/Button';
import Dropdown from '@/components/Dropdown/Dropdown';

const Header = () => {
  const path = useLocation().pathname;
  const [index, setIndex] = useRecoilState(signupIndex);
  const handleIndexBack = () => {
    setIndex(index - 1);
  };
  return (
    <HeaderWrapper>
      {path === '/' ? <Dropdown /> : null}
      {path === '/signup' ? (
        <Button onClick={handleIndexBack}>
          <ArrowBackIosNewIcon />
        </Button>
      ) : null}
    </HeaderWrapper>
  );
};

export default Header;
