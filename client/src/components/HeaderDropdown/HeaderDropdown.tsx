import { useNavigate } from 'react-router-dom';
import { HeaderDropdownItem, HeaderDropdownWrapper } from '@/components/HeaderDropdown/HeaderDropdown.style';

const HeaderDropdown = () => {
  const navigate = useNavigate();
  return (
    <HeaderDropdownWrapper>
      <HeaderDropdownItem>피드</HeaderDropdownItem>
      <HeaderDropdownItem onClick={() => navigate('/profile')}>마이페이지</HeaderDropdownItem>
      <HeaderDropdownItem>리포트</HeaderDropdownItem>
    </HeaderDropdownWrapper>
  );
};

export default HeaderDropdown;
