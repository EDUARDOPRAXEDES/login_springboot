package turma.java.sistema_login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {
    
    @Autowired
    private UsuarioService usuarioService;
    
    @GetMapping("/")              // Página inicial - redireciona para login
    public String home() {
        return "redirect:/login";
    }
    
    @GetMapping("/login")     // Exibir página de login
    public String login() {
        return "login";
    }
    
   @PostMapping("/login") // Processar login
    public String processarLogin(@RequestParam String email, 
                               @RequestParam String senha, 
                               HttpSession session, 
                               Model model) {
        
        Usuario usuario = usuarioService.autenticar(email, senha);
        
        if (usuario != null) {
            session.setAttribute("usuarioLogado", usuario);
            return "redirect:/dashboard";
        } else {
            model.addAttribute("erro", "Email ou senha inválidos!");
            return "login";
        }
    }
       @GetMapping("/cadastro")    // Exibir página de cadastro
    public String cadastro() {
        return "cadastro";
    }
    
   @PostMapping("/cadastro") // Processar cadastro
    public String processarCadastro(@RequestParam String nome,
                                  @RequestParam String email,
                                  @RequestParam String senha,
                                  Model model) {
        
        if (usuarioService.emailJaExiste(email)) {
            model.addAttribute("erro", "Email já cadastrado!");
            return "cadastro";
        }
        
        Usuario novoUsuario = new Usuario(email, senha, nome);
        usuarioService.salvarUsuario(novoUsuario);
        
        model.addAttribute("sucesso", "Usuário cadastrado com sucesso!");
        return "login";
    }
    
    @GetMapping("/dashboard")                  // Dashboard (área logada)
    public String dashboard(HttpSession session, Model model) {
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
        
        if (usuario == null) {
            return "redirect:/login";
        }
        
        model.addAttribute("usuario", usuario);
        return "dashboard";
    }
    
    // Logout
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}

    
