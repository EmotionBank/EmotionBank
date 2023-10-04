const emotionName = {
  EXCITING: '신나',
  HAPPY: '행복해',
  FLUTTER: '설레',
  SOSO: '그저그래',
  SAD: '속상해',
  TIRED: '피곤해',
  ANNOYING: '짜증나',
  ANGRY: '화나',
  GLOOMY: '우울해',
};
export const setEmotionName = (emotion: string) => {
  const result = Object.entries(emotionName).filter(([key, value]) => emotion === key);
  return result[0][1];
};
