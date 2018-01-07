package model.dao;

import model.entity.Drink;
import model.entity.Sauce;

import java.util.List;
import java.util.logging.Logger;

public class SauceDao extends GenericDaoImpl<Sauce> {
    private final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public SauceDao() {
        super(Sauce.class);
    }

    public List<Sauce> findAll(){
        logger.info("Find sauces");
        return em.createQuery("from Sauce",Sauce.class).getResultList();
    }
}


