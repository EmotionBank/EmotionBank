import { HeaderWrapper } from '@/components/Layout/Header/Header.style';
import { isLoginState, signupIndex } from '@/recoils/atom';
import ArrowBackIosNewIcon from '@mui/icons-material/ArrowBackIosNew';
import { useLocation, useNavigate } from 'react-router-dom';
import { useRecoilState, useRecoilValue } from 'recoil';
import Dropdown from '@/components/Dropdown/Dropdown';

const Header = () => {
  const path = useLocation().pathname;
  const navigate = useNavigate();
  const [index, setIndex] = useRecoilState(signupIndex);
  const isLogin = useRecoilValue(isLoginState);

  const handleIndexBack = () => {
    setIndex(index - 1);
  };
  const handleBackPage = () => {
    navigate(-1);
  };
  const checkHeaderDropDown = () => {
    if (path === '/' && isLogin) return <Dropdown />;
    if (path === '/' && !isLogin) return null;
    return <ArrowBackIosNewIcon onClick={handleBackPage} />;
  };

  return <HeaderWrapper>{checkHeaderDropDown()}</HeaderWrapper>;
};

export default Header;
