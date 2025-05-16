~~~
#init db data

/opt/mssql-tools18/bin/sqlcmd -S first-db-mssql -U SA -P 'passw0rd@Dima' -C -N -i /sql-xa/xa_install.sql;
/opt/mssql-tools18/bin/sqlcmd -S second-db-mssql -U SA -P 'passw0rd@Dima' -C -N -i /sql-xa/xa_install.sql;

~~~