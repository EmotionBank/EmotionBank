import { Button } from '@/components/common/Button/Button';
import { Flex } from '@/components/common/Flex/Flex';
import { float } from '@/constants/animation';
import styled, { css } from 'styled-components';

export const TransferModalWrapper = styled(Flex)`
  padding: 2rem;
  width: 30rem;
  flex-direction: column;
  gap: 2rem;
`;
export const TransferModalTitle = styled.h3`
  color: ${({ theme }) => theme.color.darkgray};
`;
export const EmotionContainer = styled(Flex)`
  width: 100%;
  justify-content: space-around;
`;

export const EmotionImageContainer = styled(Flex)<{ $clicked: boolean }>`
  min-width: 8rem;
  flex-direction: column;
  justify-content: space-between;
  padding: 1rem;
  cursor: pointer;
  font-weight: bold;
  color: ${({ theme }) => theme.color.lightgray};
  border-radius: 8px;
  animation: ${({ $clicked }) =>
    $clicked
      ? css`
          ${float} 0.7s ease-in-out infinite
        `
      : 'none'};
`;

export const AmountInput = styled.input`
  width: 90%;
  background-color: ${({ theme }) => theme.color.secondary};
  padding: 1rem;
  border-radius: 16px;
  &::placeholder {
    font-size: 1.4rem;
  }
`;

export const SubmitButton = styled(Button)`
  width: 90%;
  background-color: ${({ theme }) => theme.color.navy};
  color: ${({ theme }) => theme.color.white};
  font-weight: bold;
`;
