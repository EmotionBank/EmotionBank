// import * as S from '@/components/UserInfo/SignupStep/BirthdayStep/BirthdayStep.style';
import * as S from '@/components/transaction/TransferModal/TransferModal.style';
import { useInput } from '@/hooks/useInput';
import { useState } from 'react';
import DatePicker from 'react-datepicker';
import 'react-datepicker/dist/react-datepicker.css';

interface IProps {
  onNext: (birthday: string) => void;
}

const BirthdayStep = ({ onNext }: IProps) => {
  const [selectedDate, setSelectedDate] = useState(null); // 선택한 날짜를 저장할 상태

  const handleDateChange = date => {
    setSelectedDate(date); // 선택한 날짜를 상태에 업데이트
  };

  return (
    <S.TransferModalWrapper>
      <S.TransferModalTitle>생일을 입력해주세요.</S.TransferModalTitle>
      <DatePicker // DatePicker 컴포넌트 추가
        selected={selectedDate} // 선택한 날짜 설정
        onChange={handleDateChange} // 날짜가 변경될 때 실행할 함수 설정
        dateFormat="yyyy-MM-dd" // 날짜 형식 설정
        placeholderText="yyyy-MM-dd" // 플레이스홀더 텍스트 설정
        isClearable // 선택한 날짜를 지울 수 있는 버튼 표시
        showYearDropdown // 연도 선택 옵션 표시
        scrollableYearDropdown // 많은 연도를 스크롤로 선택할 수 있게 함
        yearDropdownItemNumber={15} // 스크롤할 연도 수 설정
        maxDate={new Date()} // 최대 선택 가능한 날짜를 현재 날짜로 제한
        className="your-custom-datepicker-class" // 원하는 스타일링을 위한 클래스 추가
      />
      <S.SubmitButton onClick={() => onNext(selectedDate)}>다음</S.SubmitButton>
    </S.TransferModalWrapper>

    // <S.BirthdayWrapper>
    //   <S.BirthdayTitle>생일을 입력해주세요.</S.BirthdayTitle>
    //   <S.StyledInput onChange={handleContent} value={content} placeholder="yyyy-MM-dd"></S.StyledInput>
    //   <S.StyledButton onClick={() => onNext(content)}>다음</S.StyledButton>
    // </S.BirthdayWrapper>

  );
};

export default BirthdayStep;
