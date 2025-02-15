package com.svcbackend.controller;

import com.svcbackend.exception.GenericException;
import com.svcbackend.model.CommentModel;
import com.svcbackend.response.GenericResponse;
import com.svcbackend.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/all")
    public GenericResponse<Object> findAllComment() throws GenericException {
        return commentService.findAllComment();
    }

    @GetMapping("/table")
    public GenericResponse<Object> findAllTableComment() throws GenericException {
        return commentService.findAllTableComment();
    }

    @GetMapping("/{idComment}")
    public GenericResponse<Object> findByIdComment(@PathVariable("idComment") Integer idComment) throws GenericException {
        return commentService.findByIdComment(idComment);
    }

    @GetMapping("/christian/{idChristian}")
    public GenericResponse<Object> findByIdCommentChristian(@PathVariable("idChristian") Integer idChristian) throws GenericException {
        return commentService.findByIdCommentChristian(idChristian);
    }

    @GetMapping("/leader/{idLeader}")
    public GenericResponse<Object> findByIdCommentLeader(@PathVariable("idLeader") Integer idLeader) throws GenericException {
        return commentService.findByIdCommentLeader(idLeader);
    }

    @PostMapping("/register")
    public GenericResponse<Object> registerComment(@RequestBody CommentModel comment) throws GenericException {
        return commentService.registerComment(comment);
    }

    @PutMapping("/update")
    public GenericResponse<Object> updateComment(@RequestBody CommentModel comment) throws GenericException {
        return commentService.updateComment(comment);
    }

    @DeleteMapping("/delete/{idComment}")
    public GenericResponse<Object> deleteComment(@PathVariable("idComment") Integer idComment) throws GenericException {
        return commentService.deleteComment(idComment);
    }


}
