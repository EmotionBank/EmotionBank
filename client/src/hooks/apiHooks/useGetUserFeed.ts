import { getUserFeed } from '@/apis/feed/getUserFeed';
import { QueryFunctionContext, useInfiniteQuery } from '@tanstack/react-query';

export const useGetUserFeed = (size: number) =>
  useInfiniteQuery(['users'], ({ pageParam = 0 }: QueryFunctionContext) => getUserFeed(pageParam, size), {
    getNextPageParam: ({ data: { isLastPage, pageNumber } }) => (isLastPage ? undefined : pageNumber + 1),
  });
