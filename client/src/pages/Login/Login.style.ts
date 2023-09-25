import { Button } from '@/components/common/Button/Button';
import styled from 'styled-components';

export const KakaoLoginButton = styled(Button)`
  width: 100%;
  font-size: ${({ theme }) => theme.fontSize.m};
  font-weight: bold;
`;

export const NextButton = styled(Button)`
  width: 100%;
  font-size: ${({ theme }) => theme.fontSize.m};
  font-weight: bold;
  color: ${({ theme }) => theme.color.primary};
  background: ${({ theme }) => theme.color.secondary};
`;

export const SignupTitle = styled.h3`
  color: ${({ theme }) => theme.color.primary};
`;
