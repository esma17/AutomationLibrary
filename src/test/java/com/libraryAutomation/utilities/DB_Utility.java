package com.libraryAutomation.utilities;

import java.sql.*;
import java.util.*;

public class DB_Utility {

        private static Connection conn;
        private static Statement st;
        private static ResultSet rs;
        private static ResultSetMetaData rsmd;

        /**
         * Create Connection by jdbc url and username, password provided
         */
        public static void createConnection() {
            String conString = ConfigurationReader.getProperty("hr.database.url");
            String username = ConfigurationReader.getProperty("hr.database.username");

            String password = ConfigurationReader.getProperty("hr.database.password");

            createConnection(conString, username, password);
        }

        /**
         * Create Connection by jdbc url and username, password provided
         *
         * @param database
         */

        public static void createConnection(String database) {
            String conString = ConfigurationReader.getProperty(String.format("%s.database.url", database));
            String username = ConfigurationReader.getProperty(String.format("%s.database.username", database));
            String password = ConfigurationReader.getProperty(String.format("%s.database.password", database));

            createConnection(conString, username, password);
        }


        /**
         * Create Connection by jdbc url and username, password provided
         *
         * @param url
         * @param username
         * @param password
         */
        public static void createConnection(String url, String username, String password) {
            try {
                conn = DriverManager.getConnection(url, username, password);
                System.out.println("CONNECTION SUCCESSFUL");
            } catch (SQLException e) {
                System.err.println("Connection Failed " + e.getMessage());
            }

        }

        /**
         * Create a method called runQuery that accept a SQL Query
         * and return ResultSet Object
         * setting the value of RESULT_SET_METADATA FOR REUSE
         **/
        public static ResultSet runQuery(String query) {
            try {
                st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                rs = st.executeQuery(query);
                rsmd = rs.getMetaData();
            } catch (SQLException e) {
                System.err.println("Error while getting ResultSet " + e.getMessage());
            }
            return rs;
        }

