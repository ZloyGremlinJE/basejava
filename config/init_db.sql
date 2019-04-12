CREATE TABLE resume
(
  uuid      CHAR(36) PRIMARY KEY NOT NULL,
  full_name TEXT                 NOT NULL
);

CREATE TABLE contact
(
  id          SERIAL,
  resume_uuid CHAR(36) NOT NULL REFERENCES resume (uuid) ON DELETE CASCADE,
  type        TEXT     NOT NULL,
  value       TEXT     NOT NULL
);
CREATE UNIQUE INDEX contact_uuid_type_index
  ON contact (resume_uuid, type);




create database phone_book
  with owner postgres;

create table if not exists phone_book_root
(
  uuid char(36) not null
    constraint phone_book_root_pk
      primary key,
  full_name text,
  phone_number text,
  department text
);

alter table phone_book_root owner to postgres;




