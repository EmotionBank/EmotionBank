import Header from '@/components/Layout/Header/Header';
import { LayoutWrapper, OutletWrapper } from '@/components/Layout/Layout.style';
import { Outlet } from 'react-router-dom';

const Layout = () => {
  return (
    <LayoutWrapper>
      <Header />
      <OutletWrapper>
        <Outlet />
      </OutletWrapper>
    </LayoutWrapper>
  );
};

export default Layout;
