package com.scheffel.tf_fds.dominio.persistencia;

import java.util.List;

import com.scheffel.tf_fds.dominio.modelos.ProdutoModel;

public interface IProdutoRepositorio {
    List<ProdutoModel> todos();
    ProdutoModel consultaPorId(long codigo);
}
