import * as S from './AgreementStep.style';
import { useState } from 'react';
import { useGetTermList } from '@/hooks/apiHooks/useGetAgreementList';
import ArrowRightIcon from '@mui/icons-material/ArrowRight';
import ArrowDropDownIcon from '@mui/icons-material/ArrowDropDown';
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
          <div key={index} style={{ width:'100%'}}>
          <S.AgreementContent style={{ alignItems: 'center', marginBottom: '10px', padding: 'auto' }}>
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

            <span onClick={() => handleOpenChange(index)}>
              {openStates[index]?(<ArrowDropDownIcon />) : (<ArrowRightIcon />)}
              {agreement.title}
            </span>
          </S.AgreementContent>
          {openStates[index] && <S.AgreementDetail>{agreement.content}</S.AgreementDetail>}
          </div>
        ))}
      </S.AgreementWrapper>

      <S.NextButton disabled={!meetRequired} onClick={() => onNext(checkboxStates)}>
        다음
      </S.NextButton>
    </S.AgreementForm>
  );
};

export default AgreementStep;
