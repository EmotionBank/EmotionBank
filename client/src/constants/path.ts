export const PATH = {
  ROOT: '/',
  LOGIN: '/login',
  SIGNUP: '/signup',
  REDIRECTION: '/redirection',
  DETAIL: (transactionId: string | number) => `/detail/${transactionId}`,
  TRANSACTION: '/transaction',
  OTHER_USER: (userId: string | number) => `/user/${userId}`,
};
