package top.wisely.springdatadomainevent.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by wangyunfei on 2017/6/19.
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GenderStat {
    @Id
    @GeneratedValue
    private Long id;
    private Long maleCount;
    private Long femaleCount;
}
