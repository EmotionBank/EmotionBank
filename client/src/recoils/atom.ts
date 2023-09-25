import { atom, atomFamily } from 'recoil';

export const modalState = atomFamily({
  key: 'modalState',
  default: false,
});

export const signupIndex = atom({
  key: 'signupIndex',
  default: 0,
});
