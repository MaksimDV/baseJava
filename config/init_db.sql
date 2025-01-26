create table resume
(
uuid CHAR(36) PRIMARY KEY,
full_name TEXT NOT NULL
);

CREATE TABLE contact
(
id INTEGER PRIMARY KEY NOT NULL,
resume_uuid CHAR(36) NOT NULL REFERENCES resume (uuid) ON DELETE CASCADE,
type TEXT NOT NULL,
value TEXT NOT NULL,
CONSTRAINT contact_resume_uuid_fkey FOREIGN KEY (resume_uuid) REFERENCES resume (uuid)
);

CREATE UNIQUE INDEX contact_uuid_type_index ON
contact (resume_uuid, type);