package lesson7.dao;

import lesson7.domain.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class UserHibernateDao {
    public void save(User user){
        Transaction transaction = null;

        // auto close session object
        try (Session session = new Configuration().buildSessionFactory().openSession()) {

            // start the transaction
            transaction = session.beginTransaction();

            // save a object
            session.save(user);

            // commit transction
            transaction.commit();

        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }
}
