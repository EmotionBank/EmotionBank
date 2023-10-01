import React, { Suspense } from 'react';
import { RecoilRoot } from 'recoil';
import AppRouter from '@/router/AppRouter';
import { QueryClient, QueryClientProvider } from '@tanstack/react-query';
import LoginCheck from '@/components/common/LoginCheck/LoginCheck';
import './firebase-messaging-sw.js';

const App = () => {
  const queryClient = new QueryClient({
    defaultOptions: {
      queries: {
        retry: 2,
        suspense: true,
        // useErrorBoundary: true,
      },
    },
  });
  return (
    <React.Fragment>
      <QueryClientProvider client={queryClient}>
        <Suspense fallback={<></>}>
          <RecoilRoot>
            <LoginCheck>
              <AppRouter />
            </LoginCheck>
          </RecoilRoot>
        </Suspense>
      </QueryClientProvider>
    </React.Fragment>
  );
};

export default App;