        /**
         * destroy method to clean up all the resources after being used
         */
        public static void destroy() {

            try {
                if (rs != null) {
                    rs.close();
                }
                if (st != null) {
                    st.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }


        }


        /**
         * Count how many row we have
         *
         * @return the row count of the resultset we got
         */
        public static int getRowCount() {
            int rowCount = 0;
            try {
                rs.last();
                rowCount = rs.getRow();
                rs.beforeFirst();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                resetCursor();
            }
            return rowCount;
        }

        /**
         * Get the column count
         *
         * @return count of column the result set have
         */
        public static int getColumnCNT() {
            int columnCount = 0;
            try {
                columnCount = rsmd.getColumnCount();
            } catch (SQLException e) {
                System.out.println("Error While Counting the columns -->" + e.getMessage());

            } finally {
                resetCursor();
            }
            return columnCount;
        }


        /**
         * a method that return all column names as List<String>
         */
        public static List<String> getColumnNames() {
            List<String> columnNamesList = new LinkedList<>();

            try {
                for (int colNum = 1; colNum <= getColumnCNT(); colNum++) {
                    String colName = rsmd.getColumnName(colNum);
                    columnNamesList.add(colName);


                }

            } catch (SQLException e) {
                System.err.println("Error while getting column names -->" + e.getMessage());
            } finally {
                resetCursor();
            }
            return columnNamesList;
        }

        /**
         * Create a method that return all row data as a List<String>
         *
         * @param rowNum Row number you want to get the data
         * @return the row data as List object
         */
        public static List<String> getRowDataAsList(int rowNum) {
            List<String> rowDataList = new LinkedList<>();

            try {

                rs.absolute(rowNum);
                for (int colNum = 1; colNum <= getColumnCNT(); colNum++) {
                    String cellValue = rs.getString(colNum);
                    rowDataList.add(cellValue);

                }


            } catch (SQLException e) {
                System.err.println("ERROR WHILE GETROWDATA_ASLIST " + e.getMessage());
            } finally {
                resetCursor();
            }
            return rowDataList;
        }

        /**
         * Create a method to return the cell value at certain row certain column
         *
         * @param rowNum row number
         * @param colNum column number
         * @return Cell value as String
         */

        public static String getColumnDataAtRow(int rowNum, int colNum) {
            String result = "";


            try {

                rs.absolute(rowNum);
                result = rs.getString(colNum);

            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                resetCursor();
            }
            return result;
        }

        /**
         * Create a method to return the cell value at certain row certain column
         *
         * @param rowNum  row number
         * @param colName column name
         * @return Cell value as String at specified row numeber and column number
         */
        public static String getCellValue(int rowNum, String colName) {
            String result = "";

            try {

                rs.absolute(rowNum);
                result = rs.getString(colName);

            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                resetCursor();
            }

            return result;
        }

        public static String getCellValue(int rowNum, int colNumber) {
            String result = "";

            try {

                rs.absolute(rowNum);
                result = rs.getString(colNumber);

            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                resetCursor();
            }

            return result;
        }

        /**
         * return value of all cells in that column using column name
         *
         * @param columnNum the column number you want to get the list out of
         * @return value of all cells in that column as a List<String>
         */

        public static List<String> getColumnDataAsList(int columnNum) {
            List<String> dataList = new LinkedList<>();
            try {
                while (rs.next()) {
                    String cellValue = rs.getString(columnNum);
                    dataList.add(cellValue);
                }

            } catch (SQLException e) {

                System.err.println("ERROR WHILE getColumnDataAsList METHOD -->" + e.getMessage());
            } finally {
                resetCursor();
            }

            return dataList;
        }

        /**
         * return value of all cells in that column using column name
         *
         * @param columnName the column name you want to get the list out of
         * @return value of all cells in that column as a List<String>
         */
        public static List<String> getColumnDataAsList(String columnName) {
            List<String> dataList = new LinkedList<>();
            resetCursor();
            try {
                while (rs.next()) {
                    String cellValue = rs.getString(columnName);
                    dataList.add(cellValue);
                }

            } catch (SQLException e) {

                System.err.println("ERROR WHILE getColumnDataAsList METHOD -->" + e.getMessage());
            } finally {
                resetCursor();
            }
            return dataList;
        }

        /**
         * display all data from the ResultSet Object
         */
        public static void displayAllData() {
            resetCursor();
            int columnCount = getColumnCNT();
            try {
                while (rs.next()) {

                    for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                        System.out.printf("%-35s", rs.getString(columnIndex));

                    }
                    System.out.println();
                }
            } catch (SQLException e) {
                System.out.println("Error Ocurred in displayAllData--> " + e.getMessage());
            } finally {
                resetCursor();
            }

        }

        /**
         * Save entire row data as Map<String,String>
         *
         * @param rowNum
         * @return Map object that contins key value pair
         * key:     column name
         * value:   cell value
         */

        public static Map<String, String> getRowMap(int rowNum) {
            Map<String, String> rowMap = new LinkedHashMap<>();
            int count=getColumnCNT();
            try {
                rs.absolute(rowNum);
                for (int columnIndex = 1; columnIndex <= count; columnIndex++) {
                    String columnName = rsmd.getColumnName(columnIndex);
                    String columnCellValue = rs.getString(columnName);
                    rowMap.put(columnName, columnCellValue);
                }
            } catch (SQLException e) {
                System.out.println("Error occured in getRowMap-->" + e.getMessage());
            } finally {
                resetCursor();
            }
            return rowMap;
        }

        /**
         * we know how to store one row as map object
         * Now store All rows as List of Map object
         *
         * @return List of Lost of Map object that contain each row data as Map<String,String>
         */

        public static List<Map<String, String>> getAllRowAsListOfMap() {
            List<Map<String, String>> allRowLstOfMap = new ArrayList<>();
            int rowCount = getRowCount();
            //move from first row till last row
            //get each row as map object and add it to the list
            for (int rowIndex = 1; rowIndex <= rowCount; rowIndex++) {
                Map<String, String> rowMap = getRowMap(rowIndex);
                allRowLstOfMap.add(rowMap);
            }
            resetCursor();

            return allRowLstOfMap;
        }

        /**
         *
         * Get First Cell Value at First row First Column
         */
        public static String getFirstCellData(){
            return getCellValue(1,1);

        }
        /**
         * reset the cursor to before first location
         */
        private static void resetCursor() {
            try {
                rs.beforeFirst();
            } catch (SQLException e) {
                System.out.println("ERROR OCCURED IN resetCursor-->" + e.getMessage());
            }
        }


    }


