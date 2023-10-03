import styled from 'styled-components';

export const FeedWrapper = styled.div`
`
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
`