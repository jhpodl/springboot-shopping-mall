package com.post.domain.posts;

import com.post.web.dto.request.PostRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@ToString
@Getter
@Setter
@NoArgsConstructor // https://akku-dev.tistory.com/34
public class Post {
    private Long postId;
    private Long memberId;
    private String title;
    private String content;
    private int readCnt;
    private String fixedYn;
    private String delYn;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
    private LocalDateTime deleteDate;

    public Post(PostRequestDto postRequestDto) {
        this.postId = postRequestDto.getPostId();
        this.memberId = postRequestDto.getMemberId();
        this.title = postRequestDto.getTitle();
        this.content = postRequestDto.getContent();
        this.fixedYn = postRequestDto.getFixedYn();
    }
}
