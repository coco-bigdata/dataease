[INFO] --- frontend-maven-plugin:1.9.1:install-node-and-npm (install node and npm) @ frontend ---
[INFO] Installing node version v15.12.0
[INFO] Downloading https://nodejs.org/dist/v15.12.0/node-v15.12.0-darwin-x64.tar.gz to /Users/git/.m2/repository/com/github/eirslett/node/15.12.0/node-15.12.0-darwin-x64.tar.gz
[INFO] No proxies configured
[INFO] No proxy was configured, downloading directly
[INFO] Unpacking /Users/git/.m2/repository/com/github/eirslett/node/15.12.0/node-15.12.0-darwin-x64.tar.gz into /Users/git/dataease/frontend/node/tmp
[INFO] Copying node binary from /Users/git/dataease/frontend/node/tmp/node-v15.12.0-darwin-x64/bin/node to /Users/git/dataease/frontend/node/node
[INFO] Installed node locally.
[INFO] Installing npm version 7.6.3
[INFO] Downloading https://registry.npmjs.org/npm/-/npm-7.6.3.tgz to /Users/git/.m2/repository/com/github/eirslett/npm/7.6.3/npm-7.6.3.tar.gz
[INFO] No proxies configured
[INFO] No proxy was configured, downloading directly
[INFO] Unpacking /Users/git/.m2/repository/com/github/eirslett/npm/7.6.3/npm-7.6.3.tar.gz into /Users/git/dataease/frontend/node/node_modules
[INFO] Installed npm locally.
[INFO]
[INFO] --- frontend-maven-plugin:1.9.1:npm (npm install) @ frontend ---
[INFO] Running 'npm install --force' in /Users/git/dataease/frontend
[INFO] npm WARN using --force Recommended protections disabled.

[INFO] --- frontend-maven-plugin:1.9.1:install-node-and-npm (install node and npm) @ frontend ---
[INFO] Node v15.12.0 is already installed.
[INFO] NPM 7.6.3 is already installed.
[INFO]
[INFO] --- frontend-maven-plugin:1.9.1:npm (npm install) @ frontend ---
[INFO] Running 'npm install' in /Users/git/dataease/frontend

ls ~/.m2/repository/com/github/eirslett

[INFO] ------------------------------------------------------------------------
[INFO] BUILD FAILURE
[INFO] ------------------------------------------------------------------------
[INFO] Total time: 7.505 s
[INFO] Finished at: 2021-07-05T17:08:40+08:00
[INFO] Final Memory: 30M/286M
[INFO] ------------------------------------------------------------------------
[ERROR] Failed to execute goal on project backend: Could not resolve dependencies for project io.dataease:backend:jar:1.0.0: The following artifacts could not be resolved: pentaho-kettle:kettle-core:jar:8.3.0.18-1084, pentaho-kettle:kettle-engine:jar:8.3.0.18-1084, pentaho:metastore:jar:8.3.0.18-1084, org.pentaho.di.plugins:pdi-engine-configuration-impl:jar:8.3.0.7-683, io.dataease:dataease-plugin-xpack:jar:1.0: Failure to find pentaho-kettle:kettle-core:jar:8.3.0.18-1084 in https://maven.aliyun.com/nexus/content/groups/public/ was cached in the local repository, resolution will not be reattempted until the update interval of alimaven has elapsed or updates are forced -> [Help 1]
[ERROR]
[ERROR] To see the full stack trace of the errors, re-run Maven with the -e switch.
[ERROR] Re-run Maven using the -X switch to enable full debug logging.
[ERROR]
[ERROR] For more information about the errors and possible solutions, please read the following articles:
[ERROR] [Help 1] http://cwiki.apache.org/confluence/display/MAVEN/DependencyResolutionException

[INFO] ------------------------------------------------------------------------
[INFO] BUILD FAILURE
[INFO] ------------------------------------------------------------------------
[INFO] Total time: 04:05 min
[INFO] Finished at: 2021-07-05T17:14:50+08:00
[INFO] Final Memory: 36M/540M
[INFO] ------------------------------------------------------------------------
[ERROR] Failed to execute goal on project backend: Could not resolve dependencies for project io.dataease:backend:jar:1.0.0: Failure to find io.dataease:dataease-plugin-xpack:jar:1.0 in https://maven.aliyun.com/nexus/content/groups/public/ was cached in the local repository, resolution will not be reattempted until the update interval of alimaven has elapsed or updates are forced -> [Help 1]
[ERROR]
[ERROR] To see the full stack trace of the errors, re-run Maven with the -e switch.
[ERROR] Re-run Maven using the -X switch to enable full debug logging.
[ERROR]
[ERROR] For more information about the errors and possible solutions, please read the following articles:
[ERROR] [Help 1] http://cwiki.apache.org/confluence/display/MAVEN/DependencyResolutionException

