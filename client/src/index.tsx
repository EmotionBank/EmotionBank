import { createRoot } from 'react-dom/client';
import App from '@/App';

const root = createRoot(document.querySelector('#root') as Element);
root.render(<App />);

if ('serviceWorker' in navigator) {
  window.addEventListener('load', () => {
    navigator.serviceWorker.register('/service-worker.js');
  })
}