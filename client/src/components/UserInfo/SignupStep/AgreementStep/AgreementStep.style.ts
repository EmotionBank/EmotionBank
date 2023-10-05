import { Button } from '@/components/common/Button/Button';
import { Flex } from '@/components/common/Flex/Flex';
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

export const AgreementWrapper = styled(Flex)`
  width: 100%;
  height: 100%;
  flex-direction: column;
  justify-content: space-between;
  padding: 5rem 2rem;
  gap: 1rem;
`;
export const AgreementContent = styled.div`
  margin-top: 1em;
`;

export const AgreementBody = styled.div`
  width: 100%;
`;
export const AgreementButton = styled(Button)`
  width: 100%;
  font-size: ${({ theme }) => theme.fontSize.m};
  font-weight: bold;
  color: ${({ theme }) => theme.color.primary};
  background-color: ${({ theme }) => theme.color.secondary};
`;
