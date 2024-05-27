package com.sparta.todo.controller;

import com.sparta.todo.dto.CrequestDto;
import com.sparta.todo.dto.CresponseDto;
import com.sparta.todo.service.CommnetService;
import lombok.AllArgsConstructor;
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

}
