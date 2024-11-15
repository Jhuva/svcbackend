package com.svcbackend.service;

import com.svcbackend.exception.GenericException;
import com.svcbackend.model.CommentModel;
import com.svcbackend.response.GenericResponse;

public interface CommentService {
    GenericResponse<Object> findAllComment() throws GenericException;
    GenericResponse<Object> findByIdComment(Integer idComentario) throws GenericException;
    GenericResponse<Object> registerComment(CommentModel comment);
    GenericResponse<Object> updateComment(CommentModel comment);
    GenericResponse<Object> deleteComment(Integer idComentario);
}
