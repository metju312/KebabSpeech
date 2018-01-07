package model.dao;

import model.entity.Dish;
import model.entity.Drink;

import java.util.List;
import java.util.logging.Logger;

public class DrinkDao extends GenericDaoImpl<Drink> {
    private final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public DrinkDao() {
        super(Drink.class);
    }

    public List<Drink> findAll(){
        logger.info("Find drinks");
        return em.createQuery("from drink",Drink.class).getResultList();
    }
}