import styled from 'styled-components';
import { Flex } from '@/components/common/Flex/Flex';

export const EmotionStepWrapper = styled(Flex)`
  flex-direction: column;
  justify-content: space-between;
`;

export const EmotionGrid = styled.div`
  width: 60%;
  display: grid;
  grid-column: 1fr 1fr 1fr;
`;
