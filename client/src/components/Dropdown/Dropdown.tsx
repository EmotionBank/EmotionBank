import { useState, useEffect, useRef } from 'react';
import MenuIcon from '@mui/icons-material/Menu';
import * as S from './Dropdown.style';
import { useNavigate } from 'react-router-dom';

const Dropdown = () => {
  const [isDropdownOpen, setIsDropdownOpen] = useState(false);
  const dropdownRef = useRef<HTMLDivElement | null>(null)
  const toggleDropdown = () => {
    setIsDropdownOpen(!isDropdownOpen);
  };
  const navigate = useNavigate();

  useEffect(() => {
    // Function to close the dropdown when clicking outside of it
    const handleClickOutside = (event:MouseEvent) => {
      if (dropdownRef.current && !dropdownRef.current.contains(event.target as Node)) {
        setIsDropdownOpen(false);
      }
    };

    // Add event listener when the component mounts
    document.addEventListener('mousedown', handleClickOutside);

    // Remove the event listener when the component unmounts
    return () => {
      document.removeEventListener('mousedown', handleClickOutside);
    };
  }, []);
  return (
    <S.DropdownWrapper ref={dropdownRef}>
      <S.DropdownButton onClick={toggleDropdown}>
        <MenuIcon />
      </S.DropdownButton>
      {isDropdownOpen && (
        <S.DropdownContent isOpen={isDropdownOpen}>
          <S.DropdownItem onClick={()=> navigate('/feed')}>피드</S.DropdownItem>
          <S.DropdownItem onClick={() => navigate('/profile')}>마이페이지</S.DropdownItem>
          <S.DropdownItem>리포트</S.DropdownItem>
        </S.DropdownContent>
      )}
    </S.DropdownWrapper>
  );
};

export default Dropdown;
