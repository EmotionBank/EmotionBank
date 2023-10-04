import styled from 'styled-components';
import { Flex } from '@/components/common/Flex/Flex';
import { Button } from '@/components/common/Button/Button';

export const CategoryStepWrapper = styled(Flex)`
  height: 100%;
  width: 100%;
  flex-direction: column;
  justify-content: space-between;
`;
export const CategoryHeaderContainer = styled(Flex)`
  width: 100%;
  justify-content: start;
  padding: 2rem;
`;
export const CategoryHeader = styled.h1`
  font-size: ${({ theme }) => theme.fontSize.l};
  font-weight: bold;
  color: ${({ theme }) => theme.color.darkgray};
`;

export const CategoryListWrapper = styled(Flex)`
  width: 100%;
  height: 100%;
  flex-direction: column;
  justify-content: start;
  padding: 2rem;
  gap: 1.5rem;
`;
export const CategoryContainer = styled(Flex)<{ $isSelected: boolean }>`
  width: 100%;
  height: 6rem;
  justify-content: space-between;
  padding: 0.5rem 3rem;
  border-radius: 8px;
  font-size: ${({ theme }) => theme.fontSize.m};
  background-color: ${({ theme, $isSelected }) => ($isSelected ? theme.color.purple : theme.color.secondary)};
  color: ${({ theme, $isSelected }) => ($isSelected ? theme.color.darkgray : theme.color.darkgray)};
  font-weight: bold;
  transition: 0.4s ease-in-out;
  cursor: pointer;
`;

export const CreateCategoryButton = styled(Flex)`
  width: 100%;
  height: 5rem;
  padding: 0.5rem 3rem;
  border-radius: 8px;
  font-size: ${({ theme }) => theme.fontSize.m};
  font-weight: bold;
  background-color: ${({ theme }) => theme.color.secondary};
  color: ${({ theme }) => theme.color.lightgray};
  cursor: pointer;
`;

export const VisibilityText = styled.span`
  font-size: ${({ theme }) => theme.fontSize.s};
`;

export const NextButton = styled(Button)`
  width: 100%;
  padding: 1rem;
  background-color: ${({ theme }) => theme.color.navy};
  color: ${({ theme }) => theme.color.white};
  font-size: ${({ theme }) => theme.fontSize.s};
  font-weight: bold;
`;
