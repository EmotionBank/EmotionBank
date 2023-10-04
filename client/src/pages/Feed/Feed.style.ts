import styled from 'styled-components';

export const FeedWrapper = styled.div``;
export const GridContainer = styled.div`
  display: grid;
  grid-template-columns: repeat(4, 1fr);
  gap: 100px 10px;
  width: 100%;
  margin: 0 auto;
`;

export const Content = styled.div`
  background-color: ${({ theme }) => theme.color.secondary};
  color: #fff;
  padding: 20px;
  text-align: center;
  font-size: 24px;
`;

export const EmotionImg = styled.img`
  width: 30px;
`;

export const Target = styled.div`
  height: 1px;
`;

export const SearchInput = styled.input`
  border-radius: 10px;
  box-shadow: 0px 4px 4px 0px rgba(0, 0, 0, 0.25);
  box-shadow: 0px 4px 4px 0px color(display-p3 0 0 0 / 0.25);
`;
