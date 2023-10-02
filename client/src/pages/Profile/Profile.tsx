import { useState } from 'react';
import { Logout } from '@/components/UserInfo/Logout';
import * as S from '@/pages/Profile/Profile.style';
import Gloomy from '@assets/emotions/gloomy.svg';
import EditIcon from '@mui/icons-material/Edit';
import { updateMyInfo } from '@/apis/user/updateMyInfo';
import { nicknameCheck } from '@/apis/user/nicknameCheck';
import { Button } from '@/components/common/Button/Button';
import { useGetMyInfo } from '@/hooks/apiHooks/useGetMyInfo';
import { useInput } from '@/hooks/useInput';

const Profile = () => {
  const [content, handleContent] = useInput('');
  const [possible, setPossible] = useState(false);
  const [isChangeMode, setIsChangeMode] = useState(false);

  const { getMyInfoData } = useGetMyInfo();

  console.log(getMyInfoData);
  const handleNicknameCheck = async (e: React.FormEvent<HTMLButtonElement>) => {
    const response = await nicknameCheck(content);
    if (response) {
      alert('사용가능한 닉네임입니다.');
      setPossible(true);
    } else {
      alert('이미 존재하는 닉네임입니다.');
    }
  };

  const handleNicknameSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    if (possible) {
      await updateMyInfo(content);
      setIsChangeMode(false);
    } else {
      alert('중복 검사를 해주세요.');
    }
  };

  return (
    <>
      {/* <S.profileImage src={gloomy}></S.profileImage> */}
      <Gloomy />
      {!isChangeMode ? (
        <div>
          {getMyInfoData.nickname}
          <button onClick={() => setIsChangeMode(true)}>
            <EditIcon />
          </button>
        </div>
      ) : (
        <div>
          <form onSubmit={handleNicknameSubmit}>
            <input type="text" value={content} onChange={handleContent} />
            <button>완료</button>
          </form>
          <Button onClick={handleNicknameCheck}>중복 검사</Button>
        </div>
      )}

      <Logout />
    </>
  );
};

export default Profile;
