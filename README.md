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

### 注意事项
1. 修改entity时，要new一个entity然后赋值id，再使用`updateById`,而不能先查出entity再修改值。否则自动填入更新时间无法正常记录。
2. JPA中的`Convertor`修改为`TypeHandler`