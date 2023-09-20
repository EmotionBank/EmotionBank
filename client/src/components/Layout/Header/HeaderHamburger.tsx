import MenuIcon from '@mui/icons-material/Menu';
import { useNavigate } from 'react-router-dom';

export const HeaderHamburger = () => {
  const navigate = useNavigate();
  return (
    <>
      <span onClick={() => navigate('/menu')}>
        <MenuIcon fontSize="large" />
      </span>
    </>
  );
};
