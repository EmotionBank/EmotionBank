import styled from 'styled-components';
import { Flex } from '@/components/common/Flex/Flex';

export const HeaderDropdownWrapper = styled(Flex)`
  flex-direction: column;
  gap: 0.5rem;
`;

export const HeaderDropdownItem = styled(Flex)`
  border-radius: 8px;
  font-weight: bold;
  color: ${({ theme }) => theme.color.lightgray};
  padding: 0.2rem;
  width: 100%;
  cursor: pointer;
  &:hover {
    background: ${({ theme }) => theme.color.secondary};
    color: ${({ theme }) => theme.color.darkgray};
  }
`;
