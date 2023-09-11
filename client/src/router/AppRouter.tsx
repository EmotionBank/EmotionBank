import { RouterProvider, createBrowserRouter } from 'react-router-dom';

const AppRouter = () => {
  const router = createBrowserRouter([
    {
      path: '/',
      element: <></>,
      errorElement: <></>,
      children: [
        {
          path: '/login',
          element: <></>,
        },
      ],
    },
  ]);
  return <RouterProvider router={router} />;
};

export default AppRouter;
