import styled from 'styled-components';
import { Button } from '@/components/common/Button/Button';
import { Flex } from '@/components/common/Flex/Flex';
export const AgreementTitle = styled.h3`
  color: ${({ theme }) => theme.color.black};
`;

export const AgreementForm = styled.a`
  align-items: 'center';
  flex-direction: column;
  justify-content: space-between;
`;
export const NextButton = styled(Button)`
  width: 100%;
  padding: 1rem;
  background-color: ${({ theme }) => theme.color.navy};
  color: ${({ theme }) => theme.color.white};
  font-size: ${({ theme }) => theme.fontSize.s};
  font-weight: bold;
`;
