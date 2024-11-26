package com.svcbackend.service.impl;

import com.svcbackend.dto.CommentDTO;
import com.svcbackend.dto.CommentTableDTO;
import com.svcbackend.exception.GenericException;
import com.svcbackend.mapper.CommentMapper;
import com.svcbackend.model.CommentModel;
import com.svcbackend.model.CommentTableModel;
import com.svcbackend.response.GenericResponse;
import com.svcbackend.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CommentImpl implements CommentService {

    private final CommentMapper commentMapper;

    @Override
    public GenericResponse<Object> findAllComment() throws GenericException {
        log.info("Obteniendo la lista de comentarios...");
        try {
            List<CommentModel> listComment = commentMapper.findAllComment();
            List<CommentDTO> listCommentDTO = new ArrayList<>();
            if(listComment != null && !listComment.isEmpty()) {
                listComment.forEach(comment -> {
                    CommentDTO commentDTO = fixSpacesCampsComment(comment);
                    listCommentDTO.add(commentDTO);
                });
                log.info("Lista de Comentarios obtenidos");
                return new GenericResponse<>(true, listCommentDTO, "Lista de Comentarios obtenidos");
            } else {
                log.info("No se cuenta con Comentarios");
                return new GenericResponse<>(false, "No hay lista de Comentarios");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new GenericException("Error: " + e.getMessage());
        }
    }

    @Override
    public GenericResponse<Object> findAllTableComment() throws GenericException {
        log.info("Obteniendo la lista de Comentarios...");
        try {
            List<CommentTableModel> listAllComment = commentMapper.findAllTableComment();
            List<CommentTableDTO> listAllSectorMinistriesDTO = new ArrayList<>();
            if(listAllComment != null && !listAllComment.isEmpty()) {
                listAllComment.forEach(comment -> {
                    CommentTableDTO sectorMinistriesAllDTO = fixSpacesCampsTableComment(comment);
                    listAllSectorMinistriesDTO.add(sectorMinistriesAllDTO);
                });
                log.info("Lista de Comentarios obtenidos");
                return new GenericResponse<>(true, listAllSectorMinistriesDTO, "Lista de Comentarios obtenidos");
            } else {
                log.info("No se cuenta con Comentarios");
                return new GenericResponse<>(false, "No hay lista de Comentarios");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new GenericException("Error: " + e.getMessage());
        }
    }

    @Override
    public GenericResponse<Object> findByIdComment(Integer idComment) throws GenericException {
        log.info("Obteniendo el comentario {}...", idComment);
        try {
            CommentModel commentRes = commentMapper.findByIdComment(idComment);
            if(commentRes != null) {
                CommentDTO commentDTO = fixSpacesCampsComment(commentRes);
                log.info("Comentario {}", idComment);
                return new GenericResponse<>(true, commentDTO, "Comentario " + idComment + " obtenido.");
            } else {
                log.info("No se cuenta con el comentario {}", idComment);
                return new GenericResponse<>(false, "No se cuenta con el comentario " + idComment);
            }
        } catch (Exception e) {
            throw new GenericException("Error: " + e.getMessage());
        }
    }

    @Override
    @Transactional
    public GenericResponse<Object> registerComment(CommentModel commentModel) {
        log.info("Registrando a un nuevo comentario {}... ", commentModel.getIdComentario());
        try {
            CommentModel idComment = commentMapper.findByIdComment(commentModel.getIdComentario());
            if(idComment != null) {
                log.error("Error al guardar al comentario. El comentario {} ya existe.", commentModel.getIdComentario());
                return new GenericResponse<>(false, "Error al guardar el comentario, ya existe.");
            } else {
                log.info("Se ha registrado al comentario {}.", commentModel.getIdComentario());
                commentMapper.registerComment(commentModel);
                return new GenericResponse<>(true, "Se ha registrado el comentario");
            }
        } catch (Exception e) {
            log.error("Error: {}", e.getMessage());
            e.printStackTrace();
            return new GenericResponse<>(false, "Error al momento de registrar el comentario");
        }
    }

    @Override
    @Transactional
    public GenericResponse<Object> updateComment(CommentModel commentModel) {
        log.info("Actualizando el comentario {}... ", commentModel.getIdComentario());
        try {
            if(commentModel.getIdComentario() != null) {
                CommentModel idComment = commentMapper.findByIdComment(commentModel.getIdComentario());
                if(idComment != null) {
                    commentMapper.updateComment(commentModel);
                    return new GenericResponse<>(true, "Comentario actualizado.");
                } else {
                    log.info("Error al momento de actualizar el comentario {}. No existe", commentModel.getIdComentario());
                    return new GenericResponse<>(false, "Error al momento de actualizar. El comentario no existe");
                }
            } else {
                log.error("Error al momento de actualizar. El comentario no debe ser nulo");
                return new GenericResponse<>(false, "Id del comentario es nulo");
            }
        } catch (Exception e) {
            log.error("Error: {}", e.getMessage());
            e.printStackTrace();
            return new GenericResponse<>(false, "Error al momento de actualizar el comentario ");
        }
    }

    @Override
    @Transactional
    public GenericResponse<Object> deleteComment(Integer idComment) {
        log.info("Eliminando comentario {}...", idComment);
        try {
            CommentModel commentRes = commentMapper.findByIdComment(idComment);
            if(commentRes.getIdComentario() != null) {
                commentMapper.deleteComment(idComment);
                log.info("Comentario eliminado {}", idComment);
                return new GenericResponse<>(true, "Comentario eliminado.");
            } else {
                log.info("No se cuenta con el siguiente comentario {}", idComment);
                return new GenericResponse<>(false, "No se cuenta con el comentario");
            }
        } catch (Exception e) {
            log.error("Error: {}", e.getMessage());
            e.printStackTrace();
            return new GenericResponse<>(false, "Error al momento de eliminar el comentario " + idComment);
        }
    }

    private CommentTableDTO fixSpacesCampsTableComment(CommentTableModel commentTableModel) {
        CommentTableDTO commentTableDTO = new CommentTableDTO();
        BeanUtils.copyProperties(commentTableModel, commentTableDTO);
        if(commentTableDTO.getIdCristiano() != null) {
            commentTableDTO.setIdCristiano(commentTableModel.getIdCristiano());
        }
        if(commentTableDTO.getNombres() != null) {
            commentTableDTO.setNombres(commentTableModel.getNombres().trim());
        }
        if(commentTableDTO.getApellidos() != null) {
            commentTableDTO.setApellidos(commentTableModel.getApellidos().trim());
        }
        if(commentTableDTO.getEstado() != null) {
            commentTableDTO.setEstado(commentTableModel.getEstado().trim());
        }
        if(commentTableDTO.getAreaServicio() != null) {
            commentTableDTO.setAreaServicio(commentTableModel.getAreaServicio());
        }
        if(commentTableDTO.getGrado() != null) {
            commentTableDTO.setGrado(commentTableModel.getGrado());
        }
        if(commentTableDTO.getRol() != null) {
            commentTableDTO.setRol(commentTableModel.getRol());
        }
        if(commentTableDTO.getSectorMinisterio() != null) {
            commentTableDTO.setSectorMinisterio(commentTableModel.getSectorMinisterio());
        }
        if(commentTableDTO.getZona() != null) {
            commentTableDTO.setZona(commentTableModel.getZona());
        }
        return commentTableDTO;
    }

    private CommentDTO fixSpacesCampsComment(CommentModel commentModel) {
        CommentDTO commentDTO = new CommentDTO();
        BeanUtils.copyProperties(commentModel, commentDTO);
        if(commentDTO.getIdComentario() != null) {
            commentDTO.setIdComentario(commentModel.getIdComentario());
        }
        if(commentDTO.getIdCristiano() != null) {
            commentDTO.setIdCristiano(commentModel.getIdCristiano());
        }
        if(commentDTO.getComentario() != null) {
            commentDTO.setComentario(commentModel.getComentario().trim());
        }
        return commentDTO;
    }

}
