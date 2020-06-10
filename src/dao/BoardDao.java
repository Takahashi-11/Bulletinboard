package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import dto.Board;

public class BoardDao {
	private static final String url = "jdbc:mysql://localhost:3306/kadai?serverTimezone=JST";
	private static final String user = "root";
	private static final String pass = "sql6ygoh";

	private static Connection con = null;
	private static PreparedStatement pstmt = null;
	private static ResultSet rs = null;
	private static Board result = null;

	//投稿一覧
	public static ArrayList<Board> selectAll() {
		ArrayList<Board> list = new ArrayList<>();

	try{
		Class.forName("com.mysql.cj.jdbc.Driver");

		con = DriverManager.getConnection(url,user,pass);

		String sql = "SELECT * FROM board";

		pstmt = con.prepareStatement(sql);

		rs = pstmt.executeQuery();

		while(rs.next()){
			int id = rs.getInt("id");
			String name = rs.getString("name");
			String email = rs.getString("email");
			String content = rs.getString("content");
			String file = rs.getString("file");
			Timestamp posttime = rs.getTimestamp("posttime");
			Timestamp editingtime = rs.getTimestamp("editingtime");
			result = new Board(id,name,email,content,file,posttime,editingtime);
			list.add(result);
		}
	} catch (ClassNotFoundException e) {
		System.out.println("JDBCドライバが見つかりません。");
		e.printStackTrace();
	} catch (SQLException e) {
		System.out.println("DBアクセス時にエラーが発生しました。");
		e.printStackTrace();
	}finally {
		try {
			if(rs != null){
				rs.close();
			}
		} catch (SQLException e) {
			System.out.println("DBアクセス時にエラーが発生しました。");
			e.printStackTrace();
		}
		try {
			if (pstmt != null) {
				pstmt.close();
			}
		}catch (SQLException e) {
				System.out.println("DBアクセス時にエラーが発生しました。");
				e.printStackTrace();
			}
		}
		return list;
	}

	//投稿(ファイル無し）
	public static void addDao(String name,String email,String content,Timestamp posttime) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			con = DriverManager.getConnection(url,user,pass);

			String sql = "INSERT INTO board(name,email,content,posttime) VALUES(?,?,?,?);";

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1,name);
			pstmt.setString(2, email);
			pstmt.setString(3, content);
			pstmt.setTimestamp(4, posttime);

			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			System.out.println("JDBCドライバが見つかりません。");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("DBアクセス時にエラーが発生しました。");
			e.printStackTrace();
		}finally {
				try {
					if( pstmt != null){
						pstmt.close();
					}
				} catch(SQLException e){
					System.out.println("DB切断時にエラーが発生しました。");
					e.printStackTrace();
				}
				try {
					if( con != null){
						con.close();
					}
				} catch (SQLException e){
					System.out.println("DB切断時にエラーが発生しました。");
					e.printStackTrace();
				}
			}
		}

	//投稿(ファイルあり）
		public static void fileaddDao(String name,String email,String content,String file,Timestamp posttime) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");

				con = DriverManager.getConnection(url,user,pass);

				String sql = "INSERT INTO board(name,email,content,file,posttime) VALUES(?,?,?,?,?);";

				pstmt = con.prepareStatement(sql);
				pstmt.setString(1,name);
				pstmt.setString(2, email);
				pstmt.setString(3, content);
				pstmt.setString(4, file);
				pstmt.setTimestamp(5, posttime);

				pstmt.executeUpdate();

			} catch (ClassNotFoundException e) {
				System.out.println("JDBCドライバが見つかりません。");
				e.printStackTrace();
			} catch (SQLException e) {
				System.out.println("DBアクセス時にエラーが発生しました。");
				e.printStackTrace();
			}finally {
					try {
						if( pstmt != null){
							pstmt.close();
						}
					} catch(SQLException e){
						System.out.println("DB切断時にエラーが発生しました。");
						e.printStackTrace();
					}
					try {
						if( con != null){
							con.close();
						}
					} catch (SQLException e){
						System.out.println("DB切断時にエラーが発生しました。");
						e.printStackTrace();
					}
				}
			}

	//削除
	public static void deleteDao(String key) {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			con = DriverManager.getConnection(url,user,pass);

			String sql = "DELETE FROM board WHERE id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, key);

			pstmt.executeUpdate();
			rs = pstmt.executeQuery();

		} catch (ClassNotFoundException e) {
			System.out.println("JDBCドライバが見つかりません。");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("DBアクセス時にエラーが発生しました。");
			e.printStackTrace();
		}finally {
				try {
					if( pstmt != null){
						pstmt.close();
					}
				} catch(SQLException e){
					System.out.println("DB切断時にエラーが発生しました。");
					e.printStackTrace();
				}
				try {
					if( con != null){
						con.close();
					}
				} catch (SQLException e){
					System.out.println("DB切断時にエラーが発生しました。");
					e.printStackTrace();
				}
			}
		}

	//id検索
		public static Board IDsearchDao(String key) {

			try {
				Class.forName("com.mysql.cj.jdbc.Driver");

				con = DriverManager.getConnection(url,user,pass);

				String sql = "SELECT * FROM board WHERE id = ?;";

				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, key);

				rs = pstmt.executeQuery();

				rs.next();

				int id = rs.getInt("id");
				String name = rs.getString("name");
				String email = rs.getString("email");
				String content = rs.getString("content");
				String file = rs.getString("file");
				Timestamp posttime = rs.getTimestamp("posttime");
				Timestamp editingtime = rs.getTimestamp("editingtime");
				result = new Board(id,name,email,content,file,posttime,editingtime);

			} catch (ClassNotFoundException e) {
				System.out.println("JDBCドライバが見つかりません。");
				e.printStackTrace();
			} catch (SQLException e) {
				System.out.println("DBアクセス時にエラーが発生しました。");
				e.printStackTrace();
			}finally {
				try {
					if(rs != null){
						rs.close();
					}
				} catch (SQLException e) {
					System.out.println("DBアクセス時にエラーが発生しました。");
					e.printStackTrace();
				}
				try {
					if (pstmt != null) {
						pstmt.close();
					}
				}catch (SQLException e) {
						System.out.println("DBアクセス時にエラーが発生しました。");
						e.printStackTrace();
					}
				}
				return result;
			}

	//内容の変更
		public static void EditDao(String id,String text,Timestamp eTime) {

			try {
				Class.forName("com.mysql.cj.jdbc.Driver");

				con = DriverManager.getConnection(url,user,pass);

				String sql = "UPDATE board SET content = ?, editingtime = ? WHERE id = ?";

				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, text);
				pstmt.setTimestamp(2, eTime);
				pstmt.setString(3, id);

				pstmt.executeUpdate();		//データベース更新時に必要

			} catch (ClassNotFoundException e) {
				System.out.println("JDBCドライバが見つかりません。");
				e.printStackTrace();
			} catch (SQLException e) {
				System.out.println("DBアクセス時にエラーが発生しました。");
				e.printStackTrace();
			}finally {
					try {
						if( pstmt != null){
							pstmt.close();
						}
					} catch(SQLException e){
						System.out.println("DB切断時にエラーが発生しました。");
						e.printStackTrace();
					}
					try {
						if( con != null){
							con.close();
						}
					} catch (SQLException e){
						System.out.println("DB切断時にエラーが発生しました。");
						e.printStackTrace();
					}
				}
			}
}
