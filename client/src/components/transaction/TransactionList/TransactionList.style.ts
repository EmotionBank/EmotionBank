import { Flex } from '@/components/common/Flex/Flex';
import styled from 'styled-components';

export const TransactionListWrapper = styled(Flex)`
  width: 100%;
  flex-direction: column;
  gap: 0.5rem;
`;
export const TransactionListContainer = styled(Flex)`
  flex-direction: column;
  justify-content: space-between;
  gap: 0.5rem;
  background-color: ${({ theme }) => theme.color.lightgray};
  color: ${({ theme }) => theme.color.darkgray};
`;
