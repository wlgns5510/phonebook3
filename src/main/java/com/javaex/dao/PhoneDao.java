package com.javaex.dao;

import java.sql.*;
import java.util.*;
import com.javaex.vo.*;

public class PhoneDao {
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String id = "phonedb";
	private String pw = "phonedb";
	
	private void getConnection() {
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, id, pw);
		} catch(ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch(SQLException e) {
			System.out.println("error: " + e);
		}
	}
	
	private void close() {
		try {
			if(rs != null) {
				rs.close();
			}
			if(pstmt != null) {
				pstmt.close();
			}
			if(conn != null) {
				conn.close();
			}
		} catch(SQLException e) {
			System.out.println("error: " + e);
		}
	}
	
	public int personInsert(PersonVo pv) {
		int count = -1;
		
		try {
			getConnection();
			
			String query = "";
			query += " insert into person";
			query += " values(seq_person_id.nextval, ?, ?, ?)";
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, pv.getName());
			pstmt.setString(2, pv.getHp());
			pstmt.setString(3, pv.getCompany());
			
			count = pstmt.executeUpdate();
			
			System.out.println(count + "건이 등록되었습니다.");
			
		} catch(SQLException e) {
			System.out.println("error: " + e);
		}
		
		close();
		return count;
	}
	
	
	public int personUpdate(PersonVo pv) {
		int count = -1;
		
		try {
			getConnection();
			
			String query = "";
			query += " update person";
			query += " set	  name = ?,";
			query += " 		  hp = ?,";
			query += " 		  company = ?";
			query += " where person_id = ?";
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, pv.getName());
			pstmt.setString(2, pv.getHp());
			pstmt.setString(3, pv.getCompany());
			pstmt.setInt(4, pv.getPersonId());
			
			count = pstmt.executeUpdate();
			
			System.out.println(count + "건이 수정되었습니다.");
			
		} catch(SQLException e) {
			System.out.println("error: " + e);
		}
		
		close();
		return count;
	}
	
	
	public int personDelete(int personId) {
		int count = -1;
		
		try {
			getConnection();
			
			String query = "";
			query += " delete from person";
			query += " where person_id = ?";
			
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, personId);
			
			count = pstmt.executeUpdate();
			
			System.out.println(count + "건이 삭제되었습니다.");
			
		} catch(SQLException e) {
			System.out.println("error: " + e);
		}
		
		close();
		return count;
	}
	
	public List<PersonVo> personSelect() {
		List<PersonVo> pList = new ArrayList<>();
		
		try {
			getConnection();
			
			String query = "";
			query += " select	person_id,";
			query += " 			name,";
			query += " 			hp,";
			query += " 			company";
			query += " from		person";
			
			pstmt = conn.prepareStatement(query);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int personId = rs.getInt("person_id");
				String name = rs.getString("name");
				String hp = rs.getString("hp");
				String company = rs.getString("company");
				
				pList.add(new PersonVo(personId, name, hp, company));
			}
			
		} catch(SQLException e) {
			System.out.println("error: " + e);
		}
		
		close();
		return pList;
	}
	
	
	public PersonVo getPerson(int pId) {
		PersonVo pVo = new PersonVo();
		
		try {
			getConnection();
			
			String query = "";
			query += " select	person_id,";
			query += " 			name,";
			query += " 			hp,";
			query += " 			company";
			query += " from		person";
			query += " where		person_id = ?";
			
			pstmt = conn.prepareStatement(query);
			
			if(pId != 0) {
				pstmt.setInt(1, pId);
			}
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				pVo.setPersonId(pId);
				pVo.setName(rs.getString("name"));
				pVo.setHp(rs.getString("hp"));
				pVo.setCompany(rs.getString("company"));
			}
			
		} catch(SQLException e) {
			System.out.println("error: " + e);
		}
		
		close();
		return pVo;
	}
}