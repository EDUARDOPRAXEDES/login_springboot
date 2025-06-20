package turma.java.sistema_login;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    @Column(unique = true, nullable = false)
    private String email;
    
    @Column(nullable = false)
    private String senha;
    
    @Column(nullable = false)
    private String nome;
    
    // Construtores
    public Usuario() {}
    
    public Usuario(String email, String senha, String nome) {
        this.email = email;
        this.senha = senha;
        this.nome = nome;
    }
    
    // Getters e Setters          ( Gerar os demais abaixo)
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

}

