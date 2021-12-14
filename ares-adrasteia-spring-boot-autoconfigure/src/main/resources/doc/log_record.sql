create table db_tendering.business_operator_log
(
    `id`                bigint(20) unsigned not null auto_increment comment '主键ID',
    `biz_id`            varchar(64)         not null comment '项目ID',
    `child_biz_id`      varchar(128)        null comment '子业务ID',
    `operator_id`       bigint(20) unsigned not null comment '操作人ID',
    `operator_name`     varchar(128)        not null comment '操作人名称',
    `operator_type`     varchar(8)          not null comment '用户类别',
    `identity_type`     varchar(8)          null comment '身份类别, <提到注解>',
    `institution_id`    bigint(20) unsigned null comment '操作人所属机构ID',
    `institution_name`  varchar(128)        null comment '操作人所属机构名称',
    `trace_id`          varchar(32)         null comment '修改标识<request-id>',
    `content`           varchar(512)        not null comment '操作日志记录',
    `action_node`       varchar(64)         not null comment '操作节点',
    `action_class_name` varchar(128)        null comment '操作全类名',
    `action_type`       varchar(32)         null comment '操作类型',
    `context`           varchar(500)        null comment '操作日志上下文,json格式',
    `created_time`      datetime default current_timestamp comment '创建时间',
    `modified_time`     datetime default current_timestamp on update current_timestamp comment '更新时间',
    primary key (`id`),
    index idx_biz_id_operator_id_child_biz_id_action_node (`biz_id`, `operator_id`, `child_biz_id`, `action_node`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='业务操作记录表|@斜照|2021-12-07';