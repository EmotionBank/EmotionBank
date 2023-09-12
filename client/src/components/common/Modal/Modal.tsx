import useModal from '@/hooks/useModal';
import React from 'react';
import * as S from '@/components/common/Modal/Modal.style';

const Modal = ({ children, id }: { children: JSX.Element; id?: string }) => {
  const { modalToggleState, closeModal } = useModal(id);

  return (
    <>
      {modalToggleState && (
        <S.ModalWrapper>
          <S.ModalBackground onClick={closeModal} />
          <S.ModalBody>{children}</S.ModalBody>
        </S.ModalWrapper>
      )}
    </>
  );
};

export default React.memo(Modal);
