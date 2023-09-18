import { Flex } from '@/components/common/Flex/Flex';
import styled from 'styled-components';

export const LayoutWrapper = styled.section`
  min-height: 100vh;
  background-color: ${({ theme }) => theme.color.white};
  min-width: 370px;
  max-width: 480px;
  margin: 0 auto;
  box-shadow: 0px 0px 5px 2px gray;
`;

export const OutletWrapper = styled(Flex)`
  width: 100%;
  flex-direction: column;
  padding: 0 2rem;
`;
