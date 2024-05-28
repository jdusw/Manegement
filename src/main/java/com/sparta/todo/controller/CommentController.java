package com.sparta.todo.controller;

import com.sparta.todo.dto.ComRequestDto;
import com.sparta.todo.dto.ComResponseDto;
import com.sparta.todo.service.CommnetService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/todo")
public class CommentController {

    private final CommnetService commnetService;

    @PostMapping("/{todo_id}/comment")
    public ComResponseDto addComment(@PathVariable Long todo_id, @Valid @RequestBody ComRequestDto comRequestDto) {
        log.info("제바아아아아아알 찍혀라");
        return commnetService.addComent(todo_id, comRequestDto);
    }

    @PutMapping("/{todo_id}/comment/{comment_id}")
    public ComResponseDto updateComment(@PathVariable Long todo_id, @PathVariable Long comment_id, @Valid @RequestBody ComRequestDto comRequestDto) {
        return commnetService.update(todo_id, comment_id, comRequestDto);
    }

    @DeleteMapping("/{todo_id}/comment/{comment_id}")
    public ResponseEntity<String> deleteComment(@PathVariable Long todo_id, @PathVariable Long comment_id, @RequestBody ComRequestDto comRequestDto) {
        return commnetService.delete(todo_id,comment_id, comRequestDto);
    }


}
