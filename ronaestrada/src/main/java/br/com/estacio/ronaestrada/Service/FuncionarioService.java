package br.com.estacio.ronaestrada.Service;

import br.com.estacio.ronaestrada.model.Funcionario;

import java.util.List;

public interface FuncionarioService {
    public Funcionario saveFuncionario(Funcionario funcionario);
    public List<Funcionario> getAllFuncionario();
}
