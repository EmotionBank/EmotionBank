import { ReactNode, useState } from 'react';
import MenuIcon from '@mui/icons-material/Menu';
import { DropdownBackground, DropdownWrapper } from './Dropdown.style';
import { useNavigate } from 'react-router-dom';

interface DropdownProps {
  children: ReactNode;
}

const Dropdown = ({ children }: DropdownProps) => {
  const [isDropdownOpen, setIsDropdownOpen] = useState(false);
  const toggleDropdown = () => {
    setIsDropdownOpen(!isDropdownOpen);
  };

  return (
    <div>
      <button onClick={toggleDropdown}>
        <MenuIcon />
      </button>
      {isDropdownOpen && (
        <>
          <DropdownWrapper>{children}</DropdownWrapper>
          <DropdownBackground onClick={toggleDropdown} />
        </>
      )}
    </div>
  );
};

export default Dropdown;
