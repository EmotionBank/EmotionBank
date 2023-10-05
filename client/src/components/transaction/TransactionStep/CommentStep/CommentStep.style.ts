import { Button } from '@/components/common/Button/Button';
import { Flex } from '@/components/common/Flex/Flex';
import styled from 'styled-components';

export const CommentStepWrapper = styled(Flex)`
  height: 100%;
  width: 100%;
  flex-direction: column;
  justify-content: space-between;
`;

export const CommentHeader = styled.h1`
  color: ${({ theme }) => theme.color.navy};
  font-size: ${({ theme }) => theme.fontSize.l};
`;

export const EmotionImageContainer = styled(Flex)`
  width: 70%;
  height: 250px;
  overflow: hidden;
`;

export const EmotionImage = styled.img`
  width: 70%;
`;

export const BalanceSpan = styled.span`
  font-size: ${({ theme }) => theme.fontSize.l};
  font-weight: bold;
`;

export const LabelContainer = styled(Flex)`
  width: 100%;
  flex-direction: column;
  align-items: start;
  gap: 1rem;
`;

export const Inputlabel = styled.span`
  font-size: ${({ theme }) => theme.fontSize.m};
  color: ${({ theme }) => theme.color.darkgray};
  font-weight: bold;
`;

export const AmountInput = styled.input`
  width: 100%;
  background-color: ${({ theme }) => theme.color.white};
  border: 1px solid ${({ theme }) => theme.color.lightgray};
  padding: 1rem;
  border-radius: 8px;
`;

export const CommentTextArea = styled.textarea`
  width: 100%;
  background-color: ${({ theme }) => theme.color.white};
  border: 1px solid ${({ theme }) => theme.color.lightgray};
  padding: 1rem;
  resize: none;
  border-radius: 8px;
  height: 10rem;
  &:focus {
    outline: none;
  }
`;

export const NextButton = styled(Button)`
  width: 100%;
  padding: 1rem;
  background-color: ${({ theme }) => theme.color.navy};
  color: ${({ theme }) => theme.color.white};
  font-size: ${({ theme }) => theme.fontSize.s};
  font-weight: bold;
`;
