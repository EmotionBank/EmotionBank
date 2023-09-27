import { emotionImageList } from '@/constants/emotions';

export const filteredImage = (emoticon: string) =>
  Object.entries(emotionImageList).filter(([key, value]) => key === emoticon)[0][1];
