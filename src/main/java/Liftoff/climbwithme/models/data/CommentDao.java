package Liftoff.climbwithme.models.data;

import Liftoff.climbwithme.models.Comment;
import Liftoff.climbwithme.models.Post;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface CommentDao extends CrudRepository<Comment, Integer> {

    Iterable<Comment> findAllByParentId(int postId);
}
