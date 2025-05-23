import type { NextConfig } from "next";

const nextConfig: NextConfig = {
  /* config options here */
  headers: async () => {
    return [
      {
        source: '/firebase-messaging-sw.js',
        headers: [
          {
            key: 'Service-Worker-Allowed',
            value: '/',
          },
          {
            key: 'Cache-Control',
            value: 'public, max-age=0, must-revalidate',
          },
        ],
      },
      {
        // 모든 페이지에 보안 헤더 적용
        source: '/:path*',
        headers: [
          {
            key: 'Content-Security-Policy',
            value: [
              "default-src 'self'",
              // 스크립트 소스 허용
              "script-src 'self' 'unsafe-inline' 'unsafe-eval' https://cdn.jsdelivr.net https://*.kakao.com https://*.kakao.sdk.io https://t1.kakaocdn.net https://postfiles.pstatic.net https://dapi.kakao.com https://www.gstatic.com https://www.gstatic.com/firebasejs/",
              // 스타일시트 허용
              "style-src 'self' 'unsafe-inline' https://cdn.jsdelivr.net",
              // 이미지 소스 허용
              "img-src 'self' data: https: http: https://postfiles.pstatic.net https://*.kakaocdn.net",
              // 폰트 소스 허용
              "font-src 'self' data: https://cdn.jsdelivr.net",
              // API 연결 허용
              "connect-src 'self' https: http: https://grow-farm.com ws://grow-farm.com https://*.kakao.com https://dapi.kakao.com",
              // 프레임 허용
              "frame-src 'self' https://*.kakao.com https://postfiles.pstatic.net about: chrome-extension: https://accounts.kakao.com",
              "object-src 'none'",
              "base-uri 'self'",
              "form-action 'self' https://*.kakao.com",
              // 미디어 소스 허용
              "media-src 'self' https://t1.kakaocdn.net https://postfiles.pstatic.net",
              // 카카오 공유 팝업 허용
              "child-src 'self' https://*.kakao.com about: chrome-extension:",
            ].join('; '),
          },
          {
            key: 'X-XSS-Protection',
            value: '1; mode=block',
          },
          {
            key: 'X-Content-Type-Options',
            value: 'nosniff',
          },
          {
            key: 'Referrer-Policy',
            value: 'strict-origin-when-cross-origin',
          },
        ],
      },
    ];
  },
};

export default nextConfig;
