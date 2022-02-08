create table money_transactions (
 id text not null constraint money_transactions_pk primary key,
 user_id text not null references users(id),
 date date not null,
 category varchar not null,
 payee varchar,
 comment varchar,
 outcome_account varchar,
 outcome_amount double precision,
 outcome_currency varchar,
 income_account varchar,
 income_amount double precision,
 income_currency varchar,
 status varchar not null
);
create unique index money_transactions_id_uindex on money_transactions (id);
