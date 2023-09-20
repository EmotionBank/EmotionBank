import { HeaderWrapper } from '@/components/Layout/Header/Header.style';
import { Route, Routes } from 'react-router-dom';
import { HeaderArrow } from './HeaderArrow';
import { HeaderHamburger } from './HeaderHamburger';

const Header = () => {
  return (
    <HeaderWrapper>
      <Routes>
        <Route path="/" element={<HeaderHamburger />} />
        <Route path="/signup" element={<HeaderArrow />} />
      </Routes>
      Header
    </HeaderWrapper>
  );
};

export default Header;
