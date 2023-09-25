import { Button } from '@/components/common/Button/Button';
import { Flex } from '@/components/common/Flex/Flex';
import styled from 'styled-components';

export const UserInfoWrapper = styled(Flex)`
  width: 100%;
  flex-direction: column;
  padding: 2rem;
  gap: 1rem;
`;

export const UserInfoTop = styled(Flex)`
  width: 100%;
  justify-content: space-between;
`;
export const UserInfoBottom = styled(UserInfoTop)``;

export const profileImage = styled.img`
  width: 5rem;
`;

export const InfoContainer = styled(Flex)`
  gap: 4rem;
`;

export const InfoTextContainer = styled(Flex)`
  flex-direction: column;
  align-items: start;
  gap: 0.5rem;
  color: ${({ theme }) => theme.color.darkgray};
`;

export const NicknameInfo = styled.span`
  font-weight: bold;
`;

export const FollowContainer = styled(Flex)`
  justify-content: space-between;
  gap: 2rem;
  font-weight: bold;
  color: ${({ theme }) => theme.color.darkgray};
`;

export const FollowingInfo = styled(Flex)`
  flex-direction: column;
  gap: 0.5rem;
  cursor: pointer;
`;
export const FollowerInfo = styled(FollowingInfo)``;

export const TransactionButton = styled(Button)`
  width: 40%;
  background-color: aliceblue;
  padding: 1rem 3rem;
  font-size: ${({ theme }) => theme.fontSize.s};
  color: ${({ theme }) => theme.color.darkgray};
  font-weight: bold;
`;
