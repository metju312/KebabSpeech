package model.dao;

import model.entity.Dish;

import java.util.List;
import java.util.logging.Logger;

public class DishDao extends GenericDaoImpl<Dish> {
    private final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public DishDao() {
        super(Dish.class);
    }

}