docker-compose network


### Error querying database.  Cause: java.sql.SQLException: Illegal mix of collations (utf8mb4_general_ci,IMPLICIT) and (utf8mb4_unicode_ci,IMPLICIT) for operation 'find_in_set'

Caused by: java.sql.SQLException: Illegal mix of collations (utf8mb4_general_ci,IMPLICIT) and (utf8mb4_unicode_ci,IMPLICIT) for operation 'find_in_set'

Caused by: org.apache.ibatis.exceptions.PersistenceException:
dataease          | ### Error querying database.  Cause: java.sql.SQLException: Illegal mix of collations (utf8mb4_general_ci,IMPLICIT) and (utf8mb4_unicode_ci,IMPLICIT) for operation 'find_in_set'
dataease          | ### The error may exist in io/dataease/base/mapper/ext/AuthMapper.xml
dataease          | ### The error may involve defaultParameterMap
dataease          | ### The error occurred while setting parameters
dataease          | ### SQL: SELECT    sys_menu.permission   FROM    ( SELECT GET_V_AUTH_MODEL_ID_P_USE ( ?, 'menu' ) cids ) t,    sys_menu   WHERE    FIND_IN_SET( sys_menu.menu_id, cids ) UNION ALL   SELECT    plugin_sys_menu.permission   FROM    ( SELECT GET_V_AUTH_MODEL_ID_P_USE (  ?, 'menu' ) cids ) t,    plugin_sys_menu   WHERE    FIND_IN_SET( plugin_sys_menu.menu_id, cids )
dataease          | ### Cause: java.sql.SQLException: Illegal mix of collations (utf8mb4_general_ci,IMPLICIT) and (utf8mb4_unicode_ci,IMPLICIT) for operation 'find_in_set'

[INFO] ------------------------------------------------------------------------
[INFO] BUILD FAILURE
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  01:07 min
[INFO] Finished at: 2021-07-06T16:37:04+08:00
[INFO] ------------------------------------------------------------------------
[ERROR] Failed to execute goal com.github.eirslett:frontend-maven-plugin:1.9.1:npm (npm run build) on project frontend: Failed to run task: 'npm run build'
failed. org.apache.commons.exec.ExecuteException: Process exited with an error: 137 (Exit value: 137) -> [Help 1]
[ERROR]
[ERROR] To see the full stack trace of the errors, re-run Maven with the -e switch.
[ERROR] Re-run Maven using the -X switch to enable full debug logging.
[ERROR]
[ERROR] For more information about the errors and possible solutions, please read the following articles:
[ERROR] [Help 1] http://cwiki.apache.org/confluence/display/MAVEN/MojoFailureException
[ERROR]
[ERROR] After correcting the problems, you can resume the build with the command
[ERROR]   mvn <args> -rf :frontend

java.io.IOException: the self host 172.19.0.198 does not equal to the host in ROLE file 172.19.0.4. You need to set 'priority_networks' config in fe.conf to match the host 172.19.0.4
at org.apache.doris.catalog.Catalog.getClusterIdAndRole(Catalog.java:854)
at org.apache.doris.catalog.Catalog.initialize(Catalog.java:757)
at org.apache.doris.PaloFe.start(PaloFe.java:108)
at org.apache.doris.PaloFe.main(PaloFe.java:60)

1: lo: <LOOPBACK,UP,LOWER_UP> mtu 65536 qdisc noqueue state UNKNOWN group default qlen 1000
link/loopback 00:00:00:00:00:00 brd 00:00:00:00:00:00
inet 127.0.0.1/8 scope host lo
valid_lft forever preferred_lft forever
264: eth0@if265: <BROADCAST,MULTICAST,UP,LOWER_UP> mtu 1500 qdisc noqueue state UP group default
link/ether 02:42:ac:13:00:c6 brd ff:ff:ff:ff:ff:ff link-netnsid 0
inet 172.19.0.198/16 brd 172.19.255.255 scope global eth0
valid_lft forever preferred_lft forever


