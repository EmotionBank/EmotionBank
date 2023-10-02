import * as S from './AgreementStep.style';
import { useState } from 'react';
import { Button } from '../../../common/Button/Button';
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

  const handleCheckboxChange = (index: number) => {
    const newCheckboxStates = [...checkboxStates];
    newCheckboxStates[index] = !newCheckboxStates[index];

    // 필수 약관에 모두 동의한 경우 meetRequired를 true로 설정
    const allMandatoryAgreed = agreements.every(
      (agreement, i) => agreement.mandatory !== 'ESSENTIAL' || newCheckboxStates[i],
    );
    setMeetRequired(allMandatoryAgreed);

    // 체크박스 상태 업데이트
    setCheckboxStates(newCheckboxStates);
  };

  // useEffect(() => {
  //   agreements.map((agreement, index) => {
  //     if (agreement.mandatory === 'ESSENTIAL') {
  //       setMandatoryIndexList([...mandatoryIndexList, index]);
  //     }
  //   });
  // }, []);

  console.log(agreements);
  console.log(checkboxStates);

  return (
    <div>
      <S.AgreementTitle>서비스 이용을 위해 약관에 동의해주세요</S.AgreementTitle>
      {agreements.map((agreement, index) => (
        <div key={index}>
          <input type="checkbox" checked={checkboxStates[index]} onChange={() => handleCheckboxChange(index)} />
          <span>({agreement.mandatory === 'ESSENTIAL' ? '필수' : '선택'})</span>
          <span>{agreement.title}</span>
        </div>
      ))}

      <Button disabled={!meetRequired} onClick={() => onNext(checkboxStates)}>
        다음
      </Button>
    </div>
  );
};

export default AgreementStep;
