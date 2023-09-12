import Calendar from '@/components/Calendar/Calendar';
import useModal from '@/hooks/useModal';
import { MainPageWrapper } from '@/pages/Main/Main.style';

const Main = () => {
  const { openModal, closeModal } = useModal();
  return (
    <MainPageWrapper>
      <Calendar />
    </MainPageWrapper>
  );
};

export default Main;
