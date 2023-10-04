import { Button } from '@/components/common/Button/Button';
import { Flex } from '@/components/common/Flex/Flex';
import styled from 'styled-components';

export const OtherUserInfoWrapper = styled(Flex)`
  width: 100%;
  flex-direction: column;
  gap: 1rem;
`;

export const OtherUserInfoTop = styled(Flex)`
  width: 100%;
  justify-content: space-between;
  height: 8rem;
`;
export const OtherUserInfoBottom = styled(OtherUserInfoTop)`
  height: 4rem;
  padding: 0 1rem;
`;

export const InfoContainer = styled(Flex)`
  gap: 4rem;
  height: 100%;
`;
export const NicknameInfo = styled.span`
  font-weight: bold;
`;

export const Logoimage = styled.img`
  height: 90%;
`;

export const FollowContainer = styled(Flex)`
  justify-content: space-around;
  gap: 2rem;
  font-weight: bold;
  width: 40%;
  font-size: 1.4rem;
  color: ${({ theme }) => theme.color.darkgray};
`;

export const FollowingInfo = styled(Flex)`
  flex-direction: column;
  gap: 0.5rem;
  cursor: pointer;
`;
export const FollowerInfo = styled(FollowingInfo)``;

export const TransactionButton = styled(Button)`
  background-color: ${({ theme }) => theme.color.secondary};
  width: 40%;
  font-size: ${({ theme }) => theme.fontSize.s};
  color: ${({ theme }) => theme.color.darkgray};
  font-weight: bold;
`;
