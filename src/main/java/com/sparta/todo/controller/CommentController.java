package com.sparta.todo.controller;

import com.sparta.todo.dto.ComRequestDto;
import com.sparta.todo.dto.ComResponseDto;
import com.sparta.todo.service.CommnetService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/todo")
public class CommentController {

    private final CommnetService commnetService;

    @PostMapping("/{todo_id}/comment")
    public ComResponseDto addComment(@PathVariable Long todo_id,
                                     @Valid @RequestBody ComRequestDto comRequestDto) {
        return commnetService.addComent(todo_id, comRequestDto);
    }

    @PutMapping("/{todo_id}/comment/{comment_id}")
    public ComResponseDto updateComment(@PathVariable Long todo_id,
                                        @PathVariable Long comment_id,
                                        @Valid @RequestBody ComRequestDto comRequestDto, HttpServletRequest request) {
        return commnetService.update(todo_id, comment_id, comRequestDto, request);
    }

    @DeleteMapping("/{todo_id}/comment/{comment_id}")
    public ResponseEntity<String> deleteComment(@PathVariable Long todo_id,
                                                @PathVariable Long comment_id,
                                                @Valid @RequestBody ComRequestDto comRequestDto, HttpServletRequest request) {
        commnetService.delete(todo_id,comment_id, comRequestDto, request);

        return new ResponseEntity<>("삭제완료", HttpStatus.OK);
    }


}
