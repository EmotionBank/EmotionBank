import { Button } from '@/components/common/Button/Button';
import { Flex } from '@/components/common/Flex/Flex';
import styled from 'styled-components';

export const ProfileWrapper = styled(Flex)`
  height: 100%;
  width: 100%;
  flex-direction: column;
  justify-content: space-between;
  padding-bottom: 2rem;
`;

export const ProfileImage = styled(Flex)`
  width: 100%;
`;

export const InfoWrapper = styled(Flex)`
  width: 100%;
  flex-direction: column;
  justify-content: space-between;
`;

export const InfoContainer = styled(Flex)`
  padding: 1rem;
  width: 100%;
  justify-content: space-between;
  font-weight: bold;
  font-size: ${({ theme }) => theme.fontSize.m};
  color: ${({ theme }) => theme.color.darkgray};
`;

export const NicknameInputWrapper = styled(Flex)`
  padding: 1rem;
  flex-direction: column;
  gap: 2rem;
`;

export const TopWrapper = styled(Flex)`
  flex-direction: column;
  width: 80%;
`;

export const InputContainer = styled(Flex)`
  justify-content: space-between;
  gap: 1rem;
`;

export const NicknameInput = styled.input`
  width: 100%;
  padding: 0.5rem;
  background-color: ${({ theme }) => theme.color.secondary};
  border-radius: 8px;
`;

export const CheckButton = styled(Button)`
  font-size: 1.4rem;
  font-weight: bold;
  color: ${({ theme }) => theme.color.darkgray};
`;

export const ConfirmButton = styled(Button)`
  width: 100%;
  background-color: ${({ theme }) => theme.color.navy};
  color: white;
`;
