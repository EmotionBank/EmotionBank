import { Flex } from '@/components/common/Flex/Flex';
import styled from 'styled-components';

export const NorificationWrapper = styled(Flex)`
  padding: 2rem;
  width: 30rem;
  flex-direction: column;
  gap: 1rem;
`;

export const NorificationItem = styled(Flex)`
  width: 100%;
  height: 4rem;
  background-color: whitesmoke;
  border-radius: 8px;
  justify-content: space-between;
  padding: 0 1rem;
  font-size: 1.2rem;
`;
