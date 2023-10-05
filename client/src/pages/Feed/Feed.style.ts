import { Button } from '@/components/common/Button/Button';
import { Flex } from '@/components/common/Flex/Flex';
import styled from 'styled-components';

export const FeedWrapper = styled(Flex)`
  width: 100%;
  height: 100%;
  flex-direction: column;
  justify-content: center;
`;
export const GridContainer = styled.div`
  display: grid;
  grid-template-columns: repeat(4, 1fr);
  gap: 10px 10px;
  width: 100%;
  margin: 0 auto;
`;

export const FeedBody = styled(Flex)`
  flex-direction: column;
  justify-content: center;
  overflow: auto;
  cursor: pointer;
`;

export const FeedContent = styled(Flex)`
  background-color: ${({ theme }) => theme.color.secondary};
  color: ${({ theme }) => theme.color.primary};
  padding: 20px;
  text-align: center;
  font-size: 10px;
`;

export const EmotionImageContainer = styled(Flex)`
  min-width: 10rem;
  flex-direction: column;
  justify-content: space-between;
  padding: 1rem;
`;

export const Target = styled.div`
  height: 1px;
`;

export const SearchBody = styled(Flex)`

`
export const SearchInput = styled.input`
  padding : 0.5em;
  border-radius: 10px;
  box-shadow: 0px 4px 4px 0px rgba(0, 0, 0, 0.25);
  box-shadow: 0px 4px 4px 0px color(display-p3 0 0 0 / 0.25);
`;

export const SearchButton = styled(Button)``
export const SearchResultItem = styled.li`
  cursor: pointer;
  margin: 10px 10px 0 0;
  list-style: none;
`;

