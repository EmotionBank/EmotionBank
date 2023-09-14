import Layout from '@/components/Layout/Layout';
import { PATH } from '@/constants/path';
import Detail from '@/pages/Detail/Detail';
import Main from '@/pages/Main/Main';
import { RouterProvider, createBrowserRouter } from 'react-router-dom';

const AppRouter = () => {
  const router = createBrowserRouter([
    {
      path: '/',
      element: <Layout></Layout>,
      errorElement: <></>,
      children: [
        {
          path: PATH.ROOT,
          element: <Main />,
        },
        {
          path: PATH.DETAIL(':transactionId'),
          element: <Detail />,
        },
      ],
    },
  ]);
  return <RouterProvider router={router} />;
};

export default AppRouter;
