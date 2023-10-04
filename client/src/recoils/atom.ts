import { atom, atomFamily } from 'recoil';

export const isLoginState = atom({
  key: 'isLoginState',
  default: false,
});

export const modalState = atomFamily({
  key: 'modalState',
  default: false,
});

export const signupStep = atom<'agreement' | 'nickname' | 'birthday' | 'accountName'>({
  key: 'signupStep',
  default: 'agreement',
});
