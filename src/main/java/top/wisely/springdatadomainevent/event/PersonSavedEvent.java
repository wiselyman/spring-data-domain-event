package top.wisely.springdatadomainevent.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by wangyunfei on 2017/6/19.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonSavedEvent {
    public Long id;
    private Integer gender;
}
