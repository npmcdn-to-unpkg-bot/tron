DROP TABLE supported_versions IF EXISTS;

CREATE TABLE supported_versions (
	 id        INTEGER IDENTITY PRIMARY KEY,
	 ccb_ver   VARCHAR(30),
	 desc      VARCHAR(60)
);
