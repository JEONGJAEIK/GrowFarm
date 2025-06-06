package jaeik.growfarm.controller;

import jaeik.growfarm.dto.admin.ReportDTO;
import jaeik.growfarm.dto.board.CommentDTO;
import jaeik.growfarm.global.auth.CustomUserDetails;
import jaeik.growfarm.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * <h2>댓글 관련 컨트롤러</h2>
 * <p>댓글 작성</p>
 * <p>댓글 수정</p>
 * <p>댓글 삭제</p>
 * <p>댓글 추천 / 추천 취소</p>
 * <p>댓글 신고</p>
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/board")
public class CommentController {

    private final CommentService commentService;

    /*
     * 댓글 작성 API
     * CustomUserDetails userDetails: 현재 로그인한 유저 정보
     * param Long postId: 게시글 ID
     * param CommentDTO commentDTO: 댓글 DTO
     * return: ResponseEntity<String> 댓글 작성 완료 메시지
     * 수정일 : 2025-05-06
     */
    @PostMapping("/{postId}/comment")
    public ResponseEntity<String> writeComment(@PathVariable Long postId,
                                               @RequestBody @Valid CommentDTO commentDTO) throws IOException {
        commentService.writeComment(postId, commentDTO);
        return ResponseEntity.ok("댓글 작성 완료");
    }

    /*
     * 댓글 수정 API
     * CustomUserDetails userDetails: 현재 로그인한 유저 정보
     * param Long postId: 게시글 ID
     * param Long commentId: 댓글 ID
     * param CommentDTO commentDTO: 댓글 DTO
     * return: ResponseEntity<String> 댓글 수정 완료 메시지
     * 수정일 : 2025-04-28
     */
    @PostMapping("/{postId}/{commentId}")
    public ResponseEntity<String> updateComment(
            @PathVariable Long postId,
            @PathVariable Long commentId,
            @RequestBody @Valid CommentDTO commentDTO) {
        commentService.updateComment(commentId, commentDTO);
        return ResponseEntity.ok("댓글 수정 완료");
    }

    /*
     * 댓글 삭제 API
     * CustomUserDetails userDetails: 현재 로그인한 유저 정보
     * param Long postId: 게시글 ID
     * param Long commentId: 댓글 ID
     * return: ResponseEntity<String> 댓글 삭제 완료 메시지
     * 수정일 : 2025-04-28
     */
    @PostMapping("/{postId}/{commentId}/delete")
    public ResponseEntity<String> deleteComment(
            @PathVariable Long postId,
            @PathVariable Long commentId) {
        commentService.deleteComment(commentId);
        return ResponseEntity.ok("댓글 삭제 완료");
    }

    /*
     * 댓글 추천 / 추천 취소 API
     * CustomUserDetails userDetails: 현재 로그인한 유저 정보
     * param Long postId: 게시글 ID
     * param Long commentId: 댓글 ID
     * return: ResponseEntity<String> 댓글 추천 완료 메시지
     * 수정일 : 2025-04-28
     */
    @PostMapping("/{postId}/{commentId}/like")
    public ResponseEntity<String> likeComment(@PathVariable Long postId, @PathVariable Long commentId) {
        commentService.likeComment(postId, commentId);
        return ResponseEntity.ok("댓글 추천 완료");
    }

    /*
     * 댓글 신고 API
     * CustomUserDetails userDetails: 현재 로그인한 유저 정보
     * param Long postId: 게시글 ID
     * param Long commentId: 댓글 ID
     * param ReportDTO reportDTO: 신고 DTO
     * return: ResponseEntity<String> 댓글 신고 완료 메시지
     * 수정일 : 2025-05-06
     */
    @PostMapping("/{postId}/comment/report")
    public ResponseEntity<String> reportComment(@AuthenticationPrincipal CustomUserDetails userDetails,
                                                @PathVariable Long postId,
                                                @RequestBody @Valid ReportDTO reportDTO) {
        commentService.reportComment(postId, userDetails, reportDTO);
        return ResponseEntity.ok("댓글 신고 완료");
    }
}
