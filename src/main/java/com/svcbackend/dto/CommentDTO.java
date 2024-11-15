package com.svcbackend.dto;

import lombok.Data;

@Data
public class CommentDTO {
    private Integer idComentario;
    private Integer idCristiano;
    private String comentario;
}
