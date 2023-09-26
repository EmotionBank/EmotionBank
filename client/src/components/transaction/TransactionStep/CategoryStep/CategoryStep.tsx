import { useState } from 'react';
import * as S from '@/components/transaction/TransactionStep/CategoryStep/CategoryStep.style';
import useModal from '@/hooks/useModal';
import Modal from '@/components/common/Modal/Modal';
import CreateCategoryModal from '@/components/transaction/TransactionStep/CreateCategoryModal/CreateCategoryModal';

interface IProps {
  onNext: (categoryId: string) => void;
}

const CategoryStep = ({ onNext }: IProps) => {
  const [selectedCategory, setSelectedCategory] = useState<string>('');
  const { openModal, closeModal } = useModal();

  const dummy = [
    {
      categoryId: 'Id',
      categoryName: 'name',
      visibility: 'PUBLIC',
    },
    {
      categoryId: 'Id2',
      categoryName: 'name2',
      visibility: 'PRIVATE',
    },
  ];

  const convertvisiblity = (visibility: string) => {
    if (visibility === 'PUBLIC') return '전체공개';
    if (visibility === 'PRIVATE') return '비공개';
  };

  return (
    <>
      <S.CategoryStepWrapper>
        <S.CategoryHeaderContainer>
          <S.CategoryHeader>카테고리를 골라주세요</S.CategoryHeader>
        </S.CategoryHeaderContainer>
        <S.CategoryListWrapper>
          {dummy.map(category => (
            <S.CategoryContainer
              key={category.categoryId}
              onClick={() => setSelectedCategory(category.categoryId)}
              $isSelected={selectedCategory === category.categoryId}
            >
              <span>{category.categoryName}</span>
              <S.VisibilityText>{convertvisiblity(category.visibility)}</S.VisibilityText>
            </S.CategoryContainer>
          ))}
          <S.CreateCategoryButton onClick={openModal}>+ 새 카테고리 추가</S.CreateCategoryButton>
        </S.CategoryListWrapper>
        <S.NextButton onClick={() => onNext(selectedCategory)}>작성 완료</S.NextButton>
      </S.CategoryStepWrapper>
      <Modal>
        <CreateCategoryModal />
      </Modal>
    </>
  );
};

export default CategoryStep;