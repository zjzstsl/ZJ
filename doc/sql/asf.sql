create table biz_app
(
  id     varchar(32)  not null
    primary key,
  name   varchar(128) null,
  `desc` varchar(256) null
)
  engine = InnoDB;

create table biz_field
(
  id            varchar(32)   not null,
  `order`       int           null,
  model_id      varchar(32)   not null,
  `desc`        varchar(1024) null,
  logical_name  varchar(64)   null,
  physical_name varchar(64)   null,
  type          varchar(16)   null,
  length        int           null,
  default_value varchar(128)  null,
  enums         varchar(128)  null,
  primary_key   char          null,
  primary key (id, model_id)
)
  engine = InnoDB;

create table biz_model
(
  id            varchar(32)  not null,
  module_id     varchar(32)  not null,
  physical_name varchar(128) null,
  logical_name  varchar(128) null,
  `desc`        varchar(256) null,
  primary key (id, module_id)
)
  engine = InnoDB;

create table biz_module
(
  id            varchar(32)  not null
    primary key,
  app_id        varchar(32)  null,
  logic_name    varchar(128) null,
  physical_name varchar(20)  null
)
  engine = InnoDB;

create table er_app
(
  id     varchar(32)   not null
    primary key,
  name   varchar(128)  null,
  `desc` varchar(1024) null
)
  engine = InnoDB;

create table er_category
(
  id     varchar(32) not null
    primary key,
  app_id varchar(32) null,
  name   varchar(32) null
)
  comment 'ER分类'
  engine = InnoDB;

create table er_column
(
  id             varchar(32)   not null
    primary key,
  `order`        int(3)        null,
  table_id       varchar(32)   null,
  physical_name  varchar(32)   null,
  logical_name   varchar(32)   null,
  `desc`         varchar(1024) null,
  type           varchar(32)   null,
  length         int           null,
  default_value  varchar(1024) null,
  auto_increment char          null,
  foreign_key    char          null,
  not_null       char          null,
  primary_key    char          null,
  unique_key     char          null
)
  engine = InnoDB;

create table er_table
(
  id            varchar(32)   not null
    primary key,
  category_id   varchar(32)   null,
  physical_name varchar(32)   null,
  logical_name  varchar(32)   null,
  `desc`        varchar(1024) null
)
  comment 'ER表'
  engine = InnoDB;

