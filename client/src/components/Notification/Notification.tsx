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
  // const { getNotificationData } = useGetNotification();
  // const dummy = {
  //   personalNotificationList: [
  //     {
  //       followerId: 4,
  //       followerNickname: '지은',
  //       body: '지은님이 회원님을 팔로우하기 시작했습니다.',
  //       notificationType: 'FOLLOW',
  //       createTime: '2023-10-05T13:19:30.597',
  //     },
  //     {
  //       followerId: 2,
  //       followerNickname: 'test',
  //       body: 'test님이 회원님을 팔로우하기 시작했습니다.',
  //       notificationType: 'FOLLOW',
  //       createTime: '2023-10-04T13:19:30.597',
  //     },
  //   ],
  // };
  return (
    <S.NorificationWrapper>
      <h3>💡 공지</h3>
      {getMyNotificationData.personalNotificationList.map(item => (
        <S.NorificationItem key={item.followerId}>{item.body}</S.NorificationItem>
      ))}
    </S.NorificationWrapper>
  );
};

export default Notification;
