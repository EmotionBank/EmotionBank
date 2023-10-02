import { HeaderWrapper } from '@/components/Layout/Header/Header.style';
import { signupStep } from '@/recoils/atom';
import ArrowBackIosNewIcon from '@mui/icons-material/ArrowBackIosNew';
import { useLocation, useNavigate } from 'react-router-dom';
import { useRecoilState } from 'recoil';
import { Button } from '@/components/common/Button/Button';
import Dropdown from '@/components/Dropdown/Dropdown';

const Header = () => {
  const path = useLocation().pathname;
  const [step, setStep] = useRecoilState(signupStep);
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
  return (
    <HeaderWrapper>
      {path === '/' ? <Dropdown /> : null}
      {path === '/signup' ? (
        <Button onClick={handleStepBack}>
          <ArrowBackIosNewIcon />
        </Button>
      ) : null}
    </HeaderWrapper>
  );
};

export default Header;
