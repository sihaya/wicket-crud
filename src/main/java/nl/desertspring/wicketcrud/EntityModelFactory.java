/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.desertspring.wicketcrud;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import nl.desertspring.wicketcrud.sample.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author sihaya
 */
@Service
public class EntityModelFactory
{

    
    private EntityManager entityManager;

    public <T> EntityModel<T> create(Class<T> entityKlass)
    {
        System.out.println(entityManager);
        return new EntityModel<T>(entityManager.getMetamodel().entity(entityKlass));
    }

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager)
    {
        this.entityManager = entityManager;
    }
    
    
}
