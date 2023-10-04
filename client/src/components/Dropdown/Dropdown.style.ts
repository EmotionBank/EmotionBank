import styled from 'styled-components';

export const DropdownWrapper = styled.div``;
export const DropdownButton = styled.button``;
export const DropdownContent = styled.div<{ isOpen: boolean }>`
  width: 121px;
  height: auto;
  padding: 6px;
  flex-shrink: 0;
  border-radius: 10px;
  background: #fff;
  background: color(display-p3 1 1 1);
  box-shadow: 0px 4px 4px 0px rgba(0, 0, 0, 0.25);
  box-shadow: 0px 4px 4px 0px color(display-p3 0 0 0 / 0.25);
  list-style-type: none;
  z-index: 1;
  position: absolute;
  top: 60%;
  left: 3%;
  display: ${props => (props.isOpen ? 'block' : 'none')};
`;
export const DropdownItem = styled.li`
  margin-top: 6px;
  cursor: pointer;
  &:hover {
    background: ${({ theme }) => theme.color.secondary};
    color: ${({ theme }) => theme.color.primary};
  }
`;
