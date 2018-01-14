package model.dao;

import model.entity.DishTemplate;

import java.util.List;
import java.util.logging.Logger;

public class DishTemplateDao extends GenericDaoImpl<DishTemplate> {
    private final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public DishTemplateDao() {
        super(DishTemplate.class);
    }


}
