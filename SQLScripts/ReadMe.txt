Within the database management tools select new query
copy and paste CreateDatabase then execute
copy and paste CreateAdmin then execute
copy and paste CreateTables then execute
copy and paste ViewInfo then execute for testing whether the database is storing information correctly

There are three functions in the main(saveData,loadData,flushData) with this snippet of code the log in username and password to the database must match so please change "Admin", and "password" to what you have established.
conn = DriverManager.getConnection("jdbc:sqlserver://localhost:3306;database = FORUM_PROJECT;","Amin","password");
