package com.svcbackend.mapper;

import com.svcbackend.model.CommentModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CommentMapper {
    List<CommentModel> findAllComment();
    CommentModel findByIdComment(@Param("idComentario") Integer idComentario);
    void registerComment(CommentModel memberStateModel);
    void updateComment(CommentModel memberStateModel);
    void deleteComment(@Param("idComentario") Integer idComentario);
}
