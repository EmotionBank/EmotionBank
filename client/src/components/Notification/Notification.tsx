import * as S from '@/components/Notification/Notification.style';
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

  return (
    <S.NorificationWrapper>
      <h3>💡 알림</h3>
      {getMyNotificationData.personalNotificationList.reverse().map(item => (
        <S.NorificationItem key={item.followerId}>{item.body}</S.NorificationItem>
      ))}
    </S.NorificationWrapper>
  );
};

export default Notification;
