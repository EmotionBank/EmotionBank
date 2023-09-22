import styled, { css } from 'styled-components';
import { Flex } from '@/components/common/Flex/Flex';
import { Button } from '@/components/common/Button/Button';
import { float } from '@/constants/animation';

export const EmotionStepWrapper = styled(Flex)`
  height: 100%;
  width: 100%;
  flex-direction: column;
  justify-content: space-between;
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

export const EmotionImageContainer = styled(Flex)<{ $clicked: boolean }>`
  flex-direction: column;
  padding: 1rem;
  cursor: pointer;
  border-radius: 8px;
  animation: ${({ $clicked }) =>
    $clicked
      ? css`
          ${float} 0.7s ease-in-out infinite
        `
      : 'none'};
`;

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
