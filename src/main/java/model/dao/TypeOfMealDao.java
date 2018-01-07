package model.dao;

import model.entity.Sauce;
import model.entity.TypeOfMeal;

import java.util.List;
import java.util.logging.Logger;

public class TypeOfMealDao extends GenericDaoImpl<TypeOfMeal> {
    private final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public TypeOfMealDao() {
        super(TypeOfMeal.class);
    }

    public List<TypeOfMeal> findAll(){
        logger.info("Find typeOfMeals");
        return em.createQuery("from typeOfMeal",TypeOfMeal.class).getResultList();
    }
}