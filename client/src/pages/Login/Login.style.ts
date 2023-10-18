import { Button } from '@/components/common/Button/Button';
import { Flex } from '@/components/common/Flex/Flex';
import styled from 'styled-components';

export const LoginWrapper = styled(Flex)`
  width: 100%;
  height: 100%;
  flex-direction: column;
  justify-content: space-between;
  padding: 5rem 2rem;
  gap: 1rem;
`;

export const KakaoLoginButton = styled(Button)`
  width: 100%;
  font-size: 1.8rem;
  font-family: 'Pretendard ExtraBold';
  font-weight: bold;
  padding: 1.6rem;
  border-radius: 15px;
  background-color: #ffdc00;
`;

export const NextButton = styled(Button)`
  display: flex;
  padding: 16px 20px;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  gap: 6px;
  width: 100%;
  font-size: ${({ theme }) => theme.fontSize.m};
  font-weight: bold;
  color: ${({ theme }) => theme.color.primary};
  background: ${({ theme }) => theme.color.secondary};
`;

export const MainImg = styled(Flex)`
  width: 100%;
  flex-direction: column;
  font-size: 4.5rem;
  font-weight: bold;
  color: ${({ theme }) => theme.color.navy};
`;
