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
