create table users(
  id uuid primary key,
  username varchar(256) not null,
  password_hash varchar(256) not null,
  salt varchar(128) not null
);

create table projects(
  id uuid primary key,
  name varchar(256) not null,
  description varchar(512)
);

create table roles(
  id uuid primary key,
  name varchar(256) not null
);

create table rights(
  id uuid primary key,
  name varchar(256) not null
);

create table roles_rights(
  role_id uuid not null,
  right_id uuid not null,
  right_value boolean not null default false,
  primary key(role_id, right_id)  
);

create table users_projects(
  user_id uuid not null,
  project_id uuid not null,
  role_id uuid not null,
  primary key(user_id, project_id)
);

insert into users(id, username, password_hash, salt) values
  ('89950ac3-0298-4236-bb0b-a686f6385a16'::uuid, 'ivan', 'xyz', 'abc'),
  ('89950ac3-0298-4236-bb0b-a686f6385a17'::uuid, 'john', 'wer', 'qwe')
;

insert into projects(id, name, description) values
  ('89950ac3-0298-4236-bb0b-a686f6385a18'::uuid, 'pA', 'dA'),
  ('89950ac3-0298-4236-bb0b-a686f6385a19'::uuid, 'pB', 'dB')
;

insert into roles(id, name) values
  ('89950ac3-0298-4236-bb0b-a686f6385a20'::uuid, 'owner'),
  ('89950ac3-0298-4236-bb0b-a686f6385a21'::uuid, 'guest')
;

insert into rights(id, name) values
  ('89950ac3-0298-4236-bb0b-a686f6385a22'::uuid, 'view'),
  ('89950ac3-0298-4236-bb0b-a686f6385a23'::uuid, 'edit'),
  ('89950ac3-0298-4236-bb0b-a686f6385a24'::uuid, 'view_history')
;

insert into roles_rights(role_id, right_id, right_value) values 
  ('89950ac3-0298-4236-bb0b-a686f6385a20'::uuid, '89950ac3-0298-4236-bb0b-a686f6385a22'::uuid, true),
  ('89950ac3-0298-4236-bb0b-a686f6385a20'::uuid, '89950ac3-0298-4236-bb0b-a686f6385a23'::uuid, true),
  ('89950ac3-0298-4236-bb0b-a686f6385a20'::uuid, '89950ac3-0298-4236-bb0b-a686f6385a24'::uuid, true),
  ('89950ac3-0298-4236-bb0b-a686f6385a21'::uuid, '89950ac3-0298-4236-bb0b-a686f6385a22'::uuid, true),
  ('89950ac3-0298-4236-bb0b-a686f6385a21'::uuid, '89950ac3-0298-4236-bb0b-a686f6385a23'::uuid, false)
;

insert into users_projects(user_id, project_id, role_id) values
  ('89950ac3-0298-4236-bb0b-a686f6385a16'::uuid, '89950ac3-0298-4236-bb0b-a686f6385a18'::uuid, '89950ac3-0298-4236-bb0b-a686f6385a20'::uuid),
  ('89950ac3-0298-4236-bb0b-a686f6385a16'::uuid, '89950ac3-0298-4236-bb0b-a686f6385a19'::uuid, '89950ac3-0298-4236-bb0b-a686f6385a20'::uuid),
  ('89950ac3-0298-4236-bb0b-a686f6385a17'::uuid, '89950ac3-0298-4236-bb0b-a686f6385a18'::uuid, '89950ac3-0298-4236-bb0b-a686f6385a21'::uuid)
;

-- select * from users;
-- select * from projects;
-- select * from roles;
-- select * from rights;

-- select
--   u.username,
--   p.name as project_name,
--   ro.name as role_name,
--   ri.name as right_name, 
--   rori.right_value
-- from
--   roles_rights rori,
--   roles ro,
--   rights ri,
--   users_projects up,
--   users u,
--   projects p
-- where
--   u.username = 'john'
--   and p.name = 'pA'
  
--   and ro.id = rori.role_id
--   and ri.id = rori.right_id

--   and u.id = up.user_id
--   and p.id = up.project_id
  
--   and ro.id = up.role_id
-- ;

SELECT
  users.username,
  projects.name as project_name,
  roles.name as role_name,
  rights.name as right_name, 
  roles_rights.right_value
FROM
  roles_rights
  INNER JOIN roles ON roles.id = roles_rights.role_id
  INNER JOIN rights ON rights.id = roles_rights.right_id
  INNER JOIN users_projects ON users_projects.role_id = roles.id 
  INNER JOIN users ON users.id = users_projects.user_id
  INNER JOIN projects ON projects.id = users_projects.project_id
WHERE
  users.username = 'john'
  AND projects.name = 'pA'
;

-- SELECT
--   users.username,
--   projects.name as project_name,
--   roles.name as role_name,
--   rights.name as right_name, 
--   roles_rights.right_value
-- FROM
--   roles_rights
--   LEFT JOIN roles ON roles.id = roles_rights.role_id
--   RIGHT JOIN rights ON rights.id = roles_rights.right_id
--   LEFT JOIN users_projects ON users_projects.role_id = roles.id
--   LEFT JOIN users ON users.id = users_projects.user_id AND users.username = 'john'
--   LEFT JOIN projects ON projects.id = users_projects.project_id
-- WHERE
--   projects.name = 'pA' 
-- ;

WITH sub as (SELECT
  users.username,
  projects.name as project_name,
  roles.name as role_name,
  rights.name as right_name, 
  roles_rights.right_value
FROM
  roles_rights
  LEFT JOIN roles ON roles.id = roles_rights.role_id
  RIGHT JOIN rights ON rights.id = roles_rights.right_id
  LEFT JOIN users_projects ON users_projects.role_id = roles.id
  LEFT JOIN users ON users.id = users_projects.user_id AND users.username = 'john'
  LEFT JOIN projects ON projects.id = users_projects.project_id
WHERE
  projects.name = 'pA'),
sub2 as (
select distinct right_name from sub where username is null
except
select distinct right_name from sub where username is not null
)
select right_name, 'false'::boolean as right_value from sub2
;
