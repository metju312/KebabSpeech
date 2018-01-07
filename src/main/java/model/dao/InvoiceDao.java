package model.dao;

import model.entity.Invoice;
import org.h2.engine.User;

import java.util.List;
import java.util.logging.Logger;

public class InvoiceDao extends GenericDaoImpl<Invoice> {
    private final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public InvoiceDao() {
        super(Invoice.class);
    }

    public List<Invoice> findAll(){
        logger.info("Find invoices");
        return em.createQuery("from invoice",Invoice.class).getResultList();
    }
}
