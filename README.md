# uid-spring-boot-starter

基于号段模式的分布式唯一ID生成 Spring Boot Starter，适用于高并发场景。

## 依赖引入

```xml
<dependency>
    <groupId>com.example</groupId>
    <artifactId>uid-spring-boot-starter</artifactId>
    <version>1.0.0</version>
</dependency>
```

## 数据库初始化

请先执行 `sql/uid.sql` 初始化号段表结构。

## 配置示例（application.yml）

```yaml
tinyid:
  biz-type: order_id
  token: 你的token
```

## 使用方式

```java
import com.example.uid.service.UidGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;

public class DemoService {
    @Autowired
    private UidGeneratorService uidGeneratorService;

    public void doSomething() {
        long id = uidGeneratorService.getId();
        // 使用id
    }
}
```

## 说明

- 支持多业务类型（biz-type）号段
