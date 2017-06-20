package top.wisely.springdatadomainevent.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import top.wisely.springdatadomainevent.domain.Person;

/**
 * Created by wangyunfei on 2017/6/19.
 */
public interface PersonRepository extends JpaRepository<Person,Long>{
}
