package com.sparta.todo.controller;

import com.sparta.todo.dto.CrequestDto;
import com.sparta.todo.dto.CresponseDto;
import com.sparta.todo.service.CommnetService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/todo")
public class CommentController {

    private final CommnetService commnetService;

    @PostMapping("/{todo_id}/comment")
    public CresponseDto addComment(@PathVariable Long todo_id, @RequestBody CrequestDto crequestDto) {
        if(crequestDto.getContent() == null &&  crequestDto.getContent().equals("")){
            throw new IllegalArgumentException("내용을 입력해주세요");
        }
        return commnetService.addComent(todo_id,crequestDto);
    }

    @PutMapping("/{todo_id}/comment/{comment_id}")
    public CresponseDto updateComment(@PathVariable Long todo_id, @PathVariable Long comment_id, @RequestBody CrequestDto crequestDto) {
        if(crequestDto.getContent() == null &&  crequestDto.getContent().equals("")){
            throw new IllegalArgumentException("내용을 입력해주세요");
        }
        return commnetService.update(todo_id, comment_id, crequestDto);
    }

    @DeleteMapping("/{todo_id}/comment/{comment_id}")
    public ResponseEntity<String> deleteComment(@PathVariable Long todo_id, @PathVariable Long comment_id, @RequestBody CrequestDto crequestDto) {
        return commnetService.delete(todo_id,comment_id, crequestDto);
    }

}
