import { Button } from '@/components/common/Button/Button';
import Modal from '@/components/common/Modal/Modal';
import useModal from '@/hooks/useModal';

const Main = () => {
  const { openModal, closeModal } = useModal();
  return (
    <div>
      MAIN
      <Button onClick={openModal}>open</Button>
      <Modal>
        <h1>asdfasdf</h1>
      </Modal>
    </div>
  );
};

export default Main;
