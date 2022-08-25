

CREATE TABLE employees (
	emp_id serial NOT NULL,
	emp_first_name varchar(100) NULL,
	emp_last_name varchar(100) NULL,
	emp_email varchar(200) NULL,
	emp_password varchar(200) NULL,
	emp_role varchar(100) NULL,
	CONSTRAINT emp_email_unique UNIQUE (emp_email),
	CONSTRAINT employees_pk PRIMARY KEY (emp_id)
);

CREATE TABLE reimbursements (
	reimbursement_id serial4 NOT NULL,
	emp_id int4 NOT NULL,
	reason varchar(200) NOT NULL,
	amount numeric(8, 2) NOT NULL,
	description varchar(300) NULL,
	reimbursement_status varchar(200) NULL,
	CONSTRAINT check_amount CHECK ((amount > (0)::numeric)),
	CONSTRAINT reimbursements_pkey PRIMARY KEY (reimbursement_id),
	CONSTRAINT exp_emp_fk FOREIGN KEY (emp_id) REFERENCES employees(emp_id)
);
