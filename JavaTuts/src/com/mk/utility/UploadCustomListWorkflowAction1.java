package com.mk.utility;


/**
 * @author arramanathan
 * @date 18 August 2014
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

public class UploadCustomListWorkflowAction1 {

	private static final String FILE_PASSWORD="filePassword";
	private static final String FILE_NAME="fileName";
	private static final String LIST_ID="listID";
	
	private ListDefinition listDefinition = null;
	private Map<String,ListMetaData> listMetaDataMap = null;
	private Map<String,PartyVO> partyDataMap = null;
	List<Long> listOfHistoricalPartyIDs = new ArrayList<Long>(100);

	private static final long BATCH_SIZE = 5;
	
	private String queryToGetListDefinition = " SELECT list_name,			" +
											  " 	   language_code,		" +
											  " 	   country_code			" +
											  " FROM   WLM_CUSTOM_LIST   	" +
											  " WHERE  id = ?				";
	
	private String queryToCheckListAlreadyExists = " SELECT id 				" +
												   " FROM   wlm_list 		" +
												   " WHERE  list_name=?		" +
												   " AND 	is_active='Y'	";
	
	private String queryToGetMaxIDfromWLMList = " SELECT wlm_list_seq.nextval " +
												" FROM 	 dual				  ";
	
	private String queryToGetMaxIDfromWLMParty = " SELECT wlm_party_seq.nextval " +
												 " FROM 	 dual				";
	
	private String queryToUpdateWLMList = " INSERT INTO wlm_list (id,			" +
										  "						 list_name,		" +
										  "						 is_active,		" +
										  "						 time_created,	" +
										  "						 time_updated) 	" +
										  " VALUES(?,?,?,?,?)					";

	private String queryToGetListMetadata = " SELECT header,					" +
											" 		 mapped_column,				" +
											"		 data_type,					" +
											"		 data_format,				" +
											"		 is_indexed 				" +
											" FROM 	 wlm_custom_list_meta		" +
											" WHERE  wlm_custom_list_id = ?		";

	private String queryToLoadPartyData = " SELECT id,							" +
										  " 	   entity_id,					" +
										  "		   update_category,				" +
										  "		   fingerprint_data 			" +
										  " FROM   wlm_party 					" +
										  " WHERE  entity_name=?				" +
										  " AND	   is_active='Y'				";

	private String queryToInsertIntoNativeName = " INSERT INTO wlm_party_native_name(						" +
												 " 									id,						" +
												 " 									wlm_party_id,			" +
												 " 									native_name,			" +
												 " 									language_code,			" +
												 " 									country_code,			" +
												 " 									is_active,				" +
												 " 									time_created,			" +
												 " 									time_updated) 			" +
												 " VALUES (wlm_party_native_name_seq.nextval,?,?,?,?,?,?,?)	";

	private String queryToInsertIntoListParty =  " INSERT INTO wlm_list_party(								" +
												 " 							 id,							" +
												 "							 wlm_list_id,					" +
												 "							 wlm_party_id,					" +
												 "							 time_created,					" +
												 "							 time_updated,					" +
												 "							 is_active) 					" +
												 " VALUES (wlm_list_party_seq.nextval,?,?,?,?,?)			";

	
	private String queryToMarkHistoricalInParty = " UPDATE wlm_party 		" +
												  " SET is_active='N',		" +
												  " 	time_updated=? 		" +
												  " WHERE id=? 				";

	private String queryToMarkHistoricalInListParty = " UPDATE wlm_list_party 	" +
													  " SET is_active='N',		" +
													  " 	time_updated=? 		" +
													  " WHERE wlm_party_id=?	";

	private String queryToMarkHistoricalInNativeName = " UPDATE wlm_party_native_name 	" +
													   " SET is_active='N',				" +
													   " 	 time_updated=? 			" +
													   " WHERE wlm_party_id=?			";

	public static void main(String[] args) {

		String filePassword="paypalbasic";
		String listId = "1";

		UploadCustomListWorkflowAction1 action = new UploadCustomListWorkflowAction1();
		
		action.populateListDefinition(Long.parseLong(listId));

		action.insertIntoWLMList();

		action.loadListMetaData();
		
		action.loadPartyData();

//		File uploadedFile = new File("C:\\Users\\arramanathan\\Desktop\\temp.xls");
		File uploadedFile = new File("C:\\Kumaran\\Compliance\\3.Analysis\\nls custom list\\test1.xls");

		action.fileParserAndLoader(uploadedFile, filePassword);
		
		action.markAsHistoricalData();
	}

	private void populateListDefinition(long listId) {

		Connection connection = null;
		ResultSet rs = null;
		PreparedStatement preparedStatement = null;

		listDefinition = new ListDefinition();

		try {
			connection = getCurrentHibernateConnection();
			preparedStatement = connection.prepareStatement(queryToGetListDefinition);
			preparedStatement.setLong(1, listId);
			rs = preparedStatement.executeQuery();

			if (rs.next()) {
				listDefinition.setListName(rs.getString(1));
				listDefinition.setLanguageCode(rs.getString(2));
				listDefinition.setCountryCode(rs.getString(3));
				listDefinition.setCustomListID(listId);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null) {
				closeResultSet(rs);
			}
			if (preparedStatement != null) {
				closeStatement(preparedStatement);
			}
			if (connection != null) {
				closeConnection(connection);
			}
		}
	}
	
	private void insertIntoWLMList() {
		Connection connection = null;
		ResultSet rs = null;
		ResultSet rsToFindMaxID = null;
		PreparedStatement preparedStatement = null;
		PreparedStatement preparedStatementToFindMaxID = null;
		PreparedStatement preparedStatementToUpdate = null;

		long dateTime = (new Date().getTime())/1000;

		try {
			connection = getCurrentHibernateConnection();

			preparedStatement = connection.prepareStatement(queryToCheckListAlreadyExists);
			preparedStatement.setString(1, listDefinition.getListName());
			rs = preparedStatement.executeQuery();

			if (rs.next()) {
				listDefinition.setWlmListID(rs.getLong(1));

			} else {
				long maxID = 0;
				preparedStatementToFindMaxID = connection.prepareStatement(queryToGetMaxIDfromWLMList);
				rsToFindMaxID = preparedStatementToFindMaxID.executeQuery();
				if (rsToFindMaxID.next()) {
					maxID = rsToFindMaxID.getLong(1);
				}

				preparedStatementToUpdate = connection.prepareStatement(queryToUpdateWLMList);
				preparedStatementToUpdate.setLong(1, maxID);
				preparedStatementToUpdate.setString(2, listDefinition.getListName());
				preparedStatementToUpdate.setString(3, "Y");
				preparedStatementToUpdate.setLong(4, dateTime);
				preparedStatementToUpdate.setLong(5, dateTime);
				preparedStatementToUpdate.executeUpdate();

				listDefinition.setWlmListID(maxID);

			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null) {
				closeResultSet(rs);
			}
			if (rsToFindMaxID != null) {
				closeResultSet(rsToFindMaxID);
			}
			if (preparedStatement != null) {
				closeStatement(preparedStatement);
			}
			if (preparedStatementToFindMaxID != null) {
				closeStatement(preparedStatementToFindMaxID);
			}
			if (preparedStatementToUpdate != null) {
				closeStatement(preparedStatementToUpdate);
			}
			if (connection != null) {
				closeConnection(connection);
			}
		}
	}

	private void loadListMetaData() {
		Connection connection = null;
		ResultSet rs = null;
		PreparedStatement preparedStatement = null;

		listMetaDataMap = new HashMap<String,ListMetaData>();

		try {
			connection = getCurrentHibernateConnection();
			preparedStatement = connection.prepareStatement(queryToGetListMetadata);
			preparedStatement.setString(1, Long.toString(listDefinition.getCustomListID()));
			rs = preparedStatement.executeQuery();

			while (rs.next()) {
				ListMetaData metadata = new ListMetaData();
				metadata.setHeader(rs.getString(1));
				metadata.setMappedColumn(rs.getString(2));
				metadata.setDataType(rs.getString(3));
				metadata.setDataFormat(rs.getString(4));
				metadata.setIsIndexed(rs.getString(5));

				listMetaDataMap.put(metadata.getHeader(), metadata);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null) {
				closeResultSet(rs);
			}
			if (preparedStatement != null) {
				closeStatement(preparedStatement);
			}
			if (connection != null) {
				closeConnection(connection);
			}
		}
	}

	private void fileParserAndLoader(File file, String password) {

		StringBuffer queryToLoadDataToParty = new StringBuffer("INSERT INTO wlm_party (");
		StringBuffer hashBuffer = new StringBuffer();

		LinkedList<String> listOfDataTypes 		  = new LinkedList<String>();
		LinkedList<String> listOfColumns		  = new LinkedList<String>();
		LinkedList<String> listOfDataFormat		  = new LinkedList<String>();
		LinkedList<String> listOfIsIndexed		  = new LinkedList<String>();
		List<String> listOfNativeNamesToBeIndexed = new ArrayList<String>();

		Connection connection = getCurrentHibernateConnection();
		Connection connectionForNativeName = getCurrentHibernateConnection();
		Connection connectionForWLMParty = getCurrentHibernateConnection();

		PreparedStatement preparedStatement = null;
		PreparedStatement psForNativeName	= null;
		PreparedStatement psForWLMParty		= null;

		try {
			FileInputStream fis = new FileInputStream(file);

			if (password != null) {
				org.apache.poi.hssf.record.crypto.Biff8EncryptionKey.setCurrentUserPassword(password);
			}

			HSSFWorkbook workbook = new HSSFWorkbook(fis);
			HSSFSheet sheet = workbook.getSheetAt(0);

			long rowCounter = 0;
			
		    //Iterate through each rows from first sheet
		    Iterator<Row> rowIterator = sheet.iterator();
		    while(rowIterator.hasNext()) {
		        Row row = rowIterator.next();

		        int commonIterator = 0;
		        int statementCounter = 1;
		        long columnCounter = 0;
		        int columnIterator = 0;

		        long partyID = 0;

		        String entityID = null;
		        String updateCategory = null;

		        //For each row, iterate through each columns
		        while (columnIterator < row.getLastCellNum()) { 

		        	String data = null;
		        	if (columnCounter > 0) {
		        		Cell cell = row.getCell(columnIterator); 

		        		if (cell != null) {
				            cell.setCellType(Cell.CELL_TYPE_STRING);
				            data = cell.getStringCellValue().trim();
		        		} else {
		        			data = null;
		        		}
		        	}
		        	columnIterator++;

		            if (rowCounter == 0 && columnCounter == 0) {
		            	queryToLoadDataToParty.append("ID,");

		            	listOfDataTypes.add("NUMBER");
		            	listOfDataFormat.add(null);
		            	listOfColumns.add("ID");
		            	listOfIsIndexed.add("N");

		            	columnIterator = 0;

		            }
		            if (rowCounter == 0) {
		            	if (columnCounter > 0) {
			            	ListMetaData metadata = listMetaDataMap.get(data);
			            	queryToLoadDataToParty.append(metadata.getMappedColumn());
			            	queryToLoadDataToParty.append(",");
			            	
			            	listOfDataTypes.add(metadata.getDataType());
			            	listOfColumns.add(metadata.getMappedColumn());
			            	listOfDataFormat.add(metadata.getDataFormat());
			            	listOfIsIndexed.add(metadata.getIsIndexed());
		            	}
		            	columnCounter++;
		            } 

		            if (rowCounter == 1 && columnCounter == 0) {
		            	preparedStatement = connection.prepareStatement(queryToLoadDataToParty.toString());
		            	psForNativeName = connectionForNativeName.prepareStatement(queryToInsertIntoNativeName);
		            	psForWLMParty = connectionForWLMParty.prepareStatement(queryToInsertIntoListParty);
		            }

		            if (rowCounter >=1 ) {
		            	columnCounter++;
		            	hashBuffer.append(data);
		        		String dataType = listOfDataTypes.get(commonIterator);

	        			if (listOfColumns.get(columnIterator).equalsIgnoreCase("UPDATE_CATEGORY")) {
	        				updateCategory = data;
	        			}

	        			if(listOfColumns.get(columnIterator).equalsIgnoreCase("ENTITY_ID")) {
	        				entityID = data;
	        			}

	        			if (listOfIsIndexed.get(columnIterator).equalsIgnoreCase("Y") && data != null) {
	        				listOfNativeNamesToBeIndexed.add(data);
	        			}
		        		commonIterator++;

		        		if (dataType.equalsIgnoreCase("STRING")) {
		        			preparedStatement.setString(statementCounter++, data);

		        		} else if (dataType.equalsIgnoreCase("NUMBER")) {
		        			if (columnCounter == 1) {
		        				partyID = getMaxWLMPartyID();
		        				preparedStatement.setLong(statementCounter++, partyID);
		        				columnIterator = 0;
		        				commonIterator = 1;
		        			} else {
		        				if (data != null) {
		        					preparedStatement.setLong(statementCounter++, Long.parseLong(data));
		        				} else {
		        					long val= 0;
		        					preparedStatement.setLong(statementCounter++, val);
		        				}
		        			}

		        		} else if (dataType.equalsIgnoreCase("DATE")) {
		        			if (data != null) {
			        			String OLD_FORMAT = listOfDataFormat.get(columnIterator);
			        			SimpleDateFormat sdf = new SimpleDateFormat(OLD_FORMAT);
			        			Date date = null;
			        			try {
			        				date = sdf.parse(data);
			        			} catch (ParseException e) {
			        				e.printStackTrace();
			        			}
			        			preparedStatement.setDate(statementCounter++, new java.sql.Date(date.getTime()));
		        			} else {
		        				preparedStatement.setDate(statementCounter++, null);
		        			}
		        		}
		            }
		            //log.info(cell.getStringCellValue());
		        } // End of column iterator while loop

		        if (rowCounter == 0) {

		        	queryToLoadDataToParty.append("FINGERPRINT_DATA,TIME_CREATED,TIME_UPDATED,IS_ACTIVE,ENTITY_NAME");
		        	queryToLoadDataToParty.append(") values(");
		        	for (int i=0;i<=columnCounter;i++) {
		        		queryToLoadDataToParty.append("?");
		        		if (i < columnCounter) {
		        			queryToLoadDataToParty.append(",");
		        		}
		        	}
		        	queryToLoadDataToParty.append(",?,?,?,?");
		        	queryToLoadDataToParty.append(")");
		        } else {
		        	long dateTime = (new Date().getTime())/1000;

		        	String hashcode = getHashCode(hashBuffer.toString());
		        	String entityIDAndUpdateCategory = entityID+updateCategory;

		        	preparedStatement.setString(statementCounter++, hashcode);
		        	preparedStatement.setLong(statementCounter++, dateTime);
		        	preparedStatement.setLong(statementCounter++, dateTime);
		        	preparedStatement.setString(statementCounter++, "Y");
		        	preparedStatement.setString(statementCounter++, listDefinition.getListName());
		        	
		        	if (checkIfDataAlreadyExists(entityIDAndUpdateCategory,hashcode)) {
		        		preparedStatement.addBatch();

		        		if (listOfNativeNamesToBeIndexed.size() > 0) {
		        			for (int i=0; i< listOfNativeNamesToBeIndexed.size(); i++) {
		        				psForNativeName.setLong(1, partyID);
		        				psForNativeName.setString(2, listOfNativeNamesToBeIndexed.get(i));
		        				psForNativeName.setString(3, listDefinition.getLanguageCode());
		        				psForNativeName.setString(4, listDefinition.getCountryCode());
		        				psForNativeName.setString(5, "Y");
		        				psForNativeName.setLong(6, dateTime);
		        				psForNativeName.setLong(7, dateTime);

		        				psForNativeName.addBatch();
		        			}
		        		}

		        		psForWLMParty.setLong(1, listDefinition.getWlmListID());
		        		psForWLMParty.setLong(2, partyID);
		        		psForWLMParty.setLong(3, dateTime);
		        		psForWLMParty.setLong(4, dateTime);
		        		psForWLMParty.setString(5, "Y");

		        		psForWLMParty.addBatch();

		        		if (rowCounter % BATCH_SIZE == 0) {
		        			if (preparedStatement != null) {
		        				preparedStatement.executeBatch();
		        			}
		        			if (psForNativeName != null) {
		        				psForNativeName.executeBatch();
		        			}
		        			if (psForWLMParty != null) {
		        				psForWLMParty.executeBatch();
		        			}
		        			System.out.println("Records inserted successfully!");
		        		}
		        	}
		        	listOfNativeNamesToBeIndexed = new ArrayList<String>();
		        	hashBuffer = new StringBuffer();
		        }
		        rowCounter++;
		    } // End of row iterator while loop

			if (preparedStatement != null) {
				preparedStatement.executeBatch();
			}
			if (psForNativeName != null) {
				psForNativeName.executeBatch();
			}
			if (psForWLMParty != null) {
				psForWLMParty.executeBatch();
			}

		    System.out.println(queryToLoadDataToParty.toString());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {

			if (preparedStatement != null) {
				closeStatement(preparedStatement);
			}
			if (psForNativeName != null) {
				closeStatement(psForNativeName);
			}
			if (psForWLMParty != null) {
				closeStatement(psForWLMParty);
			}
			if (connection != null) {
				closeConnection(connection);
			}
			if (connectionForNativeName != null) {
				closeConnection(connectionForNativeName);			
			}
			if (connectionForWLMParty != null) {
				closeConnection(connectionForWLMParty);
			}
		}
	}

	private void markAsHistoricalData() {

		Connection conForParty = null;
		PreparedStatement psForParty = null;

		Connection conForListParty = null;
		PreparedStatement psForListParty = null;

		Connection conForNativeName = null;
		PreparedStatement psForNativeName = null;

		if (partyDataMap != null && partyDataMap.size() > 0) {
			for (Map.Entry<String, PartyVO> entry : partyDataMap.entrySet()) {
				PartyVO partyVO = entry.getValue();
				listOfHistoricalPartyIDs.add(partyVO.getPartyID());
			}
		}

		if (listOfHistoricalPartyIDs.size() > 0) {
			int counter = 0;
			try {
				long dateTime = (new Date().getTime())/1000;

				conForParty = getCurrentHibernateConnection();
				psForParty = conForParty.prepareStatement(queryToMarkHistoricalInParty);

				conForListParty = getCurrentHibernateConnection();
				psForListParty = conForListParty.prepareStatement(queryToMarkHistoricalInListParty);

				conForNativeName = getCurrentHibernateConnection();
				psForNativeName = conForNativeName.prepareStatement(queryToMarkHistoricalInNativeName);

				for (long partyID : listOfHistoricalPartyIDs) {
					counter++;
					psForParty.setLong(1, dateTime);
					psForParty.setLong(2, partyID);
					psForListParty.setLong(1, dateTime);
					psForListParty.setLong(2, partyID);
					psForNativeName.setLong(1, dateTime);
					psForNativeName.setLong(2, partyID);

					psForParty.addBatch();
					psForListParty.addBatch();
					psForNativeName.addBatch();

					if (counter % BATCH_SIZE == 0) {
						psForParty.executeBatch();
						psForListParty.executeBatch();
						psForNativeName.executeBatch();
					}
				}

				// Flushing the remaining data
				psForParty.executeBatch();
				psForListParty.executeBatch();
				psForNativeName.executeBatch();

			} catch (SQLException ex) {
				ex.printStackTrace();
			} finally {

				if (psForParty != null) {
					closeStatement(psForParty);
				}
				if (psForListParty != null) {
					closeStatement(psForListParty);
				}
				if (psForNativeName != null) {
					closeStatement(psForNativeName);
				}
				if (conForParty != null) {
					closeConnection(conForParty);
				}
				if (conForListParty != null) {
					closeConnection(conForListParty);
				}
				if (conForNativeName != null) {
					closeConnection(conForNativeName);
				}
			}
		}
	}

	private static String getHashCode(String sourceString) {

		if (sourceString == null)
			throw new IllegalArgumentException("Source String cannot be null");

		byte[] byteHashCode = null;
		StringBuffer hashValueBuffer = new StringBuffer();

		MessageDigest messageDigest = null;
		try {
			messageDigest = MessageDigest.getInstance("MD5");
		
			if (messageDigest == null)
				throw new IllegalArgumentException("No hash algorithm found for ");
	
			messageDigest.update(sourceString.getBytes());
			byteHashCode = messageDigest.digest();
	
			for (int i = 0; i < byteHashCode.length; i++)
				hashValueBuffer.append(byteHashCode[i]);
		} catch (Exception ex) {
			//TODO : LOG the error
		}
		return hashValueBuffer.toString();

	}

	private void loadPartyData() {
		Connection connection = null;
		ResultSet rs = null;
		PreparedStatement preparedStatement = null;

		partyDataMap = new HashMap<String,PartyVO>();

		try {
			connection = getCurrentHibernateConnection();
			preparedStatement = connection.prepareStatement(queryToLoadPartyData);
			preparedStatement.setString(1, listDefinition.getListName());
			rs = preparedStatement.executeQuery();

			while (rs.next()) {
				PartyVO partyVO = new PartyVO();
				partyVO.setPartyID(rs.getLong(1));
				
				String entityIDAndUpdatecat = String.valueOf(rs.getLong(2));
				entityIDAndUpdatecat += rs.getString(3);
				
				partyVO.setFingerPrintData(rs.getString(4));
				
				partyDataMap.put(entityIDAndUpdatecat, partyVO);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null) {
				closeResultSet(rs);
			}
			if (preparedStatement != null) {
				closeStatement(preparedStatement);
			}
			if (connection != null) {
				closeConnection(connection);
			}
		}
	}

	private boolean checkIfDataAlreadyExists(String entityIDAndUpdateCategory, String hashcode) {
		boolean result = false;
		PartyVO partyVO = partyDataMap.get(entityIDAndUpdateCategory);
		
		if (partyVO == null) {
			//New record 
			result = true;
			System.out.println("New record : " + entityIDAndUpdateCategory);
		} else {
			// Already exists check hashcode
			if (partyVO.getFingerPrintData().equalsIgnoreCase(hashcode)) {
				// No changes in the existing record no need to insert
				result = false;
				partyDataMap.remove(entityIDAndUpdateCategory);
				System.out.println("Record already exists : " + entityIDAndUpdateCategory);
			} else {
				// Data changed
				listOfHistoricalPartyIDs.add(partyVO.getPartyID());
				partyDataMap.remove(entityIDAndUpdateCategory);
				System.out.println("Record changed : " + entityIDAndUpdateCategory);
				result = true;
			}
		}
		return result;
	}

	private long getMaxWLMPartyID() {
		long maxId = 0;
		Connection connection = null;
		ResultSet rsToFindMaxID = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = getCurrentHibernateConnection();
			preparedStatement = connection.prepareStatement(queryToGetMaxIDfromWLMParty);
			rsToFindMaxID = preparedStatement.executeQuery();

			if (rsToFindMaxID.next()) {
				maxId = rsToFindMaxID.getLong(1);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			if (rsToFindMaxID != null) {
				closeResultSet(rsToFindMaxID);
			}
			if (preparedStatement != null) {
				closeStatement(preparedStatement);
			}
			if (connection != null) {
				closeConnection(connection);
			}
		}
		return maxId;
	}
	
	private class ListDefinition {
		long customListID;
		long wlmListID;
		String listName;
		String languageCode;
		String countryCode;
		
		public long getCustomListID() {
			return customListID;
		}
		public void setCustomListID(long customListID) {
			this.customListID = customListID;
		}
		public long getWlmListID() {
			return wlmListID;
		}
		public void setWlmListID(long wlmListID) {
			this.wlmListID = wlmListID;
		}
		public String getListName() {
			return listName;
		}
		public void setListName(String listName) {
			this.listName = listName;
		}
		public String getLanguageCode() {
			return languageCode;
		}
		public void setLanguageCode(String languageCode) {
			this.languageCode = languageCode;
		}
		public String getCountryCode() {
			return countryCode;
		}
		public void setCountryCode(String countryCode) {
			this.countryCode = countryCode;
		}
	}
	
	private class ListMetaData {
		String header;
		String mappedColumn;
		String dataType;
		String dataFormat;
		String isIndexed;
		public String getHeader() {
			return header;
		}
		public void setHeader(String header) {
			this.header = header;
		}
		public String getMappedColumn() {
			return mappedColumn;
		}
		public void setMappedColumn(String mappedColumn) {
			this.mappedColumn = mappedColumn;
		}
		public String getDataType() {
			return dataType;
		}
		public void setDataType(String dataType) {
			this.dataType = dataType;
		}
		public String getDataFormat() {
			return dataFormat;
		}
		public void setDataFormat(String dataFormat) {
			this.dataFormat = dataFormat;
		}
		public String getIsIndexed() {
			return isIndexed;
		}
		public void setIsIndexed(String isIndexed) {
			this.isIndexed = isIndexed;
		}
		
	}
	
	private class PartyVO {
		long partyID;
		String fingerPrintData;
		public long getPartyID() {
			return partyID;
		}
		public void setPartyID(long partyID) {
			this.partyID = partyID;
		}
		public String getFingerPrintData() {
			return fingerPrintData;
		}
		public void setFingerPrintData(String fingerPrintData) {
			this.fingerPrintData = fingerPrintData;
		}
		
	}
	public Connection getCurrentHibernateConnection() {
		Connection connection = null;
		 
		try {
 
			Class.forName("oracle.jdbc.driver.OracleDriver");
 
		} catch (ClassNotFoundException e) {
 
			System.out.println("Where is your Oracle JDBC Driver?");
			e.printStackTrace();
 
		}
 
		try {
 
			connection = DriverManager.getConnection(
					"jdbc:oracle:thin:@lvsvmdb35.qa.paypal.com:2126:QADBAA9B", "norkomdba",
					"norkomdbastg");
 
		} catch (SQLException e) {
 
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
 
		} 
		return connection;
	}
	
	public void closeResultSet(ResultSet rs) {
		if (rs!= null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void closeStatement(Statement st) {
		if (st != null) {
			try {
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void closeConnection(Connection connection) {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
