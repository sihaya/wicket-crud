/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.desertspring.wicketcrud;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author sihaya
 */
@Repository
public class CrudRepository
{
    private EntityManager entityManager;
    
    @Transactional
    public <T> T merge(T entity) {
        return entityManager.merge(entity);
    }

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager)
    {
        this.entityManager = entityManager;
    }
    
    public <T> List<T> findAll(EntityModel<T> entityModel) {
        return null;
    }
}
