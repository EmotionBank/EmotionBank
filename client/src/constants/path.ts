export const PATH = {
  ROOT: '/',
  LOGIN: '/login',
  SIGNUP: '/signup',
  REDIRECTION: '/redirection',
  DETAIL: (transactionId: string | number) => `/detail/${transactionId}`,
  TRANSACTION: '/transaction',
  PROFILE: '/profile',
  FEED: '/feed',
  REPORT: '/report',
  OTHER_USER: (userId: string | number) => `/user/${userId}`,
};
