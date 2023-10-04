import { useNavigate } from 'react-router-dom';
import { HeaderDropdownItem, HeaderDropdownWrapper } from '@/components/HeaderDropdown/HeaderDropdown.style';
import { PATH } from '@/constants/path';

const HeaderDropdown = () => {
  const navigate = useNavigate();
  return (
    <HeaderDropdownWrapper>
      <HeaderDropdownItem onClick={() => navigate(PATH.FEED)}>피드</HeaderDropdownItem>
      <HeaderDropdownItem onClick={() => navigate(PATH.PROFILE)}>마이페이지</HeaderDropdownItem>
      <HeaderDropdownItem onClick={() => navigate(PATH.REPORT)}>리포트</HeaderDropdownItem>
    </HeaderDropdownWrapper>
  );
};

export default HeaderDropdown;