show tables;
+----------------------------------+
| Tables_in_dataease               |
+----------------------------------+
| chart_group                      |
| chart_view                       |
| dataease_version                 |
| dataset_group                    |
| dataset_table                    |
| dataset_table_field              |
| dataset_table_incremental_config |
| dataset_table_task               |
| dataset_table_task_log           |
| dataset_table_union              |
| datasource                       |
| demo_domestic_epidemic           |
| demo_new_trend_of_diagnosis      |
| demo_recent_local_cases          |
| demo_vaccination                 |
| file_content                     |
| file_metadata                    |
| license                          |
| my_plugin                        |
| panel_design                     |
| panel_group                      |
| panel_link                       |
| panel_share                      |
| panel_store                      |
| panel_subject                    |
| panel_template                   |
| panel_view                       |
| plugin_sys_menu                  |
| qrtz_blob_triggers               |
| qrtz_calendars                   |
| qrtz_cron_triggers               |
| qrtz_fired_triggers              |
| qrtz_job_details                 |
| qrtz_locks                       |
| qrtz_paused_trigger_grps         |
| qrtz_scheduler_state             |
| qrtz_simple_triggers             |
| qrtz_simprop_triggers            |
| qrtz_triggers                    |
| schedule                         |
| sys_auth                         |
| sys_auth_detail                  |
| sys_dept                         |
| sys_menu                         |
| sys_role                         |
| sys_roles_menus                  |
| sys_user                         |
| sys_users_roles                  |
| system_parameter                 |
| user_role                        |
| v_auth_model                     |
| v_auth_privilege                 |
+----------------------------------+


io.dataease.exception.DataEaseException: io.dataease.exception.DataEaseException: java.sql.SQLException: Access denied for user 'root' (using password: YES)
at io.dataease.exception.DataEaseException.throwException(DataEaseException.java:22)
at io.dataease.datasource.provider.JdbcProvider.exec(JdbcProvider.java:56)
at io.dataease.service.dataset.ExtractDataService.createDorisTable(ExtractDataService.java:362)
at io.dataease.service.dataset.ExtractDataService.extractData(ExtractDataService.java:194)
at io.dataease.service.dataset.DataSetTableService.lambda$save$0(DataSetTableService.java:115)
at java.util.concurrent.Executors$RunnableAdapter.call(Executors.java:511)
at java.util.concurrent.FutureTask.run(FutureTask.java:266)
at java.util.concurrent.ScheduledThreadPoolExecutor$ScheduledFutureTask.access$201(ScheduledThreadPoolExecutor.java:180)
at java.util.concurrent.ScheduledThreadPoolExecutor$ScheduledFutureTask.run(ScheduledThreadPoolExecutor.java:293)
at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1149)
at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624)
at java.lang.Thread.run(Thread.java:748)
Caused by: io.dataease.exception.DataEaseException: java.sql.SQLException: Access denied for user 'root' (using password: YES)
at io.dataease.exception.DataEaseException.throwException(DataEaseException.java:22)
at io.dataease.datasource.provider.JdbcProvider.checkStatus(JdbcProvider.java:252)
at io.dataease.datasource.provider.JdbcProvider.handleDatasource(JdbcProvider.java:291)
at io.dataease.datasource.provider.JdbcProvider.getConnectionFromPool(JdbcProvider.java:279)
at io.dataease.datasource.provider.JdbcProvider.exec(JdbcProvider.java:49)
... 10 common frames omitted
Caused by: java.sql.SQLException: Access denied for user 'root' (using password: YES)
at com.mysql.cj.jdbc.exceptions.SQLError.createSQLException(SQLError.java:129)
at com.mysql.cj.jdbc.exceptions.SQLExceptionsMapping.translateException(SQLExceptionsMapping.java:122)
at com.mysql.cj.jdbc.ConnectionImpl.createNewIO(ConnectionImpl.java:833)
at com.mysql.cj.jdbc.ConnectionImpl.<init>(ConnectionImpl.java:453)
at com.mysql.cj.jdbc.ConnectionImpl.getInstance(ConnectionImpl.java:246)
at com.mysql.cj.jdbc.NonRegisteringDriver.connect(NonRegisteringDriver.java:198)
at java.sql.DriverManager.getConnection(DriverManager.java:664)
at java.sql.DriverManager.getConnection(DriverManager.java:208)
at io.dataease.datasource.provider.JdbcProvider.getConnection(JdbcProvider.java:376)
at io.dataease.datasource.provider.JdbcProvider.checkStatus(JdbcProvider.java:246)
... 13 common frames omitted


SHOW PROC '/backends'\G
*************************** 1. row ***************************
BackendId: 11001
Cluster: default_cluster
IP: 172.19.0.198
HostName: 5ac4ecc37896
HeartbeatPort: 8030
BePort: -1
HttpPort: -1
BrpcPort: -1
LastStartTime: NULL
LastHeartbeat: NULL
Alive: false
SystemDecommissioned: false
ClusterDecommissioned: false
TabletNum: 0
DataUsedCapacity: .000
AvailCapacity: 1.000 B
TotalCapacity: .000
UsedPct: 0.00 %
MaxDiskUsedPct: 0.00 %
ErrMsg: got exception
Version:
Status: {"lastSuccessReportTabletsTime":"N/A","lastStreamLoadTime":-1}
1 row in set (0.01 sec)

