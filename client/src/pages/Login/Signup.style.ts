import { Button } from '@/components/common/Button/Button';
import styled from 'styled-components';

export const InputWrapper = styled.div`
  display: flex;
  align-items: center;
`;

export const StyledInput = styled.input`
  flex-grow: 1;
  border: 1px solid #ccc;
  padding: 8px;
  border-radius: 4px;
`;

export const StyledButton = styled.button`
  margin-left: 8px;
  background-color: #007bff;
  color: #fff;
  border: none;
  border-radius: 4px;
  padding: 8px 12px;
  cursor: pointer;

  &:hover {
    background-color: #0056b3;
  }
`;
