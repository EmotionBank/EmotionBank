import { Button } from '@/components/common/Button/Button';

export const Menu = () => {
  const nickname = localStorage.getItem('nickname');
  return (
    <>
      <span>{nickname}</span>
      <Button>프로필 편집</Button>
      <span>서비스 버전 1.0.0</span>
    </>
  );
};
