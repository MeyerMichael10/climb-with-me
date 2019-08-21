package Liftoff.climbwithme.models.data;

import Liftoff.climbwithme.models.PartnerReq;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface PartnerReqDao extends CrudRepository<PartnerReq, Integer> {
}
