import { Flex } from '@/components/common/Flex/Flex';
import styled from 'styled-components';

export const CalendarWrapper = styled(Flex)`
  flex-direction: column;
  width: 100%;
  border-radius: 8px;
  padding: 1rem;
  background-color: whitesmoke;
`;

export const CalendarControllerWrapper = styled(Flex)`
  width: 100%;
  padding: 0.5rem 2rem;
  align-items: center;
`;

export const CalendarControllerContainer = styled(Flex)`
  gap: 3rem;
  align-items: center;
`;
export const YearAndMonth = styled.span`
  font-size: 1.7rem;
  font-weight: bold;
  color: ${({ theme }) => theme.color.darkgray};
`;

export const CalendarController = styled.span`
  font-weight: bold;
  font-size: 2rem;
  cursor: pointer;
  color: ${({ theme }) => theme.color.lightgray};
`;

export const CalendarHeader = styled(Flex)`
  width: 100%;
  height: 4rem;
  justify-content: space-around;
  font-size: ${({ theme }) => theme.fontSize.m};
  font-weight: bold;
`;

export const CalendarBody = styled(Flex)`
  flex-direction: column;
  width: 100%;
`;

export const WeekContainer = styled(Flex)`
  width: 100%;
  justify-content: space-around;
`;

export const DayContainer = styled(Flex)<{ $thisMonth: boolean }>`
  text-align: center;
  width: 50px;
  height: 50px;
  padding: 0.5rem;
  color: ${({ $thisMonth }) => ($thisMonth ? 'black' : 'lightgray')};
  cursor: pointer;
`;

export const DateSpan = styled.span`
  font-weight: bold;
  font-size: 1.2rem;
`;
