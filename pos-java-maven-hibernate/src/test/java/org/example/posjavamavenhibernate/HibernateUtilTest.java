package org.example.posjavamavenhibernate;

import dao.DaoGeneric;
import model.Telefone;
import model.UsuarioPessoa;
import org.junit.Test;

import java.util.List;

public class HibernateUtilTest {
    @Test
    public void testeHibernateUtil() {
        DaoGeneric<UsuarioPessoa> usuarioPessoaDaoGeneric = new DaoGeneric<>();
        UsuarioPessoa usuarioPessoa = new UsuarioPessoa();

        usuarioPessoa.setNome("Gabriel");
        usuarioPessoa.setSobrenome("Sadrack");
        usuarioPessoa.setEmail("gabrielscr20sad23@gmail.com");
        usuarioPessoa.setLogin("sadrack10asd1");
        usuarioPessoa.setSenha("mariabonita");

        usuarioPessoaDaoGeneric.salvar(usuarioPessoa);
    }

    @Test
    public void testeHibernateBusca() {
        DaoGeneric<UsuarioPessoa> usuarioPessoaDaoGeneric = new DaoGeneric<>();
        UsuarioPessoa usuarioPessoa = new UsuarioPessoa();
        usuarioPessoa.setId(2L);

        UsuarioPessoa pesquisa = usuarioPessoaDaoGeneric.pesquisar(usuarioPessoa);
        System.out.println(pesquisa);
    }

    @Test
    public void testeHibernateBuscaPorId() {
        DaoGeneric<UsuarioPessoa> generic = new DaoGeneric<>();

        UsuarioPessoa pesquisa = generic.pesquisarPorId(1L, UsuarioPessoa.class);
        System.out.println(pesquisa);
    }

    @Test
    public void testeHibernateAtualiza() {
        DaoGeneric<UsuarioPessoa> generic = new DaoGeneric<>();
        UsuarioPessoa pessoaPesquisada = generic.pesquisarPorId(1L, UsuarioPessoa.class);
        pessoaPesquisada.setNome("Jorge");
        pessoaPesquisada.setSobrenome("Vercilo");

        generic.atualizar(pessoaPesquisada);

        System.out.println(pessoaPesquisada);
    }

    @Test
    public void testeHibernateDeletar() {
        DaoGeneric<UsuarioPessoa> generic = new DaoGeneric<>();
        UsuarioPessoa pessoaPesquisada = generic.pesquisarPorId(2L, UsuarioPessoa.class);
        generic.deletar(pessoaPesquisada);
    }

    @Test
    public void testeHibernateListar() {
        DaoGeneric<UsuarioPessoa> generic = new DaoGeneric<>();
        List<UsuarioPessoa> list = generic.listar(UsuarioPessoa.class);
        for (UsuarioPessoa pessoa : list) {
            System.out.println(pessoa.toString());
        }
    }

    @Test
    public void testeHibernateQueryListDePessoa() {
        DaoGeneric<UsuarioPessoa> generic = new DaoGeneric<>();
        List<UsuarioPessoa> list = generic.getEntityManager()
                .createQuery("from UsuarioPessoa")
                .getResultList();
        for (UsuarioPessoa pessoa : list) {
            System.out.println(pessoa.toString());
        }
    }

    @Test
    public void testeHibernateQueryListDePessoaMaxResult() {
        DaoGeneric<UsuarioPessoa> generic = new DaoGeneric<>();
        List<UsuarioPessoa> list = generic.getEntityManager()
                .createQuery("from UsuarioPessoa")
                .setMaxResults(5)
                .getResultList();

        for (UsuarioPessoa pessoa : list) {
            System.out.println(pessoa.toString());
        }
    }

    @Test
    public void teteHibernatePesquisaPorParametro() {
        DaoGeneric<UsuarioPessoa> generic = new DaoGeneric<>();
        List<UsuarioPessoa> list = generic.getEntityManager()
                .createQuery("from UsuarioPessoa where sobrenome = :sobrenome")
                .setParameter("sobrenome", "Sadrack")
                .getResultList();

        for (UsuarioPessoa pessoa : list) {
            System.out.println(pessoa);
        }
    }

    @Test
    public void teteHibernatePesquisaPorParametroNome() {
        DaoGeneric<UsuarioPessoa> generic = new DaoGeneric<>();
        List<UsuarioPessoa> list = generic.getEntityManager()
                .createQuery("from UsuarioPessoa where nome = :nome")
                .setParameter("nome", "Gabriel")
                .getResultList();

        for (UsuarioPessoa pessoa : list) {
            System.out.println(pessoa);
        }
    }

    @Test
    public void teteHibernatePesquisaPorParametroNomeESobrenome() {
        DaoGeneric<UsuarioPessoa> generic = new DaoGeneric<>();
        List<UsuarioPessoa> list = generic.getEntityManager()
                .createQuery("from UsuarioPessoa where nome = :nome and sobrenome = :sobrenome")
                .setParameter("nome", "Gabriel")
                .setParameter("sobrenome", "Sadrack")
                .getResultList();

        for (UsuarioPessoa pessoa : list) {
            System.out.println(pessoa);
        }
    }

    @Test
    public void teteHibernatePesquisaPorParametroNomeOuSobrenome() {
        DaoGeneric<UsuarioPessoa> generic = new DaoGeneric<>();
        List<UsuarioPessoa> list = generic.getEntityManager()
                .createQuery("from UsuarioPessoa where nome = :nome or sobrenome = :sobrenome")
                .setParameter("nome", "Josão")
                .setParameter("sobrenome", "Sadrack")
                .getResultList();

        for (UsuarioPessoa pessoa : list) {
            System.out.println(pessoa);
        }
    }

    @Test
    public void teteHibernateSomaIdade() {
        DaoGeneric<UsuarioPessoa> generic = new DaoGeneric<>();
        Long idades = (Long) generic.getEntityManager()
                .createQuery("SELECT SUM(u.idade) FROM UsuarioPessoa u").getSingleResult();
        System.out.println("As idades somadas: " + idades);
    }

    @Test
    public void teteHibernateMediaDeIdade() {
        DaoGeneric<UsuarioPessoa> generic = new DaoGeneric<>();
        Double idades = (Double) generic.getEntityManager()
                .createQuery("SELECT AVG(u.idade) FROM UsuarioPessoa u").getSingleResult();
        System.out.println("A média das idades: " + idades);
    }

    @Test
    public void testeNamedQueries1() {
        DaoGeneric<UsuarioPessoa> generic = new DaoGeneric<>();
        List resultList = generic.getEntityManager().createNamedQuery("UsuarioPessoa.findAll").getResultList();

        System.out.println(resultList);
    }

    @Test
    public void testeNamedQueries2() {
        DaoGeneric<UsuarioPessoa> generic = new DaoGeneric<>();
        Object singleResult = generic.getEntityManager()
                .createNamedQuery("UsuarioPessoa.findById")
                .setParameter("id", 5L)
                .getSingleResult();

        System.out.println(singleResult);
    }

    @Test
    public void testeTelefone() {
        DaoGeneric generic = new DaoGeneric();
        UsuarioPessoa pessoa = (UsuarioPessoa) generic.pesquisarPorId(10L, UsuarioPessoa.class);

        Telefone telefone = new Telefone();
        telefone.setNumero("95465132121");
        telefone.setTipo("Casa");
        telefone.setPessoa(pessoa);

        generic.salvar(telefone);
    }

}