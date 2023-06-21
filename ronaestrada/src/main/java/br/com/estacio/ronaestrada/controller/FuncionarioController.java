package br.com.estacio.ronaestrada.controller;

import br.com.estacio.ronaestrada.model.Funcionario;
import br.com.estacio.ronaestrada.Service.FuncionarioService;
import br.com.estacio.ronaestrada.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/funcionario")
@CrossOrigin()
public class FuncionarioController {
    @Autowired
    private FuncionarioService funcionarioService;
    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @GetMapping("/get/{id}")
    public Funcionario get(@PathVariable(value = "id") int usuarioId) {
        Optional<Funcionario> optUsuario = funcionarioRepository.findById(usuarioId);
        return optUsuario.orElse(null);
    }

    @PostMapping("/add")
    public String add(@RequestBody Funcionario funcionario){
        funcionarioService.saveFuncionario(funcionario);
        return "Novo funcionario cadastrado!";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable(value = "id") int usuarioId) {
        Optional<Funcionario> optUsuario = funcionarioRepository.findById(usuarioId);
        if(optUsuario.isPresent()){
            funcionarioRepository.delete(optUsuario.get());
            return "Funcionario apagado com sucesso!";
        } else {
            return "Funcionario não encontrado!";
        }
    }

    @PutMapping("/update/{id}")
    public Funcionario updateFuncionario(@PathVariable int id, @RequestBody Funcionario funcionarioDetalhes) {
        Optional<Funcionario> optionalFuncionario = funcionarioRepository.findById(id);

        if(optionalFuncionario.isPresent()) {
            Funcionario funcionario = optionalFuncionario.get();
            funcionario.setNome(funcionarioDetalhes.getNome());
            funcionario.setCargo(funcionarioDetalhes.getCargo());
            funcionario.setSalario(funcionarioDetalhes.getSalario());

            return funcionarioRepository.save(funcionario);
        } else {
            return null;
        }
    }

    @GetMapping("/getAll")
    public List<Funcionario> getFuncionarios(){
        return funcionarioService.getAllFuncionario();
    }
}
