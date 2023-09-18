import styled from 'styled-components';
import { Flex } from '@/components/common/Flex/Flex';
import { Button } from '@/components/common/Button/Button';

export const EmotionStepWrapper = styled(Flex)`
  height: calc(100vh - 6rem);
  flex-direction: column;
  justify-content: space-between;
  padding: 4rem 0;
`;

export const EmotionHeader = styled.h1`
  color: ${({ theme }) => theme.color.navy};
  font-size: ${({ theme }) => theme.fontSize.l};
`;

export const EmotionGrid = styled.div`
  width: 60%;
  display: grid;
  grid-template-columns: 1fr 1fr 1fr;
  gap: 0.5rem;
`;

export const EmotionImageContainer = styled(Flex)``;

export const EmotionImage = styled.img`
  width: 100%;
`;

export const NextButton = styled(Button)`
  width: 100%;
  padding: 1rem;
  background-color: ${({ theme }) => theme.color.navy};
  color: ${({ theme }) => theme.color.white};
  font-size: ${({ theme }) => theme.fontSize.s};
  font-weight: bold;
`;
