import { Flex } from '@/components/common/Flex/Flex';
import styled from 'styled-components';

export const DropdownBackground = styled(Flex)`
  position: fixed;
  top: 0;
  left: 0;
  width: 100vw;
  height: 100vh;
  background-color: transparent;
  z-index: 5;
`;

export const DropdownWrapper = styled.div`
  position: absolute;
  width: 121px;
  padding: 0.5rem;
  flex-shrink: 0;
  border-radius: 8px;
  background: #fff;
  box-shadow: 0px 4px 4px 0px rgba(0, 0, 0, 0.25);
  z-index: 10;
  border: 1px solid whitesmoke;
`;
