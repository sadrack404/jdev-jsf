package model;

import javax.persistence.*;

@Entity
public class Telefone {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private String tipo;
    @Column(nullable = false)
    private String numero;
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private UsuarioPessoa pessoa;

    public UsuarioPessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(UsuarioPessoa pessoa) {
        this.pessoa = pessoa;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }
}
