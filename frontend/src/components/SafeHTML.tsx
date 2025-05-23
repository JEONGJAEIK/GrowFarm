import React from "react";
import { sanitizeHtml } from "@/util/sanitize";

interface SafeHTMLProps {
  html: string;
  className?: string;
}

/**
 * 사용자 입력 HTML을 안전하게 렌더링하는 컴포넌트
 *
 * DOMPurify를 사용하여 XSS 공격을 방지합니다.
 * dangerouslySetInnerHTML 대신 이 컴포넌트를 사용하세요.
 */
const SafeHTML: React.FC<SafeHTMLProps> = ({ html, className }) => {
  // 빈 문자열이면 렌더링하지 않음
  if (!html) return null;

  // HTML 콘텐츠 정화
  const sanitizedHtml = sanitizeHtml(html);

  return (
    <span
      className={className}
      dangerouslySetInnerHTML={{ __html: sanitizedHtml }}
    />
  );
};

export default SafeHTML;
