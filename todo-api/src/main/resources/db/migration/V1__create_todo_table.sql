
create table todo(
    id uuid not null,
    title varchar(100),
    description varchar(255),
    createdAt timestamp,
    dueDate timestamp,
    primary key(id)
);