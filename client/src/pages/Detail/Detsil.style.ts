import { Button } from '@/components/common/Button/Button';
import { Flex } from '@/components/common/Flex/Flex';
import styled from 'styled-components';

export const DetailWrapper = styled(Flex)`
  width: 100%;
  flex-direction: column;
  gap: 2rem;
`;

export const ReceiptWrapper = styled(Flex)`
  flex-direction: column;
  width: 100%;
  padding: 3rem;
  color: ${({ theme }) => theme.color.darkgray};
  --mask: radial-gradient(5.02px at 50% 5.5px, #000 99%, #0000 101%) calc(50% - 10px) 0/20px 51% repeat-x,
    radial-gradient(5.02px at 50% -0.5px, #0000 99%, #000 101%) 50% 5px/20px calc(51% - 5px) repeat-x,
    radial-gradient(5.02px at 50% calc(100% - 5.5px), #000 99%, #0000 101%) 50% 100%/20px 51% repeat-x,
    radial-gradient(5.02px at 50% calc(100% + 0.5px), #0000 99%, #000 101%) calc(50% - 10px) calc(100% - 5px) / 20px
      calc(51% - 5px) repeat-x;
  -webkit-mask: var(--mask);
  mask: var(--mask);
  background-color: #f2f2f2;
  gap: 2rem;
`;

export const TransactionTitle = styled.h1`
  font-size: ${({ theme }) => theme.fontSize.l};
`;

export const EmotionImageContainer = styled(Flex)`
  width: 80%;
  border-radius: 10px;
  overflow: hidden;
`;

export const EmotionImage = styled.img`
  width: 100%;
`;

export const Money = styled.h2`
  font-size: ${({ theme }) => theme.fontSize.l};
  font-weight: bold;
`;

export const EmotionContentContainer = styled(Flex)`
  flex-direction: column;
`;

export const EmotionInfoWrapper = styled(Flex)`
  width: 100%;
  justify-content: space-between;
`;

export const EmotionInfo = styled.span`
  font-size: ${({ theme }) => theme.fontSize.s};
  font-weight: bold;
`;

export const SavePhotoButton = styled(Button)`
  width: 100%;
  font-size: ${({ theme }) => theme.fontSize.m};
  font-weight: bold;
  background-color: ${({ theme }) => theme.color.navy};
  color: ${({ theme }) => theme.color.white};
`;
