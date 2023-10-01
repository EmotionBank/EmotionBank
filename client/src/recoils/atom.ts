import { atom, atomFamily } from 'recoil';

export const isLoginState = atom({
  key: 'isLoginState',
  default: false,
});

export const modalState = atomFamily({
  key: 'modalState',
  default: false,
});

export const signupIndex = atom({
  key: 'signupIndex',
  default: 0,
});
