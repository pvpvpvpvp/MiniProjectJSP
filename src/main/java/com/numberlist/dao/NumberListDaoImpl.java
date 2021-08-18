package com.numberlist.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.numberlist.vo.NumberListVo;

public class NumberListDaoImpl implements NumberListDao {

	//오라클 연결
	private Connection getConnection() throws SQLException {
		Connection conn = null;
		try {
			// 드라이버 로드
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// Connection 가져오기
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", // DBURL
					"C##BITUSER", // DB User
					"bituser"); // DB Pass
		} catch (ClassNotFoundException e) {
			System.err.println("드라이버 로드 실패!");
			e.printStackTrace();
		}

		return conn;
	}
	
	@Override
	public int insert(NumberListVo vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int insertedCount = 0;

		try {
			conn = getConnection();
			String sql = "INSERT INTO phone_book" + " VALUES(seq_phone_book.NEXTVAL,?,?,?)";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, vo.getNumberName());
			pstmt.setString(2, vo.getNumberHp());
			pstmt.setString(3, vo.getNumberTel());

			insertedCount = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			// TODO: handle exception
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
				// TODO: handle exception
			}
		}
		return insertedCount;
	}

	@Override
	public List<NumberListVo> getSearch(String find) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<NumberListVo> list = new ArrayList<>();

		try {
			conn = getConnection();
			String sql = "SELECT phone_book.id, phone_book.name, phone_book.hp, phone_book.tel  FROM phone_book "
					+ " WHERE phone_book.id LIKE ? OR phone_book.name LIKE ?" + " OR phone_book.hp LIKE ?"
					+ " OR phone_book.tel LIKE ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + find + "%");
			pstmt.setString(2, "%" + find + "%");
			pstmt.setString(3, "%" + find + "%");
			pstmt.setString(4, "%" + find + "%");

			rs = pstmt.executeQuery();
			while (rs.next()) {
				Long id = rs.getLong(1);
				String name = rs.getString(2);
				String hp = rs.getString(3);
				String tel = rs.getString(4);
				NumberListVo vo = new NumberListVo();
				vo.setNumberId(id);
				vo.setNumberHp(hp);
				vo.setNumberName(name);
				vo.setNumberTel(tel);
				list.add(vo);
			}
		} catch (SQLException e) {
			// TODO: handle exception
		}
		return list;
	}

	@Override
	public int delete(Long no) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int deletedCount = 0;
		try {
			conn = getConnection();
			String sql = "DELETE FROM phone_book " + " WHERE phone_book.id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, no);
			deletedCount = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
				// TODO: handle exception
			}
		}
		return deletedCount;
	}

	@Override
	public boolean insert(NumberListVo vo, Long checkIndex) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int insertedCount = 0;

		try {
			conn = getConnection();
			String sql = "INSERT INTO phone_book" + " VALUES(?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, checkIndex);
			pstmt.setString(2, vo.getNumberName());
			pstmt.setString(3, vo.getNumberHp());
			pstmt.setString(4, vo.getNumberTel());

			insertedCount = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			// TODO: handle exception
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
				// TODO: handle exception
			}
		}
		return 1 == insertedCount;
	}

	@Override
	public List<NumberListVo> getList() {
		List<NumberListVo> list = new ArrayList<NumberListVo>();

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();
			stmt = conn.createStatement();

			String sql = "SELECT phone_book.id, phone_book.name, phone_book.hp, phone_book.tel " + " FROM phone_book ORDER BY phone_book.id DESC";

			// 쿼리 수행
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				// 데이터 받기
				Long id = rs.getLong(1);
				String name = rs.getString(2);
				String hp = rs.getString(3);
				String tel = rs.getString(4);
				NumberListVo vo = new NumberListVo();
				vo.setNumberId(id);
				vo.setNumberHp(hp);
				vo.setNumberName(name);
				vo.setNumberTel(tel);
						
				list.add(vo);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 자원 정리
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	

}
