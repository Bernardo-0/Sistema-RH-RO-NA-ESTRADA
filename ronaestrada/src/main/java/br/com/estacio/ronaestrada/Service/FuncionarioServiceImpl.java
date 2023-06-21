package br.com.estacio.ronaestrada.Service;

import br.com.estacio.ronaestrada.model.Funcionario;
import br.com.estacio.ronaestrada.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FuncionarioServiceImpl implements FuncionarioService {
    @Autowired
    private FuncionarioRepository funcionarioRepository;
    @Override
    public Funcionario saveFuncionario(Funcionario funcionario) {
        return funcionarioRepository.save(funcionario);
    }

    @Override
    public List<Funcionario> getAllFuncionario() {
        return funcionarioRepository.findAll();
    }
}
