import Header from '@/components/Layout/Header/Header';
import { LayoutWrapper } from '@/components/Layout/Layout.style';
import { Outlet } from 'react-router-dom';

const Layout = () => {
  return (
    <LayoutWrapper>
      <Header />
      <Outlet />
    </LayoutWrapper>
  );
};

export default Layout;
