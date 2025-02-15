package com.svcbackend.mapper;

import com.svcbackend.model.CommentModel;
import com.svcbackend.model.CommentTableModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CommentMapper {
    List<CommentModel> findAllComment();
    List<CommentTableModel> findAllTableComment();
    CommentModel findByIdComment(@Param("idComentario") Integer idComentario);
    List<CommentModel> findByIdCommentChristian(@Param("idCristiano") Integer idCristiano);
    List<CommentModel> findByIdCommentLeader(@Param("idLider") Integer idLider);
    void registerComment(CommentModel memberStateModel);
    void updateComment(CommentModel memberStateModel);
    void deleteComment(@Param("idComentario") Integer idComentario);
}
