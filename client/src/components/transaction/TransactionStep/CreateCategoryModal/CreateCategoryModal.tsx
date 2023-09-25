import * as S from '@/components/transaction/TransactionStep/CreateCategoryModal/CreateCategoryModal.style';

const CreateCategoryModal = () => {
  return (
    <S.CreateCategoryModalWrapper>
      <S.CategoryForm>
        <S.LabelContainer>
          <span>카테고리명 입력</span>
        </S.LabelContainer>
        <S.CategoryInput />
        <S.LabelContainer>
          <span>공개 여부</span>
        </S.LabelContainer>
        <S.RadioContainer>
          <label>
            <input type="radio" name="visibility" checked />
            공개
          </label>
          <label>
            <input type="radio" name="visibility" />
            비공개
          </label>
        </S.RadioContainer>
      </S.CategoryForm>
      <S.CreateButton>생성하기</S.CreateButton>
    </S.CreateCategoryModalWrapper>
  );
};

export default CreateCategoryModal;
