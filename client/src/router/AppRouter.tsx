import Layout from '@/components/Layout/Layout';
import Main from '@/pages/Main/Main';
import Login from '@/pages/Login/Login';
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
        }
      ],
    },
  ]);
  return <RouterProvider router={router} />;
};

export default AppRouter;
