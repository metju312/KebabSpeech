package model.dao;

import model.entity.Addition;
import model.entity.Invoice;

import java.util.List;
import java.util.logging.Logger;

public class AdditionDao extends GenericDaoImpl<Addition> {
    private final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public AdditionDao() {
        super(Addition.class);
    }

    public List<Addition> findAll(){
        logger.info("Find additions");
        return em.createQuery("from addition",Addition.class).getResultList();
    }
}
