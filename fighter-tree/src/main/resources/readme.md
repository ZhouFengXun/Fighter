实现部门树以及任意树结构

~~~~
成都总公司
    研发部门
        产品部门
        ...
    市场部门
    ...
宜宾分公司
    营销部门
    运维部门
北京分公司
    ...
~~~~
具体实现代码：
~~~~
    public List<myDept> getTreeData() {
        //保存头节点
        List<myDept> arrayList = new ArrayList<>();
        //获取表中数据
        List<myDept> myDeptList = myDeptMapper.selectList(null);
        //将数据转换成map
        Map<Integer, myDept> myDeptMap = myDeptList.stream().collect(Collectors.toMap(myDept::getDeptId, myDept -> myDept));
        //添加子节点
        myDeptList.stream().forEach(myDept -> {
            if (myDept.getParentId() == 0){
                arrayList.add(myDept);
            }else {
                myDept MyDept = myDeptMap.get(myDept.getParentId());
                MyDept.getChildren().add(myDept);
            }
        });
        return arrayList;
    }
~~~~

sql
~~~~
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for my_dept
-- ----------------------------
DROP TABLE IF EXISTS `my_dept`;
CREATE TABLE `my_dept`  (
  `dept_id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id值',
  `parent_id` int(11) NULL DEFAULT NULL COMMENT '上级部门',
  `dept_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称',
  `sort` int(11) NULL DEFAULT NULL COMMENT '排序',
  `status` bit(1) NOT NULL COMMENT '状态',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`dept_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of my_dept
-- ----------------------------
INSERT INTO `my_dept` VALUES (1, 0, '成都总公司', 1, b'1', '2022-01-30 11:01:09', '2022-01-30 18:21:26');
INSERT INTO `my_dept` VALUES (2, 1, '研发部门', 1, b'1', '2022-01-30 11:01:28', '2022-01-30 11:01:30');
INSERT INTO `my_dept` VALUES (3, 1, '市场部门', 2, b'1', '2022-01-30 11:01:47', '2022-01-30 11:01:48');
INSERT INTO `my_dept` VALUES (4, 1, '运维部门', 3, b'1', '2022-01-30 11:02:01', '2022-01-30 11:02:04');
INSERT INTO `my_dept` VALUES (5, 0, '宜宾分公司', 2, b'1', '2022-01-30 11:07:36', '2022-01-30 14:18:48');
INSERT INTO `my_dept` VALUES (6, 5, '营销部门', 1, b'1', '2022-01-30 11:08:40', '2022-01-30 20:32:40');
INSERT INTO `my_dept` VALUES (7, 5, '运维部门', 2, b'1', '2022-01-30 11:08:56', '2022-01-30 18:03:56');
INSERT INTO `my_dept` VALUES (8, 5, '公关部门', 3, b'1', '2022-01-30 20:47:32', '2022-01-30 20:47:29');
INSERT INTO `my_dept` VALUES (9, 2, '产品部门', 1, b'1', '2022-03-21 14:10:00', '2022-03-21 14:10:05');
INSERT INTO `my_dept` VALUES (10, 0, '后勤部门', 1, b'1', '2022-03-21 14:12:16', '2022-03-21 14:12:21');
INSERT INTO `my_dept` VALUES (11, 2, '测试部门', 2, b'1', '2022-03-21 14:18:02', '2022-03-21 14:18:05');

SET FOREIGN_KEY_CHECKS = 1;
~~~~
