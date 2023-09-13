import styled from 'styled-components';

export const Button = styled.button<{ $kakao?:boolean; }>`
  padding: 1rem 2rem;
  border-radius: 8px;
  white-space: nowrap;
  background: ${props => props.$kakao ? '#FFDC00':"white"}
`;
