package model.dao;

import model.entity.Ingredient;
import java.util.List;
import java.util.logging.Logger;

public class IngredientDao extends GenericDaoImpl<Ingredient> {
    private final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public IngredientDao() {
        super(Ingredient.class);
    }

    public List<Ingredient> findAll(){
        logger.info("Find Ingredients");
        return em.createQuery("from ingredient",Ingredient.class).getResultList();
    }
}
