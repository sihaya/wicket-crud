/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.desertspring.wicketcrud;

import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.SingularAttribute;

/**
 *
 * @author sihaya
 */
public class EntityModel<T>
{

    private EntityType<T> entityClass;

    public static class SimpleSingularAttribute<T>
    {

        private Member member;
        private String name;

        private SimpleSingularAttribute(String name, Member javaMember)
        {
            this.member = javaMember;
            this.name = name;
        }

        public String getName()
        {
            return name;
        }
        
        

        public Object getValue(T entity)
        {
            if (member instanceof Field) {
                try {
                    return ((Field) member).get(entity);
                }
                catch (Exception ex) {
                    throw new IllegalStateException(ex);
                }
            }

            return null;
        }
        
        public void setValue(T entity, Object value) {
            if (member instanceof Field) {
                try {
                    ((Field) member).set(entity, value);
                }
                catch (Exception ex) {
                    throw new IllegalStateException(ex);
                }
            }
        }
    }

    EntityModel(EntityType<T> entityClass)
    {
        this.entityClass = entityClass;
    }

    public List<SimpleSingularAttribute> getSingularAttributes()
    {
        List<SimpleSingularAttribute> results = new ArrayList<SimpleSingularAttribute>();

        for (SingularAttribute singularAttribute : entityClass.getSingularAttributes()) {
            results.add(new SimpleSingularAttribute<T>(singularAttribute.getName(), singularAttribute.getJavaMember()));
        }

        return results;
    }
}
