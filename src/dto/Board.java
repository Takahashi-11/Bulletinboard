package dto;

import java.sql.Timestamp;

public class Board {
	private int id;				//id(自動採番）
	private String name;		//名前
	private String email;		//メールアドレス
	private String content;		//内容
	private String file;		//添付ファイル
	private Timestamp posttime;		//投稿日時(sql側はDATETIME型)
	private Timestamp editingtime;	//編集日時

	public Board() {
	}

	public Board(int id,String name,String email,String content,String file,Timestamp posttime,Timestamp editingtime) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.content = content;
		this.file = file;
		this.posttime = posttime;
		this.editingtime = editingtime;
	}

	public int getId() {
		return id;
	}

	public void setId(int id){
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public Timestamp getPosttime() {
		return posttime;
	}

	public void setPosttime(Timestamp posttime){
		this.posttime = posttime;
	}

	public Timestamp getEditingtime() {
		return editingtime;
	}

	public void setEditingtime(Timestamp editingtime) {
		this.editingtime = editingtime;
	}
}
