import { Flex } from '@/components/common/Flex/Flex';
import styled from 'styled-components';

export const TransactionListWrapper = styled(Flex)`
  width: 100%;
  flex-direction: column;
  gap: 0.5rem;
  overflow: scroll;
`;
export const TransactionListContainer = styled(Flex)`
  padding: 0 2rem;
  width: 100%;
  min-height: 5.5rem;
  justify-content: space-between;
  color: ${({ theme }) => theme.color.gray};
  border-radius: 8px;
  font-size: ${({ theme }) => theme.fontSize.s};
  font-weight: bold;
  background-color: whitesmoke;
  cursor: pointer;
`;

export const EmotionImage = styled.div`
  width: 15%;
`;
