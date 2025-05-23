"use client";

import { useEffect, useState } from "react";
import { SimplePostDTO } from "@/components/types/schema";
import Pagination from "@/components/Pagination";
import { formatDateTime } from "@/util/date";
import Link from "next/link";
import LoadingSpinner from "@/components/LoadingSpinner";
import { useRouter } from "next/navigation";
import useAuthStore from "@/util/authStore";
import fetchClient from "@/util/fetchClient";

const API_BASE = "https://grow-farm.com/api";



// 게시글 목록 아이템 컴포넌트
const PostItem = ({ post }: { post: SimplePostDTO }) => (
  <tr key={post.postId}>
    <td>
      <Link
        href={`/board/${post.postId}`}
        className="text-decoration-none text-dark"
      >
        {post.title}
        {post.commentCount > 0 && (
          <span className="text-primary ms-1">[{post.commentCount}]</span>
        )}
      </Link>
    </td>
    <td>{post.views}</td>
    <td>{post.likes}</td>
    <td>{formatDateTime(post.createdAt).split(" ")[0]}</td>
  </tr>
);

export default function MyPostsPage() {
  const router = useRouter();
  const { user } = useAuthStore();
  // 페이지 상태관리
  const [currentPage, setCurrentPage] = useState<number>(1);
  const [totalPages, setTotalPages] = useState<number>(5);
  const [loading, setLoading] = useState<boolean>(false);
  const [posts, setPosts] = useState<SimplePostDTO[]>([]);
  const [pageSize, setPageSize] = useState<number>(10);

  // 게시글 데이터 불러오기 (페이지별)
  useEffect(() => {
    const fetchPosts = async () => {
      if (!user) {
        router.push("/");
        return;
      }

      setLoading(true);
      try {
        const response = await fetchClient(
          `${API_BASE}/user/mypage/posts?page=${
            currentPage - 1
          }&size=${pageSize}`
        );

        if (response.ok) {
          const data = await response.json();
          setPosts(data?.content || []);
          setTotalPages(data?.totalPages || 1);
        } else {
          console.error(
            "내 게시글을 불러오는데 실패했습니다:",
            response.status
          );
        }
      } catch (error) {
        console.error("내 게시글 불러오기 오류:", error);
      } finally {
        setLoading(false);
      }
    };

    fetchPosts();
  }, [user, router, currentPage, pageSize]);

  // 페이지 변경 핸들러
  const handlePageChange = (page: number) => {
    setCurrentPage(page);
    // 페이지 상단으로 스크롤
    window.scrollTo({ top: 0, behavior: "smooth" });
  };

  // 페이지 사이즈 변경 핸들러
  const handlePageSizeChange = (e: React.ChangeEvent<HTMLSelectElement>) => {
    const newSize = parseInt(e.target.value, 10);
    setPageSize(newSize);
    setCurrentPage(1); // 페이지 크기가 변경되면 첫 페이지로 돌아감
  };

  return (
    <main className="flex-shrink-0">
      {/* 공간 띄우기 용 */}
      <header className="py-1 bg-white">
        <div className="text-center my-3"></div>
      </header>

      {/* 본문 */}
      <div className="container px-5">
        <div className="row">
          {/* 게시글 목록 */}
          <div className="col-lg-12">
            {/* 필터 영역 */}
            <div className="row mb-3">
              <div className="col-md-6"></div>
              <div className="col-md-6 text-end">
                <select
                  className="form-select"
                  value={pageSize}
                  onChange={handlePageSizeChange}
                  style={{ width: "auto", display: "inline-block" }}
                >
                  <option value={10}>10개 보기</option>
                  <option value={30}>30개 보기</option>
                  <option value={50}>50개 보기</option>
                </select>
              </div>
            </div>

            {/* 게시글 목록 테이블 */}
            <div className="card mb-4">
              <div className="card-body p-0">
                <div className="table-responsive">
                  <table className="table table-hover mb-0">
                    <thead className="bg-light">
                      <tr>
                        <th style={{ width: "60%" }}>제목</th>
                        <th style={{ width: "10%" }}>조회수</th>
                        <th style={{ width: "10%" }}>추천수</th>
                        <th style={{ width: "20%" }}>작성일</th>
                      </tr>
                    </thead>
                    <tbody>
                      {loading ? (
                        <tr>
                          <td colSpan={4} className="text-center">
                            <LoadingSpinner width={100} height={100} />
                          </td>
                        </tr>
                      ) : posts.length === 0 ? (
                        <tr>
                          <td colSpan={4} className="text-center py-4">
                            작성한 글이 없습니다.
                          </td>
                        </tr>
                      ) : (
                        posts.map((post) => (
                          <PostItem key={post.postId} post={post} />
                        ))
                      )}
                    </tbody>
                  </table>
                </div>
              </div>
            </div>

            {/* 페이지네이션 */}
            <div className="d-flex justify-content-center align-items-center">
              <Pagination
                currentPage={currentPage}
                totalPages={totalPages}
                onPageChange={handlePageChange}
                pageRangeDisplayed={5}
              />
            </div>
          </div>
        </div>
      </div>
    </main>
  );
}
