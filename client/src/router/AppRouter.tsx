import Layout from '@/components/Layout/Layout';
import Main from '@/pages/Main/Main';
import Login from '@/pages/Login/Login';
import Signup from '@/pages/Login/Signup';
import { RouterProvider, createBrowserRouter } from 'react-router-dom';
import Redirection from '@/pages/Login/Redirection';

const AppRouter = () => {
  const router = createBrowserRouter([
    {
      path: '/',
      element: <Layout></Layout>,
      errorElement: <></>,
      children: [
        {
          path: '',
          element: <Main></Main>,
        },
        {
          path:'/login',
          element:<Login></Login>
        },
        {
          path:'/redirection',
          element:<Redirection />
        },
        {
          path:'/signup',
          element:<Signup />
        },
      ],
    },
  ]);
  return <RouterProvider router={router} />;
};

export default AppRouter;
