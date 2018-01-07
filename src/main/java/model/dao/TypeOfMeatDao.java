package model.dao;

import model.entity.TypeOfMeal;
import model.entity.TypeOfMeat;

import java.util.List;
import java.util.logging.Logger;

public class TypeOfMeatDao extends GenericDaoImpl<TypeOfMeat> {
    private final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public TypeOfMeatDao() {
        super(TypeOfMeat.class);
    }

    public List<TypeOfMeat> findAll(){
        logger.info("Find typeOfMeats");
        return em.createQuery("from typeOfMeat",TypeOfMeat.class).getResultList();
    }
}
