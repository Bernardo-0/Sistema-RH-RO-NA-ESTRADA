package br.com.estacio.ronaestrada.controller;

import br.com.estacio.ronaestrada.model.Usuario;
import br.com.estacio.ronaestrada.repository.UsuarioRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
@CrossOrigin()
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping("/registro")
    public ResponseEntity<String> registro(@RequestBody Usuario usuario) {
        Usuario usuarioExistente = usuarioRepository.findByEmail(usuario.getEmail());
        if (usuarioExistente != null) {
            return ResponseEntity.badRequest().body("O email já está em uso.");
        }

        usuario.setSenha(BCrypt.hashpw(usuario.getSenha(), BCrypt.gensalt()));

        usuarioRepository.save(usuario);
        return ResponseEntity.ok("Usuário registrado com sucesso");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Usuario usuario) {
        Usuario usuarioExistente = usuarioRepository.findByEmail(usuario.getEmail());
        if (usuarioExistente == null) {
            return ResponseEntity.badRequest().body("Email não encontrado.");
        }

        // Verifica a senha
        if (!BCrypt.checkpw(usuario.getSenha(), usuarioExistente.getSenha())) {
            return ResponseEntity.badRequest().body("Senha incorreta.");
        }

        return ResponseEntity.ok("Login bem-sucedido: " + usuarioExistente.getId());
    }
}