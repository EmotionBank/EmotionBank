import { Flex } from '@/components/common/Flex/Flex';
import { float } from '@/constants/animation';
import styled, { css } from 'styled-components';

export const TransferModalWrapper = styled(Flex)`
  width: 30rem;
`;

export const EmotionContainer = styled(Flex)`
  width: 100%;
  justify-content: space-around;
`;

export const EmotionImageContainer = styled(Flex)<{ $clicked: boolean }>`
  flex-direction: column;
  justify-content: space-between;
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
