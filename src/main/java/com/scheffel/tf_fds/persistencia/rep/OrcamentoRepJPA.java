package com.scheffel.tf_fds.persistencia.rep;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.scheffel.tf_fds.dominio.modelos.OrcamentoModel;

import com.scheffel.tf_fds.dominio.persistencia.IOrcamentoRepositorio;
import com.scheffel.tf_fds.persistencia.entity.Orcamento;
import com.scheffel.tf_fds.persistencia.jpa.OrcamentoJPA_ItfRep;

@Repository
@Primary
public class OrcamentoRepJPA implements IOrcamentoRepositorio {

    @Autowired
    private OrcamentoJPA_ItfRep orcamentos;

    @Override
    public List<OrcamentoModel> todos() {
        return orcamentos.findAll().stream()
                .map(o -> Orcamento.toOrcamentoModel(o))
                .toList();
    }

    @Override
    public OrcamentoModel cadastra(OrcamentoModel orcamento) {
        Orcamento orc = orcamentos.save(Orcamento.fromOrcamentoModel(orcamento));
        return Orcamento.toOrcamentoModel(orc);

    }

    @Override
    public OrcamentoModel recuperaPorId(long id) {
        Orcamento orcamento = orcamentos.findById(id).orElse(null);
        return Orcamento.toOrcamentoModel(orcamento);
    }

    @Override
    public void marcaComoEfetivado(long id) {
        Orcamento orcamento = orcamentos.findById(id).orElse(null);
        if (orcamento != null) {
            orcamento.efetiva();
            orcamentos.save(orcamento);
        }
        System.out.println("\n\n- efetivado: " + id);

    }
}
