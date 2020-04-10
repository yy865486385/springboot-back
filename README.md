# 整合一个简单的springboot后端接口-Mybatis分支

## 需要整合的功能
- OAuth2
- log4j2
- aop
- swagger2
- ......
## 修改JPA为MybatisPlus
### 问题
1. 使用自定义语句插入和更新数据时无法使用MP自动生成id和审计信息。
2. 使用`BaseMapper`中的update时总是报错NullPointerException。
3. `entity`中`Set<String>`类型的字段在JPA中的时候数据库中存的是以逗号隔开的，改为mybatis之后无法使用convertor。