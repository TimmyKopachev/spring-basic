use master
go

if exists (select * from sys.objects where object_id = object_id('xp_sqljdbc_xa_init') and OBJECTPROPERTY(object_id, N'IsExtendedProc') = 1) exec sp_dropextendedproc 'xp_sqljdbc_xa_init'
if exists (select * from sys.objects where object_id = object_id('xp_sqljdbc_xa_start') and OBJECTPROPERTY(object_id, N'IsExtendedProc') = 1) exec sp_dropextendedproc 'xp_sqljdbc_xa_start'
if exists (select * from sys.objects where object_id = object_id('xp_sqljdbc_xa_end') and OBJECTPROPERTY(object_id, N'IsExtendedProc') = 1) exec sp_dropextendedproc 'xp_sqljdbc_xa_end'
if exists (select * from sys.objects where object_id = object_id('xp_sqljdbc_xa_prepare') and OBJECTPROPERTY(object_id, N'IsExtendedProc') = 1) exec sp_dropextendedproc 'xp_sqljdbc_xa_prepare'
if exists (select * from sys.objects where object_id = object_id('xp_sqljdbc_xa_commit') and OBJECTPROPERTY(object_id, N'IsExtendedProc') = 1) exec sp_dropextendedproc 'xp_sqljdbc_xa_commit'
if exists (select * from sys.objects where object_id = object_id('xp_sqljdbc_xa_rollback') and OBJECTPROPERTY(object_id, N'IsExtendedProc') = 1) exec sp_dropextendedproc 'xp_sqljdbc_xa_rollback'
if exists (select * from sys.objects where object_id = object_id('xp_sqljdbc_xa_forget') and OBJECTPROPERTY(object_id, N'IsExtendedProc') = 1) exec sp_dropextendedproc 'xp_sqljdbc_xa_forget'
if exists (select * from sys.objects where object_id = object_id('xp_sqljdbc_xa_recover') and OBJECTPROPERTY(object_id, N'IsExtendedProc') = 1) exec sp_dropextendedproc 'xp_sqljdbc_xa_recover'
if exists (select * from sys.objects where object_id = object_id('xp_sqljdbc_xa_rollback_ex') and OBJECTPROPERTY(object_id, N'IsExtendedProc') = 1) exec sp_dropextendedproc 'xp_sqljdbc_xa_rollback_ex'
if exists (select * from sys.objects where object_id = object_id('xp_sqljdbc_xa_forget_ex') and OBJECTPROPERTY(object_id, N'IsExtendedProc') = 1) exec sp_dropextendedproc 'xp_sqljdbc_xa_forget_ex'
if exists (select * from sys.objects where object_id = object_id('xp_sqljdbc_xa_prepare_ex') and OBJECTPROPERTY(object_id, N'IsExtendedProc') = 1) exec sp_dropextendedproc 'xp_sqljdbc_xa_prepare_ex'
if exists (select * from sys.objects where object_id = object_id('xp_sqljdbc_xa_init_ex') and OBJECTPROPERTY(object_id, N'IsExtendedProc') = 1) exec sp_dropextendedproc 'xp_sqljdbc_xa_init_ex'
go

exec sp_addextendedproc 'xp_sqljdbc_xa_init', 'sqljdbc_xa.dll'
exec sp_addextendedproc 'xp_sqljdbc_xa_start', 'sqljdbc_xa.dll'
exec sp_addextendedproc 'xp_sqljdbc_xa_end', 'sqljdbc_xa.dll'
exec sp_addextendedproc 'xp_sqljdbc_xa_prepare', 'sqljdbc_xa.dll'
exec sp_addextendedproc 'xp_sqljdbc_xa_commit', 'sqljdbc_xa.dll'
exec sp_addextendedproc 'xp_sqljdbc_xa_rollback', 'sqljdbc_xa.dll'
exec sp_addextendedproc 'xp_sqljdbc_xa_forget', 'sqljdbc_xa.dll'
exec sp_addextendedproc 'xp_sqljdbc_xa_recover', 'sqljdbc_xa.dll'
exec sp_addextendedproc 'xp_sqljdbc_xa_rollback_ex', 'sqljdbc_xa.dll'
exec sp_addextendedproc 'xp_sqljdbc_xa_forget_ex', 'sqljdbc_xa.dll'
exec sp_addextendedproc 'xp_sqljdbc_xa_prepare_ex', 'sqljdbc_xa.dll'
exec sp_addextendedproc 'xp_sqljdbc_xa_init_ex', 'sqljdbc_xa.dll'
go


if exists (select * from sys.schemas where name = 'SqlJDBCXAUser' )
drop schema [SqlJDBCXAUser];

if exists (select * from sys.database_principals where name = 'SqlJDBCXAUser' and type='R')
drop role [SqlJDBCXAUser];

create role [SqlJDBCXAUser]
go


grant execute on xp_sqljdbc_xa_init to [SqlJDBCXAUser]
grant execute on xp_sqljdbc_xa_start to [SqlJDBCXAUser]
grant execute on xp_sqljdbc_xa_end to [SqlJDBCXAUser]
grant execute on xp_sqljdbc_xa_prepare to [SqlJDBCXAUser]
grant execute on xp_sqljdbc_xa_commit to [SqlJDBCXAUser]
grant execute on xp_sqljdbc_xa_rollback to [SqlJDBCXAUser]
grant execute on xp_sqljdbc_xa_recover to [SqlJDBCXAUser]
grant execute on xp_sqljdbc_xa_forget to [SqlJDBCXAUser]
grant execute on xp_sqljdbc_xa_rollback_ex to [SqlJDBCXAUser]
grant execute on xp_sqljdbc_xa_forget_ex to [SqlJDBCXAUser]
grant execute on xp_sqljdbc_xa_prepare_ex to [SqlJDBCXAUser]
grant execute on xp_sqljdbc_xa_init_ex to [SqlJDBCXAUser]
go

exec sp_addrolemember [SqlJDBCXAUser], 'iamuser';

print ''
print 'SQLJDBC XA DLL installation script complete.'
print 'Check for any error messages generated above.'
