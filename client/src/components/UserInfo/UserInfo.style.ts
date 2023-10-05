import { Button } from '@/components/common/Button/Button';
import { Flex } from '@/components/common/Flex/Flex';
import styled from 'styled-components';

export const UserInfoWrapper = styled(Flex)`
  width: 100%;
  flex-direction: column;
  gap: 1rem;
`;

export const UserInfoTop = styled(Flex)`
  width: 100%;
  justify-content: space-between;
  height: 8rem;
`;
export const UserInfoBottom = styled(UserInfoTop)`
  height: 4rem;
  font-weight: bold;
  color: ${({ theme }) => theme.color.darkgray};
  padding: 0 1rem;
`;

export const profileImage = styled.img`
  width: 5rem;
`;

export const InfoContainer = styled(Flex)`
  gap: 2rem;
  /* width: 60%; */
  height: 100%;
`;

export const InfoTextContainer = styled(Flex)`
  flex-direction: column;
  align-items: start;
  gap: 0.5rem;
  color: ${({ theme }) => theme.color.darkgray};
  width: 100%;
`;
export const Logoimage = styled.img`
  height: 90%;
`;

export const NicknameInfo = styled.span`
  font-weight: bold;
`;
export const AccountNumberInfo = styled.span`
  font-weight: bold;
  font-size: 1.2rem;
  color: ${({ theme }) => theme.color.lightgray};
  white-space: nowrap;
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

export const MoneySpan = styled.span`
  letter-spacing: 1.5px;
`;

export const TransactionButton = styled(Button)`
  width: 40%;
  background-color: ${({ theme }) => theme.color.secondary};
  padding: 1rem 3rem;
  font-size: ${({ theme }) => theme.fontSize.s};
  color: ${({ theme }) => theme.color.darkgray};
  font-weight: bold;
`;
