import { useState } from 'react';
import * as S from '@/components/transaction/TransactionStep/CategoryStep/CategoryStep.style';
import useModal from '@/hooks/useModal';
import Modal from '@/components/common/Modal/Modal';
import CreateCategoryModal from '@/components/transaction/TransactionStep/CreateCategoryModal/CreateCategoryModal';
import { useGetCategoryList } from '@/hooks/apiHooks/useGetCategoryList';
import { CategoryResponse } from '@/types/bank';

interface IProps {
  onNext: (categoryId: number) => void;
}

const CategoryStep = ({ onNext }: IProps) => {
  const [selectedCategory, setSelectedCategory] = useState<number>(0);
  const { openModal, closeModal } = useModal('createCategory');
  const { getCategoryListData } = useGetCategoryList();

  const convertvisiblity = (visibility: string) => {
    if (visibility === 'PUBLIC') return '전체공개';
    if (visibility === 'PRIVATE') return '비공개';
  };
  console.log(getCategoryListData);
  return (
    <>
      <S.CategoryStepWrapper>
        <S.CategoryHeaderContainer>
          <S.CategoryHeader>카테고리를 골라주세요</S.CategoryHeader>
        </S.CategoryHeaderContainer>
        <S.CategoryListWrapper>
          {getCategoryListData.categoryInfoList.map((category: CategoryResponse) => (
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
      <Modal id="createCategory">
        <CreateCategoryModal />
      </Modal>
    </>
  );
};

export default CategoryStep;
