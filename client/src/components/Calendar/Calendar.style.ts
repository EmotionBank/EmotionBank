import { Flex } from '@/components/common/Flex/Flex';
import styled from 'styled-components';

export const CalendarWrapper = styled(Flex)`
  flex-direction: column;
  width: 100%;
`;

export const CalendarHeader = styled(Flex)`
  width: 100%;
  background-color: aliceblue;
  height: 4rem;
  justify-content: space-around;
  font-size: ${({ theme }) => theme.fontSize.m};
  font-weight: bold;
`;

export const CalendarBody = styled(Flex)`
  flex-direction: column;
  width: 100%;
  gap: 1rem;
`;

export const WeekContainer = styled(Flex)`
  width: 100%;
  justify-content: space-around;
`;

export const DayContainer = styled(Flex)`
  text-align: center;
  width: 20px;
`;
