
DROP TABLE IF EXISTS `zhenghe_rx`;

CREATE TABLE `zhenghe_rx` (
  `id` varchar(64) NOT NULL COMMENT 'id',
  `rx_name` varchar(64) DEFAULT NULL COMMENT '处方名称',
  `rx_no` varchar(64) DEFAULT NULL COMMENT '处方编号',
  `pay_type` varchar(2) DEFAULT NULL COMMENT '付款类型',
  `patient_id` varchar(64) DEFAULT '' COMMENT '患者编号',
  `patient_name` varchar(2) DEFAULT '' COMMENT '患者名称',
  `patient_gender` char(1) DEFAULT NULL COMMENT '患者性别',
  `patient_age` int(11) DEFAULT NULL COMMENT '患者年龄',
  `patient_address` varchar(120) DEFAULT '' COMMENT '患者地址',
  `patient_phone` varchar(21) DEFAULT '' COMMENT '患者电话',
  `case_no` varchar(64) DEFAULT '' COMMENT '病例号',
  `category` varchar(32) DEFAULT '' COMMENT '科别',
  `clinical_diagnosis` varchar(255) DEFAULT '' COMMENT '临床诊断',
  `doctor` varchar(10) DEFAULT '' COMMENT '医师',
  `approval_doctor` varchar(10) DEFAULT '' COMMENT '审核药师',
  `deploy_doctor` varchar(10) DEFAULT '' COMMENT '调配药师/士',
  `check_doctor` varchar(10) DEFAULT '' COMMENT '核对、发药药师',
  `total_amount` decimal(10,2) DEFAULT NULL COMMENT '药品金额',
  `status` char(2) DEFAULT NULL COMMENT '状态',
  `department_id` varchar(64) DEFAULT '' COMMENT '所属部门',
  `create_date` datetime DEFAULT NULL COMMENT '开具时间',
  `process_date` datetime DEFAULT NULL COMMENT '处理时间',
  `process_user` datetime DEFAULT NULL COMMENT '处理人',
  `delete_mark` varchar(32) DEFAULT '' COMMENT '删除标记',
  `remark` varchar(255) DEFAULT '' COMMENT '备注',
  `update_date` datetime DEFAULT NULL COMMENT '修改时间',
  `mender` varchar(32) DEFAULT '' COMMENT '修改者',
  `creator` varchar(32) DEFAULT NULL COMMENT '创建者',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `zhenghe_rx_detail`;
CREATE TABLE `zhenghe_rx_detail` (
  `id` varchar(64) NOT NULL COMMENT 'id',
  `rx_id` varchar(64) NOT NULL COMMENT '处方编号',
  `department_id` varchar(64) DEFAULT '' COMMENT '所属部门',
  `product_id` varchar(64) DEFAULT '''''' COMMENT '商品编号',
  `product_name` varchar(64) DEFAULT NULL COMMENT '药名',
  `price` decimal(10,2) DEFAULT NULL COMMENT '价格',
  `num` int(11) DEFAULT NULL COMMENT '数量',
  `standard` varchar(64) DEFAULT '' COMMENT '规格',
  `sig` varchar(64) DEFAULT '' COMMENT '使用说明',
  `create_date` datetime DEFAULT NULL COMMENT '开具时间',
  `delete_mark` varchar(32) DEFAULT '' COMMENT '删除标记',
  `remark` varchar(255) DEFAULT '' COMMENT '备注',
  `update_date` datetime DEFAULT NULL COMMENT '修改时间',
  `mender` varchar(32) DEFAULT '' COMMENT '修改者',
  `creator` varchar(32) DEFAULT NULL COMMENT '创建者',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `sys_dict` VALUES ('481f3e56ab1340c9b2e75c5bccd69dc3', '2', '已取消', 'rx_status', '待接收', 30, '0', '1', '2017-9-25 14:39:33', '1', '2017-9-25 14:39:33', '', '0');
INSERT INTO `sys_dict` VALUES ('5e9fcfd40be94492b3fad7c6ddab4c8c', '1', '已接收', 'rx_status', '待接收', 20, '0', '1', '2017-9-25 14:38:08', '1', '2017-9-25 14:38:08', '', '0');
INSERT INTO `sys_dict` VALUES ('61373fd4ac1f4268bd2c63c72c7f29fb', '0', '待接收', 'rx_status', '待接收', 10, '0', '1', '2017-9-25 14:37:47', '1', '2017-9-25 14:37:47', '', '0');
INSERT INTO `sys_dict` VALUES ('7e7e98b184624662a4e3a843b36783b3', '3', '隐藏', 'doctor_type', '专家', 30, '0', '1', '2017-9-21 10:07:56', '1', '2017-9-21 10:07:56', '不在APP显示', '0');