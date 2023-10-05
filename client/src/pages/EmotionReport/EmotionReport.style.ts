import { Flex } from '@/components/common/Flex/Flex';
import styled from 'styled-components';

export const EmotionReportWrapper = styled(Flex)`
  flex-direction: column;
  justify-content: start;
  gap: 5rem;
  padding-bottom: 5rem;
  overflow: scroll;
  &::-webkit-scrollbar {
    display: none;
  }
`;
export const ReportContainer = styled(Flex)`
  flex-direction: column;
  gap: 2rem;
`;

export const ReportSpan = styled.span`
  font-size: ${({ theme }) => theme.fontSize.m};
  font-weight: bold;
  color: ${({ theme }) => theme.color.darkgray};
`;
