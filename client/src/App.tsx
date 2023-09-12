import React from 'react';
import { RecoilRoot } from 'recoil';
import AppRouter from '@/router/AppRouter';

const App = () => {
  return (
    <React.Fragment>
      <RecoilRoot>
        <AppRouter />
      </RecoilRoot>
    </React.Fragment>
  );
};

export default App;
