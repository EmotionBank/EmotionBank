import { Button } from '@/components/common/Button/Button';
import { Flex } from '@/components/common/Flex/Flex';
import styled from 'styled-components';

export const BirthdayWrapper = styled(Flex)`
  width: 100%;
  height: 100%;
  flex-direction: column;
  justify-content: space-between;
  padding: 4rem 0;
  gap: 1rem;
`;

export const BirthdayTitle = styled.h3`
  color: ${({ theme }) => theme.color.darkgray};
`;

export const BirthdayBody = styled.div`
  width: 100%;
`;

export const StyledInput = styled.input`
  border-radius: 8px;
  padding: 8px 12px;
  background-color: whitesmoke;
`;

export const StyledButton = styled(Button)`
  width: 80%;
  background-color: ${({ theme }) => theme.color.navy};
  color: #fff;
  border-radius: 8px;
  padding: 8px 12px;
`;
