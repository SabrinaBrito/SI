# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table task (
  id                        bigint not null,
  descricao                 varchar(255),
  projeto_associado         varchar(255),
  prioridade                integer,
  tarefa_realizada          boolean,
  constraint pk_task primary key (id))
;

create sequence task_seq;




# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists task;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists task_seq;

