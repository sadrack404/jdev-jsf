package dao;

import org.example.posjavamavenhibernate.HibernateUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

public class DaoGeneric<E> {
    private final EntityManager entityManager = HibernateUtil.getEntityManager();

    public void salvar(E entidade) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(entidade);
        transaction.commit();
    }

    public E atualizar(E entidade) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        E merge = entityManager.merge(entidade);
        transaction.commit();

        return merge;
    }

    public E pesquisar(E entidade) {
        Object id = HibernateUtil.getPrimaryKey(entidade);
        return (E) entityManager.find(entidade.getClass(), id);
    }

    public E pesquisarPorId(Long id, Class<E> entidade) {
        return (E) entityManager.find(entidade, id);
    }

    public void deletar(E entidade) {
        Object id = HibernateUtil.getPrimaryKey(entidade);

        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.createNativeQuery("delete from " + entidade.getClass().getSimpleName().toLowerCase() + " where id = " + id).executeUpdate();
        transaction.commit();
    }

    public List<E> listar(Class<E> entidade) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        return entityManager.createQuery("from " + entidade.getName()).getResultList();
    }

    public EntityManager getEntityManager() {
        return this.entityManager;
    }

}
