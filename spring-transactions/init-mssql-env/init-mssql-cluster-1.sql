PRINT 'Initializing database...';
USE master;
GO

CREATE DATABASE prePrDb;
GO

CREATE LOGIN iamuser WITH PASSWORD = 'passw0rd@Dima';
CREATE USER iamuser FOR LOGIN iamuser;
ALTER ROLE db_owner ADD MEMBER iamuser;

PRINT 'Initialization complete.';

