import { Button } from '@/components/common/Button/Button';
import { Flex } from '@/components/common/Flex/Flex';
import styled from 'styled-components';

export const BirthdayWrapper = styled(Flex)`
  width: 100%;
  height: 100%;
  flex-direction: column;
  justify-content: space-between;
  align-items: flex-start;
  padding: 4rem 0;
  gap: 1rem;
`;

export const BirthdayTitle = styled.h3`
  color: ${({ theme }) => theme.color.darkgray};
  margin-bottom:16px;
`;

export const BirthdayBody = styled.div`
  width: 100%;
  height: 100%;
  justify-content: start;
`;

export const StyledInput = styled.input`
  width: 100%;
  height: 50px;
  border-radius: 8px;
  padding: 8px 12px;
  background-color: whitesmoke;
`;

export const StyledButton = styled(Button)`
  width: 100%;
  background-color: ${({ theme }) => theme.color.navy};
  color: #fff;
  border-radius: 8px;
  padding: 8px 12px;
`;
