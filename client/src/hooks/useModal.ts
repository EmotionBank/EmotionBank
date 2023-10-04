import { useCallback } from 'react';
import { useRecoilState } from 'recoil';
import { modalState } from '@/recoils/atom';

const useModal = (id?: string) => {
  const [modalToggleState, setModalToggleState] = useRecoilState(modalState(id));

  const openModal = useCallback(() => {
    setModalToggleState(true);
    document.body.style.overflow = 'hidden';
  }, [setModalToggleState]);

  const closeModal = useCallback(() => {
    setModalToggleState(false);
    document.body.style.overflow = 'unset';
  }, [setModalToggleState]);

  return { modalToggleState, openModal, closeModal };
};

export default useModal;
