import { getUserFeed } from '@/apis/feed/getUserFeed';
import { QueryFunctionContext, useInfiniteQuery, useQuery } from '@tanstack/react-query';

export const useGetUserFeed = (pageParam: number) => {
  const { data } = useQuery(['feeds', pageParam], () => getUserFeed(pageParam));
  return data!;
};
// useInfiniteQuery(['feeds'], ({ pageParam = 0 }: QueryFunctionContext) => getUserFeed(pageParam), {
//   getNextPageParam: ( lastPage,allPages ) => ( pageParam + 1),
// });
