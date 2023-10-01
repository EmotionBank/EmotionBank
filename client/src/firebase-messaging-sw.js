import { initializeApp } from 'firebase/app';
import { getMessaging, getToken, onMessage } from 'firebase/messaging';

const firebaseConfig = {
  apiKey: process.env.REACT_APP_FCM_API_KEY,
  authDomain: 'emotionbank-f8d93.firebaseapp.com',
  projectId: 'emotionbank-f8d93',
  storageBucket: 'emotionbank-f8d93.appspot.com',
  messagingSenderId: '358570287045',
  appId: process.env.REACT_APP_FCM_APP_ID,
};
const app = initializeApp(firebaseConfig);
const messaging = getMessaging(app);

async function requestPermission() {
  console.log('권한 요청 중...');

  const permission = await Notification.requestPermission();
  if (permission === 'denied') {
    console.log('알림 권한 허용 안됨');
    return;
  }

  console.log('알림 권한이 허용됨');

  const token = await getToken(messaging, {
    vapidKey: process.env.REACT_APP_FCM_VAPID_KEY,
  });

  if (token) {
    console.log('TOKEN 발금 완료!');
    localStorage.setItem('FCM_token', token);
  } else console.log('Can not get Token');

  onMessage(messaging, payload => {
    console.log('메시지가 도착했습니다.', payload);
    // ...
  });
}

requestPermission();
