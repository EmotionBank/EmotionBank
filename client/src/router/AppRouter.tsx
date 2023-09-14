import Layout from '@/components/Layout/Layout';
import { PATH } from '@/constants/path';
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
      ],
    },
  ]);
  return <RouterProvider router={router} />;
};

export default AppRouter;
