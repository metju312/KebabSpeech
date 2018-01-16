package model.dao;

import model.entity.IngredientTemplate;

import java.util.List;
import java.util.logging.Logger;

public class IngredientTemplateDao extends GenericDaoImpl<IngredientTemplate> {
    private final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public IngredientTemplateDao() {
        super(IngredientTemplate.class);
    }

}
