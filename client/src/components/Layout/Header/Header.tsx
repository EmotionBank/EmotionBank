import { HeaderWrapper } from '@/components/Layout/Header/Header.style';
import { isLoginState, signupStep } from '@/recoils/atom';
import { useLocation, useNavigate } from 'react-router-dom';
import { useRecoilState, useRecoilValue } from 'recoil';
import ArrowBackIosNewIcon from '@mui/icons-material/ArrowBackIosNew';
import Dropdown from '@/components/common/Dropdown/Dropdown';
import HeaderDropdown from '@/components/HeaderDropdown/HeaderDropdown';

const Header = () => {
  const path = useLocation().pathname;
  const navigate = useNavigate();
  const isLogin = useRecoilValue(isLoginState);
  const [step, setStep] = useRecoilState(signupStep);

  const handleBackPage = () => {
    navigate(-1);
  };

  const checkHeaderDropDown = () => {
    if (path === '/' && isLogin)
      return (
        <Dropdown>
          <HeaderDropdown />
        </Dropdown>
      );
    if (path === '/' && !isLogin) return null;
    return <ArrowBackIosNewIcon onClick={handleBackPage} style={{ fontSize: '2rem', cursor: 'pointer' }} />;
  };

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
  return <HeaderWrapper>{checkHeaderDropDown()}</HeaderWrapper>;
};

export default Header;
