package jaeik.growfarm.repository.comment;

import jaeik.growfarm.entity.board.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
 * 댓글 Repository
 * 댓글 관련 데이터베이스 작업을 수행하는 Repository
 * 커스텀 댓글 저장소를 상속받음
 * 수정일 : 2025-05-03
 */
@Repository
public interface CommentRepository extends JpaRepository<Comment, Long>, CommentCustomRepository {

    int countByPostId(Long postId);

    List<Comment> findByCommentList(Long postId);

    // 해당 유저의 작성 댓글 목록 반환
    Page<Comment> findByUserId(Long userId, Pageable pageable);

    // 해당 유저가 추천 누른 댓글 목록 반환
    Page<Comment> findByLikedComments(Long userId, Pageable pageable);

    @Modifying
    @Query("UPDATE Comment c SET c.isFeatured = false")
    void resetAllCommentFeaturedFlags();

    @Query("""
                SELECT c
                FROM Comment c
                WHERE (SELECT COUNT(cl) FROM CommentLike cl WHERE cl.comment = c) >= 3
                ORDER BY c.post.id, (SELECT COUNT(cl) FROM CommentLike cl WHERE cl.comment = c) DESC
            """)
    List<Comment> findPopularComments();

}
