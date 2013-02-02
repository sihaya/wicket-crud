/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.desertspring.wicketcrud.sample;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 *
 * @author sihaya
 */
@Entity
public class Car implements Serializable
{

    @Id
    @GeneratedValue
    private Integer carId;
    private String name;
    private String description;
    @Column(nullable = false)
    private int lengthDimension;
    @Column(nullable = false)
    private int wheelbase;

    /**
     * @return the carId
     */
    public Integer getCarId()
    {
        return carId;
    }

    /**
     * @param carId the carId to set
     */
    public void setCarId(Integer carId)
    {
        this.carId = carId;
    }

    /**
     * @return the name
     */
    public String getName()
    {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * @return the description
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description)
    {
        this.description = description;
    }

    /**
     * @return the lengthDimension
     */
    public int getLengthDimension()
    {
        return lengthDimension;
    }

    /**
     * @param lengthDimension the lengthDimension to set
     */
    public void setLengthDimension(int lengthDimension)
    {
        this.lengthDimension = lengthDimension;
    }

    /**
     * @return the wheelbase
     */
    public int getWheelbase()
    {
        return wheelbase;
    }

    /**
     * @param wheelbase the wheelbase to set
     */
    public void setWheelbase(int wheelbase)
    {
        this.wheelbase = wheelbase;
    }
    
    
}
