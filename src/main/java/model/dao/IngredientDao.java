package model.dao;

import java.util.logging.Logger;

public class IngredientDao extends GenericDaoImpl<Ingredient> {
    private final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private Ingredient ingredient;

    public IngredientDao(Ingredient ingredient) {
        super(Ingredient.class);
        this.ingredient = ingredient;
    }
}
