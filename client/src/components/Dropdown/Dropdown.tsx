import { useState } from 'react';
import MenuIcon from '@mui/icons-material/Menu';
import { DropdownItem, DropdownWrapper } from './Dropdown.style';
import { useNavigate } from 'react-router-dom';

const Dropdown = () => {
  const [isDropdownOpen, setIsDropdownOpen] = useState(false);
  const toggleDropdown = () => {
    setIsDropdownOpen(!isDropdownOpen);
  };
  const navigate = useNavigate();

  return (
    <div>
      <button onClick={toggleDropdown}>
        <MenuIcon />
      </button>
      {isDropdownOpen && (
        <DropdownWrapper>
          <DropdownItem>피드</DropdownItem>
          <DropdownItem onClick={() => navigate('/profile')}>마이페이지</DropdownItem>
          <DropdownItem>리포트</DropdownItem>
        </DropdownWrapper>
      )}
    </div>
  );
};

export default Dropdown;
