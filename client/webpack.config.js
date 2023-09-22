const webpack = require('webpack');
const dotenv = require('dotenv');
const path = require('path');
const HtmlWebpackPlugin = require('html-webpack-plugin');
const WebpackPwaManifest = require('webpack-pwa-manifest');
const { GenerateSW } = require('workbox-webpack-plugin');

const PORT = 3000;
dotenv.config();
module.exports = (env, argv) => {
  const prod = argv.mode === 'production';

  const plugins = [
    new webpack.ProvidePlugin({
      React: 'react',
    }),
    new HtmlWebpackPlugin({
      template: path.resolve(__dirname, './public/index.html'),
      minify:
        process.env.NODE_ENV === 'production'
          ? {
              collapseWhitespace: true, // 빈칸 제거
              removeComments: true, // 주석 제거
            }
          : false,
    }),
    new webpack.DefinePlugin({
      'process.env': JSON.stringify(process.env),
    }),
  ];
  if (prod) {
    plugins.push(
      new WebpackPwaManifest({
        name: 'EmotionBank',
        description: 'EmotionBank',
        background_color: '#ffffff',
        crossOrigin: 'use-credentials',
        theme_color: '#eeeeee',
        icons: [
          {
            src: path.resolve('public/logo192.png'),
            sizes: [16, 24, 32, 64],
          },
          {
            src: path.resolve('public/logo192.png'),
            sizes: '192x192',
          },
          {
            src: path.resolve('public/logo512.png'),
            sizes: '512x512',
          },
        ],
      }),
    );
  }
  if (prod) {
    plugins.push(
      new GenerateSW({
        include: [/\.html$/, /\.js$/],
      }),
    );
  }

  return {
    mode: prod ? 'production' : 'development',
    devtool: prod ? 'hidden-source-map' : 'eval',
    entry: './src/index.tsx',
    output: {
      path: path.join(__dirname, '/dist'),
      filename: 'app.bundle.js',
      clean: true,
      publicPath: '/',
    },
    devServer: {
      port: PORT,
      hot: true,
      historyApiFallback: true,
    },
    resolve: {
      extensions: ['.js', '.jsx', '.ts', '.tsx', '.json'],
      alias: {
        '@': path.resolve(__dirname, './src/'),
        '@components': path.resolve(__dirname, './src/components'),
        '@assets': path.resolve(__dirname, './src/assets'),
        '@mui/styled-engine': '@mui/styled-engine-sc',
      },
    },
    module: {
      rules: [
        {
          test: /\.(js|jsx|ts|tsx)$/,
          exclude: /(node_modules)/,
          use: 'babel-loader',
        },
        {
          test: /\.(png|jpe?g|gif|ico)$/,
          type: 'asset/resource',
          generator: {
            filename: 'images/[hash][ext][query]',
          },
        },
      ],
    },
    plugins,
  };
};
