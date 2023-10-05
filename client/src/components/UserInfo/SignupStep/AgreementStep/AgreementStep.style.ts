import styled from 'styled-components';
import { Button } from '@/components/common/Button/Button';
import { Flex } from '@/components/common/Flex/Flex';

export const AgreementTitle = styled.h3`
  color: ${({ theme }) => theme.color.black};
`;

export const AgreementForm = styled(Flex)`
  align-items: center;
  flex-direction: column;
  justify-content: space-between;
  height: 100%;
  padding: 4rem 0;
  width: 80%;
`;
export const NextButton = styled(Button)`
  width: 100%;
  background-color: ${({ theme }) => theme.color.navy};
  color: #fff;
  border-radius: 8px;
  padding: 8px 12px;
`;

export const AgreementWrapper = styled(Flex)`
  width: 100%;
  height: 100%;
  flex-direction: column;
  padding: 5rem 2rem;
  align-items: center;
  gap: 1rem;
`;
export const AgreementContent = styled(Flex)`
  width: 200px;
  justify-content: start;
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
