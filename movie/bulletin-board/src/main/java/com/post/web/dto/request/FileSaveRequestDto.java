package com.post.web.dto.request;

import lombok.Builder;
import lombok.Getter;

/**
 * @author      :   ymkim
 * @since       :   2023. 05. 20
 * @description :   게시글 등록 후 파일 저장 시 사용하는 Dto
 */
@Getter
public class FileSaveRequestDto {
    private Long fileId;
    private Long postId;
    private String originalName;
    private String saveName;
    private String filePath;
    private long fileSize;
    private String fileType;

    @Builder
    public FileSaveRequestDto(String originalName, String saveName, String filePath, long fileSize, String fileType) {
        this.originalName = originalName;
        this.saveName = saveName;
        this.filePath = filePath;
        this.fileSize = fileSize;
        this.fileType = fileType;
    }

    /**
     * 파일 저장의 경우 게시글 등록이 완료 된 이후에 진행 한다.
     * 해당 함수는 생성된 게시글 ID를 파일 요청 객체의 postId에 저장하는 용도로 사용
     * -> FileServiceImpl 클래스 참고
     */
    public void setPostId(Long postId) {
        this.postId = postId;
    }
}
