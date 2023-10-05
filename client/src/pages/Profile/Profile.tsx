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
import useModal from '@/hooks/useModal';
import Modal from '@/components/common/Modal/Modal';

const Profile = () => {
  const [content, handleContent] = useInput('');
  const [nicknamePossible, setNicknamePossible] = useState(false);
  const { openModal, closeModal } = useModal();

  const { getMyInfoData } = useGetMyInfo();

  const handleNicknameCheck = async (e: React.FormEvent<HTMLButtonElement>) => {
    e.preventDefault();
    if (content === '') return;
    const { possible } = await nicknameCheck(content);
    console.log(possible);
    if (possible) {
      alert('사용가능한 닉네임입니다.');
      setNicknamePossible(true);
      return;
    }
    alert('이미 존재하는 닉네임입니다.');
  };

  const handleNicknameSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    if (nicknamePossible) {
      await updateMyInfo(content);
      closeModal();
      return;
    }
    alert('중복 검사를 해주세요.');
  };

  return (
    <>
      <S.ProfileWrapper>
        <S.TopWrapper>
          <S.ProfileImage>
            <Gloomy />
          </S.ProfileImage>
          <S.InfoWrapper>
            <S.InfoContainer>
              <span>닉네임 </span>
              <S.InputContainer>
                {getMyInfoData.nickname}
                <EditIcon onClick={openModal} style={{ cursor: 'pointer', color: 'gray' }} />
              </S.InputContainer>
            </S.InfoContainer>
            <S.InfoContainer>
              <span>생년월일 </span>
              {getMyInfoData.birthday}
            </S.InfoContainer>
            <S.InfoContainer>
              <span>내 계좌이름 </span>
              {getMyInfoData.accountName}
            </S.InfoContainer>
            <S.InfoContainer>
              <span>내 계좌번호 </span>
              {getMyInfoData.accountNumber}
            </S.InfoContainer>
          </S.InfoWrapper>
        </S.TopWrapper>
        <Logout />
      </S.ProfileWrapper>
      <Modal>
        <S.NicknameInputWrapper>
          <h3>닉네임 수정</h3>
          <form id="nickname" onSubmit={handleNicknameSubmit}>
            <S.InputContainer>
              <S.NicknameInput type="text" value={content} onChange={handleContent} />
              <S.CheckButton onClick={handleNicknameCheck}>중복 검사</S.CheckButton>
            </S.InputContainer>
          </form>
          <S.ConfirmButton type="submit" form="nickname">
            완료
          </S.ConfirmButton>
        </S.NicknameInputWrapper>
      </Modal>
    </>
  );
};

export default Profile;
