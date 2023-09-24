import Layout from '@/components/Layout/Layout';
import { PATH } from '@/constants/path';
import Detail from '@/pages/Detail/Detail';
import Main from '@/pages/Main/Main';
import Login from '@/pages/Login/Login';
import Signup from '@/pages/Login/Signup';
import { RouterProvider, createBrowserRouter } from 'react-router-dom';
import Redirection from '@/pages/Login/Redirection';
import Transaction from '@/pages/Transaction/Transaction';

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
        { path: '/login', element: <Login></Login> },
        {
          path: '/redirection',
          element: <Redirection />,
        },
        {
          path: '/signup',
          element: <Signup />,
        },
        {
          path: PATH.TRANSACTION,
          element: <Transaction />,
        },
      ],
    },
  ]);
  return <RouterProvider router={router} />;
};

export default AppRouter;
