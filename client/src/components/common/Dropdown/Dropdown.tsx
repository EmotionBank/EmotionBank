import { ReactNode, useState } from 'react';
import MenuIcon from '@mui/icons-material/Menu';
import { DropdownBackground, DropdownWrapper } from './Dropdown.style';

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
        <MenuIcon style={{ fontSize: '2rem', cursor: 'pointer' }} />
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
