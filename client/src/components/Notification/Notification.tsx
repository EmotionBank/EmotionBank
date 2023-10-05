import { NorificationWrapper } from '@/components/Notification/Notification.style';
import Modal from '@/components/common/Modal/Modal';
import { useGetMyNotification } from '@/hooks/apiHooks/useGetMyNotification';
import { useGetNotification } from '@/hooks/apiHooks/useGetNotification';
import useModal from '@/hooks/useModal';
import NotificationsNoneIcon from '@mui/icons-material/NotificationsNone';

const Notification = () => {
  const { openModal: openNotificationModal } = useModal('notification');

  return (
    <>
      <NotificationsNoneIcon onClick={openNotificationModal} style={{ fontSize: '2.2rem', cursor: 'pointer' }} />
      <Modal id="notification">
        <NotificationModal />
      </Modal>
    </>
  );
};

const NotificationModal = () => {
  const { getMyNotificationData } = useGetMyNotification();
  const { getNotificationData } = useGetNotification();
  return <NorificationWrapper></NorificationWrapper>;
};

export default Notification;
