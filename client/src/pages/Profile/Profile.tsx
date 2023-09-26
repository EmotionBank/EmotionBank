import { useEffect, useState } from 'react';
import { getMyInfo } from '@/apis/user/getMyInfo';
import { Logout } from '@/components/UserInfo/Logout';
import * as S from '@/pages/Profile/Profile.style';
import gloomy from '@assets/emotions/gloomy.png';
import EditIcon from '@mui/icons-material/Edit';
import { updateMyInfo } from '@/apis/user/updateMyInfo';
const Profile = () => {
  const [nickname, setNickname] = useState('');
  const [birthday, setBirthday] = useState('');
  const [accountName, setAccountName] = useState('');
  const [isChangeMode, setIsChangeMode] = useState(false);
  useEffect(() => {
    const Myinfo = async () => {
      const info = await getMyInfo();
      setNickname(info.nickname);
      console.log(info);
    };
    Myinfo();
  }, []);

  const handleNicknameChange = (e: React.FormEvent<HTMLInputElement>) => {
    const { value } = e.currentTarget;
    setNickname(value);
  };

  const handleNicknameSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();

    await updateMyInfo(nickname);
    setIsChangeMode(false);
  };

  return (
    <>
      <S.profileImage src={gloomy}></S.profileImage>
      {!isChangeMode ? (
        <div>
          {nickname}
          <button onClick={() => setIsChangeMode(true)}>
            <EditIcon />
          </button>
        </div>
      ) : (
        <div>
          <form onSubmit={handleNicknameSubmit}>
            <input type="text" value={nickname} onChange={handleNicknameChange} />
            <button>완료</button>
          </form>
        </div>
      )}

      <Logout />
    </>
  );
};

export default Profile;
