import { Button } from '@/components/common/Button/Button';
import { Flex } from '@/components/common/Flex/Flex';
import styled from 'styled-components';

export const NicknameWrapper = styled(Flex)`
  width: 100%;
  height: 100%;
  flex-direction: column;
  justify-content: space-between;
  padding: 4rem 0;
`;

export const NicknameBody = styled.div`
  width: 100%;
`;
export const NicknameTitle = styled.h3`
  color: ${({ theme }) => theme.color.darkgray};
`;

export const InputWrapper = styled(Flex)`
  width: 80%;
  gap: 1rem;
`;

export const StyledInput = styled.input`
  width: 40%;
  padding: 8px;
  border-radius: 8px;
  border: 1px solid ${({ theme }) => theme.color.lightgray};
`;

export const StyledButton = styled(Button)`
  width: 30%;
  border-radius: 8px;
  padding: 8px 12px;
  font-weight: bold;
`;

export const NextButton = styled(Button)`
  width: 80%;
  background-color: ${({ theme }) => theme.color.navy};
  color: #fff;
  border-radius: 8px;
  padding: 8px 12px;
`;
