import styled from 'styled-components';

export const DropdownWrapper = styled.ul`
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
`;

export const DropdownItem = styled.li`
  margin-top: 6px;
  cursor: pointer;
  &:hover {
    background: aliceblue;
    color: ${({ theme }) => theme.color.darkgray};
  }
`;
