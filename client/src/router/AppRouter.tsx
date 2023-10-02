import Layout from '@/components/Layout/Layout';
import { PATH } from '@/constants/path';
import Detail from '@/pages/Detail/Detail';
import Main from '@/pages/Main/Main';
import Login from '@/pages/Login/Login';
import Signup from '@/pages/Login/Signup';
import { RouterProvider, createBrowserRouter } from 'react-router-dom';
import Redirection from '@/pages/Login/Redirection';
import Transaction from '@/pages/Transaction/Transaction';
import Profile from '@/pages/Profile/Profile';
import Feed from '@/pages/Feed/Feed';

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
        { path: PATH.LOGIN, element: <Login /> },
        {
          path: PATH.REDIRECTION,
          element: <Redirection />,
        },
        {
          path: PATH.SIGNUP,
          element: <Signup />,
        },
        {
          path: PATH.TRANSACTION,
          element: <Transaction />,
        },
        {
          path: '/profile',
          element: <Profile />,
        },
        {
          path: '/feed',
          element: <Feed />,
        },
      ],
    },
  ]);
  return <RouterProvider router={router} />;
};

export default AppRouter;
