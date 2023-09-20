import { Flex } from '@/components/common/Flex/Flex';
import styled from 'styled-components';

export const CalendarWrapper = styled(Flex)`
  flex-direction: column;
  width: 100%;
  border-radius: 5px;
  box-shadow: 0px 1px 2px rgba(0, 0, 0, 0.4);
`;

export const CalendarControllerWrapper = styled(Flex)`
  width: 100%;
  padding: 0.5rem 2rem;
  justify-content: space-between;
  align-items: center;
`;

export const CalendarControllerContainer = styled(Flex)`
  gap: 2rem;
`;

export const CalendarController = styled.span`
  font-weight: bold;
  font-size: 2rem;
  cursor: pointer;
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
  gap: 1rem;
`;

export const WeekContainer = styled(Flex)`
  width: 100%;
  justify-content: space-around;
`;

export const DayContainer = styled(Flex)<{ $thisMonth: boolean }>`
  text-align: center;
  width: 45px;
  height: 45px;
  padding: 0.5rem;
  color: ${({ $thisMonth }) => ($thisMonth ? 'black' : 'lightgray')};
  cursor: pointer;
`;

export const EmotionImage = styled.img`
  width: 100%;
`;
