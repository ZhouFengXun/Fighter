查看某张表的索引情况。也可以通过命令：

```
 SHOW INDEX from my_dept;
```

查看整张表的建表语句，里面同样会显示索引情况。通过ALTER TABLE命令可以添加索引：

```
 ALTER TABLE my_dept add INDEX my_dept.parent_id(parent_id)
```

也可以通过CREATE INDEX命令添加索引：

```
CREATE INDEX parent_idON `my_dept ` 
```

不过这里有一个需要注意的地方是：想通过命令修改索引，是不行的。
目前在mysql中如果想要修改索引，只能先删除索引，再重新添加新的。
删除索引可以用DROP INDEX命令：

```
ALTER TABLE  my_dept DROP INDEX parent_id 
```

用DROP INDEX命令也行：

```
 DROP INDEX parent_id ON my_dept
```
explain命令，查看mysql的执行计划，它会显示索引的使用情况。

例如：

```
EXPLAIN select * from my_dept WHERE my_dept.dept_id=3
```
索引失效原因：

```
1、单值索引，尽量选择过滤性更好的字段，例如：性别字段，过滤度为50%，识别率很差，不建议建索引
2、组合索引，索引字段的顺序可以按照识别度进行排序，识别度越高，放在越靠前
3、组合索引，尽量包含where语句中的更多字段
4、尽可能的根据分析执行计划、统计信息，去调整query的写法达到合适索引的目的
索引容易失效的几个注意点

1、不在索引列上做任何的操作（计算、函数、类型转换），会导致索引失效而转向全表扫描
2、组合索引中，如果中间某个字段使用了范围条件，则右边的列索引失效
3、尽量使用覆盖索引（索引列和查询列一致），减少使用select *
4、mysql在使用不等于（！= 或者<>）的时候，无法使用索引列会导致全表扫描
5、is null ，is not null 也无法使用索引
6、like通配符必须放在索引列的右边，否则索引失效，编程全表扫描
7、字符串不加单引号索引失效
8、少用or，用它连接索引会失效
```
