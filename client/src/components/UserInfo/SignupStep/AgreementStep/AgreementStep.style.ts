import styled from 'styled-components';
import { Button } from '@/components/common/Button/Button';
import { Flex } from '@/components/common/Flex/Flex';

export const AgreementTitle = styled.h3`
  color: ${({ theme }) => theme.color.black};
  margin-bottom: 16px;
`;

export const AgreementForm = styled(Flex)`
  align-items: center;
  flex-direction: column;
  justify-content: space-between;
  align-items: flex-start;
  height: 100%;
  padding: 4rem 0;
  width: 100%;
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
  padding: 1rem 0rem;
  justify-content: start;
  align-items: flex-start;
  gap: 1rem;
`;
export const AgreementContent = styled(Flex)`
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

export const AgreementDetail = styled.div`
  width: 100%;
  margin-left: 15px;
  padding: 5px 10px;
  border: 1px solid ${({ theme }) => theme.color.darkgray};
  border-radius: 4px;
`;

export const DetailButton = styled.div`
  width: 10%;
  color: '#6b7685';
  font-weight: bold;
`;
