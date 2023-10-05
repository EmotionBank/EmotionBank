import { Button } from '@/components/common/Button/Button';
import { Flex } from '@/components/common/Flex/Flex';
import styled from 'styled-components';

export const EmotionReportWrapper = styled(Flex)`
  width: 100%;
  min-height: 100%;
  flex-direction: column;
  justify-content: start;
  gap: 5rem;
  padding-bottom: 5rem;
  overflow: scroll;
  &::-webkit-scrollbar {
    display: none;
  }
`;

export const SelectHeader = styled(Flex)`
  width: 100%;
  justify-content: start;
  position: sticky;
`;

export const SelectContainer = styled(Flex)`
  /* width: 40%; */
`;
export const ToggleButton = styled(Button)<{ $toggle: boolean }>`
  padding: 0 1rem;
  color: ${({ theme, $toggle }) => ($toggle ? theme.color.darkgray : theme.color.lightgray)};
  font-size: ${({ theme }) => theme.fontSize.l};
  font-weight: bold;
`;

export const ReportContainer = styled(Flex)`
  width: 80%;
  flex-direction: column;
  gap: 2rem;
`;

export const ReportSpan = styled.span`
  font-size: ${({ theme }) => theme.fontSize.m};
  font-weight: bold;
  color: ${({ theme }) => theme.color.darkgray};
`;
