import { createRoot } from 'react-dom/client';
import { ThemeProvider } from 'styled-components';
import App from '@/App';
import { theme } from '@/styles/theme';
import GlobalStyle from '@/styles/globalStyle';

const root = createRoot(document.querySelector('#root') as Element);
root.render(
  <ThemeProvider theme={theme}>
    <GlobalStyle />
    <App />
  </ThemeProvider>,
);

if (process.env.NODE_ENV === 'production') {
  if ('serviceWorker' in navigator) {
    window.addEventListener('load', () => {
      navigator.serviceWorker
        .register('/service-worker.js')
        .then(registration => {
          console.log('Service Worker registered with scope:', registration.scope);
        })
        .catch(error => {
          console.error('Service Worker registration failed:', error);
        });
    });
  }
}
