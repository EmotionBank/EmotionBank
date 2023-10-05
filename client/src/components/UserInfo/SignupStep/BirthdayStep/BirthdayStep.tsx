// import * as S from '@/components/UserInfo/SignupStep/BirthdayStep/BirthdayStep.style';
import * as S from '@/components/UserInfo/SignupStep/BirthdayStep/BirthdayStep.style';
import { useState } from 'react';

interface IProps {
  onNext: (birthday: string) => void;
}

const BirthdayStep = ({ onNext }: IProps) => {
  const [selectedDate, setSelectedDate] = useState(''); // 선택한 날짜를 저장할 상태

  const handleDateChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const dateValue = e.target.value;
    setSelectedDate(dateValue);
  };
  console.log(selectedDate);
  return (
    <S.BirthdayWrapper>
      <S.BirthdayTitle>생일을 입력해주세요.</S.BirthdayTitle>
      <S.BirthdayBody>
        <S.StyledInput type="date" onChange={handleDateChange} placeholder="yyyy-MM-dd" />
      </S.BirthdayBody>
      <S.StyledButton onClick={() => onNext(selectedDate)}>다음</S.StyledButton>
    </S.BirthdayWrapper>
  );
};

export default BirthdayStep;
