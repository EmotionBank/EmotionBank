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
  height: 95%;
  margin: 0 auto;
`;

export const FeedBody = styled(Flex)`
  flex-direction: column;
  justify-content: center;
  overflow: auto;
  cursor: pointer;
`;

export const FeedContent = styled(Flex)`
  color: ${({ theme }) => theme.color.darkgray};
  text-align: center;
  font-size: 10px;
`;

export const EmotionImageContainer = styled(Flex)`
  min-width: 10rem;
  width: 100px;
  min-height: 10rem;
  flex-direction: row;
  justify-content: right;
  padding: 1rem;
`;

export const Target = styled.div`
  height: 1px;
`;

export const SearchBody = styled(Flex)`
  width: 95%;
`
export const SearchInput = styled.input`
  width : 100%;
  padding : 0.5em;
  border-radius: 10px;
  border : solid;
`;

export const SearchButton = styled.button`
  margin: 10px;
`

export const SearchResultBody = styled.div`
  width: 300px;
  height: 300px;
  padding: 10px;
`
export const SearchResultItem = styled.div`
  padding : 20px 0;
  cursor: pointer;
  margin: 10px 10px 0 0;
`;

export const SearchResultImg = styled.img`
width: 20px;
height:20px;

`
