import { getCategotyList } from '@/apis/category/getCategotyList';
import { useQuery } from '@tanstack/react-query';

export const useGetCategoryList = () => {
  const { data } = useQuery(['categoryList'], getCategotyList);
  return { getCategoryListData: data! };
};
