import { Flex } from '@/components/common/Flex/Flex';
import styled from 'styled-components';

export const TransactionListWrapper = styled(Flex)`
  width: 100%;
  margin-top: 10px;
  flex-direction: column;
  gap: 0.5rem;
  overflow: scroll;
  &::-webkit-scrollbar {
    display: none;
  }
`;
export const TransactionListContainer = styled(Flex)`
  padding: 3rem 2rem;
  width: 100%;
  min-height: 5.5rem;
  color: ${({ theme }) => theme.color.gray};
  border-radius: 8px;
  font-size: ${({ theme }) => theme.fontSize.s};
  font-weight: bold;
  background-color: whitesmoke;
  cursor: pointer;
`;

export const EmotionImage = styled.div`
  width: 15%;
  margin-right: 20px;
`;

export const TransactionTitle = styled.div`width: 60%`;

export const TransactionAmount = styled.div`width: 20%`;