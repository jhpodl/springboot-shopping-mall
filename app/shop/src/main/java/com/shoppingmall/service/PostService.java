package com.shoppingmall.service;

import com.shoppingmall.constant.FileType;
import com.shoppingmall.utils.FileHandlerHelper;
import com.shoppingmall.utils.PaginationUtils;
import com.shoppingmall.common.MessageCode;
import com.shoppingmall.domain.Post;
import com.shoppingmall.dto.request.FileRequestDto;
import com.shoppingmall.dto.request.PostRequestDto;
import com.shoppingmall.dto.request.SearchRequestDto;
import com.shoppingmall.dto.response.FileResponseDto;
import com.shoppingmall.dto.response.PagingResponseDto;
import com.shoppingmall.dto.response.PostResponseDto;
import com.shoppingmall.repository.FileMapper;
import com.shoppingmall.repository.PostMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class PostService {

    private final PostMapper postMapper;
    private final FileMapper fileMapper;
    private final FileHandlerHelper fileHandlerHelper;

    @Transactional(readOnly = true)
    public PagingResponseDto<PostResponseDto> getPosts(SearchRequestDto searchRequestDto) {
        int totalRecordCount = postMapper.count(searchRequestDto);
        if (totalRecordCount < 1) {
            return new PagingResponseDto<>(Collections.emptyList(), null);
        }

        PaginationUtils pagination = new PaginationUtils(totalRecordCount, searchRequestDto);
        searchRequestDto.setPagination(pagination);

        List<PostResponseDto> posts = postMapper.getPosts(searchRequestDto)
                .stream()
                .map(PostResponseDto::new)
                .collect(Collectors.toList());

        return new PagingResponseDto<>(posts, pagination);
    }

    @Transactional(readOnly = true)
    public PostResponseDto getPostById(Long postId) {
        return postMapper.getPostById(postId)
                .map(post -> new PostResponseDto(post))
                .orElse(new PostResponseDto());
    }

    @Transactional
    public Long savePost(PostRequestDto postRequestDto) {
        Post post = new Post(postRequestDto);
        postMapper.savePost(post);
        return post.getPostId();
    }

    /**
     * Modifying the current file consists of deleting all existing files and re-uploading the user's uploads.
     * It looks like it needs to be modified.
     */
    @Transactional
    public int updatePost(PostRequestDto postRequestDto) {
        Long result = postMapper.updatePostById(new Post(postRequestDto));
        if (result == null || result == 0) {
            return MessageCode.FAIL.getCode();
        }

        List<MultipartFile> files = postRequestDto.getFiles();
        return updateFilesByPostId(postRequestDto.getPostId(), files);
    }

    private int updateFilesByPostId(Long postId, List<MultipartFile> files) {
        if (!isFileSizeOverThanZero(files)) {
            return MessageCode.FAIL.getCode();
        }

        List<FileResponseDto> fileResponseDtos = getFileResponseDtos(postId);
        fileHandlerHelper.deleteFiles(fileResponseDtos);

        int result = fileMapper.deleteFilesByPostId(postId);
        if (result > 0) {
            log.info("uploadFile is exists, result = {}", result);
            List<FileRequestDto> fileRequestDtos = fileHandlerHelper.uploadFiles(files, FileType.POSTS);
            setFileInfoPostId(postId, fileRequestDtos);
            fileMapper.saveFiles(fileRequestDtos);
        }
        return MessageCode.SUCCESS.getCode();
    }

    private boolean isFileSizeOverThanZero(List<MultipartFile> files) {
        return (files.size() > 0);
    }

    private void setFileInfoPostId(Long postId, List<FileRequestDto> fileRequestDtos) {
        fileRequestDtos.stream()
                .forEach(fileRequestDto -> fileRequestDto.setPostId(postId));
    }

    @Transactional
    public int deletePost(long postId) {
        Long deletedPostId = postMapper.deletePostById(postId);
        log.debug("deletedPostId = {}", deletedPostId);
        if (deletedPostId == 0) {
            return MessageCode.FAIL.getCode();
        }

        List<FileResponseDto> fileResponseDtos = getFileResponseDtos(postId);

        // empty라는 것은 해당 게시글에 매핑된 파일이 없다는 의미
        // 게시글 삭제 성공으로 판단 후 성공 코드 반환
        if (CollectionUtils.isEmpty(fileResponseDtos)) {
            return MessageCode.SUCCESS.getCode();
        }

        fileHandlerHelper.deleteFiles(fileResponseDtos);
        return fileMapper.deleteUpdateFilesByPostId(postId);
    }

    private List<FileResponseDto> getFileResponseDtos(long postId) {
        return fileMapper.getFilesByPostId(postId).stream()
                .map(file -> new FileResponseDto(file))
                .collect(Collectors.toList());
    }
}
