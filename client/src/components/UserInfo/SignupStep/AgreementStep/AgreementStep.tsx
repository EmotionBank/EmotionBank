import * as S from './AgreementStep.style';
import { useState } from 'react';
import { useGetTermList } from '@/hooks/apiHooks/useGetAgreementList';

interface Iprops {
  onNext: (requests: boolean[]) => void;
}
const AgreementStep = ({ onNext }: Iprops) => {
  const {
    getTermsListData: { agreements },
  } = useGetTermList();
  const [meetRequired, setMeetRequired] = useState(false);
  const [checkboxStates, setCheckboxStates] = useState<boolean[]>(new Array(agreements.length).fill(false));
  const [openStates, setOpenStates] = useState<boolean[]>(new Array(agreements.length).fill(false));
  const handleOpenChange = (index: number) => {
    const newOpenStates = [...openStates];
    newOpenStates[index] = !newOpenStates[index];
    setOpenStates(newOpenStates);
  };
  const handleCheckboxChange = (index: number) => {
    const newCheckboxStates = [...checkboxStates];
    newCheckboxStates[index] = !newCheckboxStates[index];
    const allMandatoryAgreed = agreements.every(
      (agreement, i) => agreement.mandatory !== 'ESSENTIAL' || newCheckboxStates[i],
    );
    setMeetRequired(allMandatoryAgreed);
    setCheckboxStates(newCheckboxStates);
  };

  return (
    <S.AgreementForm>
      <S.AgreementTitle style={{ marginBottom: '20px' }}>서비스 이용을 위해 약관에 동의해주세요</S.AgreementTitle>
      <S.AgreementWrapper>
        {agreements.map((agreement, index) => (
          <S.AgreementContent key={index} style={{ display: 'flex', alignItems: 'center', marginBottom: '10px' }}>
            <input
              type="checkbox"
              checked={checkboxStates[index]}
              onChange={() => handleCheckboxChange(index)}
              style={{ marginRight: '5px' }}
            />
            <span
              style={{
                marginRight: '5px',
                color: agreement.mandatory === 'ESSENTIAL' ? 'red' : 'inherit',
              }}
            >
              ({agreement.mandatory === 'ESSENTIAL' ? '필수' : '선택'})
            </span>
            <span>{agreement.title}</span>
          </S.AgreementContent>
        ))}
      </S.AgreementWrapper>

      <S.NextButton disabled={!meetRequired} onClick={() => onNext(checkboxStates)}>
        다음
      </S.NextButton>
    </S.AgreementForm>
  );
};

export default AgreementStep;
