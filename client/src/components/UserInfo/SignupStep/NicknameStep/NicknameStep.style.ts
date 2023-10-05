import { Button } from '@/components/common/Button/Button';
import { Flex } from '@/components/common/Flex/Flex';
import styled from 'styled-components';

export const NicknameWrapper = styled(Flex)`
  width: 100%;
  height: 100%;
  flex-direction: column;
  justify-content: space-between;
  align-items: flex-start;
  padding: 4rem 0;
`;

export const NicknameBody = styled.div`
  width: 100%;
`;
export const NicknameTitle = styled.h3`
  color: ${({ theme }) => theme.color.darkgray};
  margin-bottom:16px;
`;

export const InputWrapper = styled(Flex)`
  width: 100%;
  height: 100%;
  flex-direction: row;
  padding: 1rem 0rem;
  justify-content: space-between;
  align-items: flex-start;
`;

export const StyledInput = styled.input`
  width: 60%;
  height: 50px;
  padding: 8px;
  border-radius: 8px;
  border: 1px solid ${({ theme }) => theme.color.lightgray};
`;

export const StyledButton = styled(Button)`
  width: 30%;
  height: 50px;
  border-radius: 8px;
  padding: 8px 12px;
  font-weight: bold;
  background-color: ${({ theme }) => theme.color.navy};
  color: #fff;
`;

export const NextButton = styled(Button)`
  width: 100%;
  background-color: ${({ theme }) => theme.color.navy};
  color: #fff;
  border-radius: 8px;
  padding: 8px 12px;
`;
