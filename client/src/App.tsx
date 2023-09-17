import React from 'react';
import { RecoilRoot } from 'recoil';
import AppRouter from '@/router/AppRouter';
import { QueryClient, QueryClientProvider } from '@tanstack/react-query';

const App = () => {
  const queryClient = new QueryClient({
    defaultOptions: {
      queries: {
        retry: 2,
        suspense: true,
        useErrorBoundary: true,
      },
    },
  });
  return (
    <React.Fragment>
      <QueryClientProvider client={queryClient}>
        <RecoilRoot>
          <AppRouter />
        </RecoilRoot>
      </QueryClientProvider>
    </React.Fragment>
  );
};

export default App;
