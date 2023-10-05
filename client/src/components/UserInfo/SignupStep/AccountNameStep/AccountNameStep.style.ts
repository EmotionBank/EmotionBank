import { Flex } from '@/components/common/Flex/Flex';
import styled from 'styled-components';

export const AccountNameTitle = styled.h3`
  color: ${({ theme }) => theme.color.primary};
`;

export const StyledInput = styled.input`
  flex-grow: 1;
  border: 1px solid #ccc;
  padding: 8px;
  border-radius: 4px;
`;

export const AccountNameWrapper = styled(Flex)`
  width: 100%;
  height: 100%;
  flex-direction: column;
  justify-content: space-between;
  padding: 5rem 2rem;
  gap: 1rem;
`;

export const AccountNameBody = styled.div`
  width: 100%;
`;
