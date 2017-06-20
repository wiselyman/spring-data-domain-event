package top.wisely.springdatadomainevent.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import top.wisely.springdatadomainevent.domain.GenderStat;

/**
 * Created by wangyunfei on 2017/6/19.
 */
public interface GenderRepository  extends JpaRepository<GenderStat,Long> {
}
