### 1.DDD与Spring Data
DDD: domain-driven design(领域驱动设计)是复杂需求下软件开发的实现方式。有时间我将专门来讲解一下DDD。

Spring Data在很多地方都是按照DDD原则进行的设计(如Repository), 这里Spring Data主要是实现了DDD的`aggregate`和`domain event`:

- `aggregate`:一系列可以看成单一单元的领域对象的组合。如订单(order)和购物清单(line-items)都是单独的对象，但是将他们当成一个独立的单元(aggregate)。每个aggregate都有一个`aggregate root`,任何和外部交互应该只能通过`aggregate root`,这样`aggregate root`就可以确保`aggregate`的完整性。一个`aggregate`内部具有事务(数据一致性)边界。

- `domain event`: `aggregate`之间为了保证数据的一致性，使用`事件驱动架构(Event-Driven Architecture)`来实现数据的`最终一致性(Eventual consistency)`。而这些事件是通过`aggregate root`发布的`domain events`。


下面我们演示一个当`Person`这个`aggregate root` 保存成功后，通过`Person`的`gender`属性对统计实体`GenderStat`更新男女性别的统计数量。


### 2.演示

#### 2.1 Person Aggregate Root

`Person`作为Aggregate Root具备发布domain event的能力，在Spring Data 下可以有两种实现方式:
- 继承`AbstractAggregateRoot`，并使用其`registerEvent()`方法注册发布事件
- 使用`@DomainEvents`注解方法发布事件
  如：

```java
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "domainEvents")
public class Person  {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private Integer gender;//1:male;2:female

    @DomainEvents
    Collection<Object> domainEvents() {
        List<Object> events= new ArrayList<Object>();
        events.add(new PersonSavedEvent(this.id,this.gender));
        return events;
    }

    @AfterDomainEventPublication
    void callbackMethod() {
        //
    }

}
```

或

```java
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "domainEvents")
public class Person  extends AbstractAggregateRoot{
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private Integer gender;//1:male;2:female

    public Person afterPersonSavedCompleted(){
        registerEvent(new PersonSavedEvent(this.id,this.gender));
        return this;
    }


}
```

#### 2.2 监听事件处理程序
```java
@Component
public class GenderStatProcessor {
    @Autowired
    GenderRepository genderRepository;

    @Async
    @TransactionalEventListener
    public void handleAfterPersonSavedComplete(PersonSavedEvent event){

        GenderStat genderStat = genderRepository.findOne(1l);
        if(event.getGender()==1){
            genderStat.setMaleCount(genderStat.getMaleCount()+1);
        }else {
            genderStat.setFemaleCount(genderStat.getFemaleCount()+1);
        }
        genderRepository.save(genderStat);
    }
}
```
