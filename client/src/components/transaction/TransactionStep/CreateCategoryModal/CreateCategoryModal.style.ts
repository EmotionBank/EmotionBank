import styled from 'styled-components';
import { Button } from '@/components/common/Button/Button';
import { Flex } from '@/components/common/Flex/Flex';

export const CreateCategoryModalWrapper = styled(Flex)`
  width: 100%;
  flex-direction: column;
  padding: 2rem;
`;

export const CategoryForm = styled.form`
  width: 100%;
`;

export const LabelContainer = styled(Flex)`
  width: 100%;
  padding-top: 2rem;
  justify-content: start;
  color: ${({ theme }) => theme.color.darkgray};
  font-size: ${({ theme }) => theme.fontSize.s};
  font-weight: bold;
`;

export const RadioContainer = styled(Flex)`
  width: 100%;
  justify-content: space-around;
  color: ${({ theme }) => theme.color.darkgray};
  font-weight: bold;
  padding: 1rem 0 2rem 0;
`;

export const CategoryInput = styled.input`
  width: 100%;
  padding: 1rem;
  border-bottom: 1px solid silver;
`;

export const CreateButton = styled(Button)`
  width: 100%;
  padding: 1rem 2rem;
  font-weight: bold;
  background-color: ${({ theme }) => theme.color.secondary};
  color: ${({ theme }) => theme.color.darkgray};
`;
