import { HeaderWrapper } from '@/components/Layout/Header/Header.style';
import { isLoginState, signupStep } from '@/recoils/atom';
import ArrowBackIosNewIcon from '@mui/icons-material/ArrowBackIosNew';
import { useLocation, useNavigate } from 'react-router-dom';
import { useRecoilState, useRecoilValue } from 'recoil';
import Dropdown from '@/components/Dropdown/Dropdown';

const Header = () => {
  const path = useLocation().pathname;
  const [step, setStep] = useRecoilState(signupStep);
  const isLogin = useRecoilValue(isLoginState);
  const navigate = useNavigate();
  const handleStepBack = () => {
    switch (step) {
      case 'agreement':
        navigate('/login');
      case 'nickname':
        setStep('agreement');
      case 'birthday':
        setStep('nickname');
      case 'accountName':
        setStep('birthday');
    }
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
