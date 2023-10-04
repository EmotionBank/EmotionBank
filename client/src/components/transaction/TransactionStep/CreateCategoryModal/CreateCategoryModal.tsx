import { useState } from 'react';
import * as S from '@/components/transaction/TransactionStep/CreateCategoryModal/CreateCategoryModal.style';
import { postCategoryRequestType } from '@/apis/category/postCategory';
import { usePostCategory } from '@/hooks/apiHooks/usePostCategory';

const CreateCategoryModal = () => {
  const initForm = {
    categoryName: '',
    visibility: 'PUBLIC',
  };
  const [categoryForm, setCategoryForm] = useState<postCategoryRequestType>(initForm);
  const postCategoryMutation = usePostCategory();

  const handleInputCategory = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;
    setCategoryForm({ ...categoryForm, [name]: value });
  };

  const handleCreateCategory = (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    postCategoryMutation.mutate(categoryForm);
  };
  return (
    <S.CreateCategoryModalWrapper>
      <S.CategoryForm onSubmit={handleCreateCategory} id="createCategory">
        <S.LabelContainer>
          <span>카테고리명 입력</span>
        </S.LabelContainer>
        <S.CategoryInput type="text" name="categoryName" onChange={handleInputCategory} />
        <S.LabelContainer>
          <span>공개 여부</span>
        </S.LabelContainer>
        <S.RadioContainer>
          <label>
            <input type="radio" name="visibility" value="PUBLIC" defaultChecked onChange={handleInputCategory} />
            공개
          </label>
          <label>
            <input type="radio" name="visibility" value="PRIVATE" onChange={handleInputCategory} />
            비공개
          </label>
        </S.RadioContainer>
      </S.CategoryForm>
      <S.CreateButton type="submit" form="createCategory">
        생성하기
      </S.CreateButton>
    </S.CreateCategoryModalWrapper>
  );
};

export default CreateCategoryModal;
