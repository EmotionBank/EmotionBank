import { Flex } from '@/components/common/Flex/Flex';
import styled from 'styled-components';

export const UserInfoWrapper = styled(Flex)`
  width: 100%;
  justify-content: space-between;
  padding: 2rem;
  /* background-color: red; */
`;

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
