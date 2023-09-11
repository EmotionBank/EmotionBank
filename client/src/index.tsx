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

if ('serviceWorker' in navigator) {
  window.addEventListener('load', () => {
    navigator.serviceWorker.register('/service-worker.js');
  });
}
