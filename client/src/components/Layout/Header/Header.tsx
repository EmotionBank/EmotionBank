import { HeaderWrapper } from '@/components/Layout/Header/Header.style';
import { signupIndex } from '@/recoils/atom';
import MenuIcon from '@mui/icons-material/Menu';
import ArrowBackIosNewIcon from '@mui/icons-material/ArrowBackIosNew';
import { useLocation } from 'react-router-dom';
import { useRecoilState } from 'recoil';
import { Button } from '@/components/common/Button/Button';
const Header = () => {
  const path = useLocation().pathname;
  const [index, setIndex] = useRecoilState(signupIndex);
  const handleIndexBack = () => {
    setIndex(index - 1);
  };
  return (
    <HeaderWrapper>
      {path === '/' ? <MenuIcon /> : null}
      {path === '/signup' ? (
        <Button onClick={handleIndexBack}>
          <ArrowBackIosNewIcon />
        </Button>
      ) : null}
    </HeaderWrapper>
  );
};

export default Header;
