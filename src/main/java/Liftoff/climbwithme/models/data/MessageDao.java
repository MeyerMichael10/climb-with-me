package Liftoff.climbwithme.models.data;

import Liftoff.climbwithme.models.Message;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import java.util.Optional;

@Repository
@Transactional
public interface MessageDao extends CrudRepository<Message, Integer> {

    Iterable<Message> findAllByUser(String user);
    Iterable<Message> findAllByRecipient(String recipient);
}
