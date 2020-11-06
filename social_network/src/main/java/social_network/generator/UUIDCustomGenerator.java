package social_network.generator;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.UUIDGenerator;

import java.io.Serializable;

/**
 * Генератор UUID
 *
 */
public class UUIDCustomGenerator extends UUIDGenerator {

  @Override
  public Serializable generate(SharedSessionContractImplementor session, Object object)
    throws HibernateException {
    Serializable id = session.getEntityPersister(null, object)
      .getClassMetadata().getIdentifier(object, session);
    return id != null ? id : super.generate(session, object);
  }
}
