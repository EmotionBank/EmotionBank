import styled from 'styled-components';
import { Flex } from '@/components/common/Flex/Flex';

export const FollowListModalWrapper = styled(Flex)`
  width: 30rem;
  height: 50rem;
  flex-direction: column;
  justify-content: start;
`;

export const FollowListModalHeader = styled(Flex)`
  width: 100%;
  justify-content: space-around;
  height: 5rem;
`;

export const FollowSelectButton = styled(Flex)<{ $isSelected: boolean }>`
  width: 50%;
  height: 100%;
  font-weight: bold;
  cursor: pointer;
  color: ${({ theme, $isSelected }) => ($isSelected ? theme.color.darkgray : theme.color.lightgray)};
`;

export const ItemContainer = styled(Flex)`
  width: 100%;
  flex-direction: column;
  gap: 1rem;
  font-size: 1.4rem;
  color: ${({ theme }) => theme.color.darkgray};
  padding: 0 1rem;
`;

export const FollowImg = styled.img`
  height: 70%;
`;

export const FollowListModalItem = styled(Flex)`
  font-weight: bold;
  width: 100%;
  padding: 0 1rem;
  justify-content: space-between;
  height: 5rem;
  border-bottom: 1px solid ${({ theme }) => theme.color.secondary};
`;
