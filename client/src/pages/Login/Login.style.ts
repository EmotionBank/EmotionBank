import { Button } from '@/components/common/Button/Button';
import styled from 'styled-components';

export const KakaoLoginButton = styled(Button)`
  width: 100%;
  font-size: ${({ theme }) => theme.fontSize.m};
  font-weight: bold;
`;
