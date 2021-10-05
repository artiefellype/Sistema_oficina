package model.dao;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.vo.AdminVO;

public class AdminDAO<VO extends AdminVO> extends BaseDAO<VO> {  
	@Override
	public void inserir(VO admin) {
		conect = getConnection();
		String sql = "insert into admin (nome, senha) values (?,?)";
		PreparedStatement ptst;
		try {
		
			ptst = conect.prepareStatement(sql);		
			ptst.setString(1, admin.getName());
			ptst.setString(2, admin.getSenha());
			ptst.execute();
			
			} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public Integer getIdFromBD() { // RETORNA O ID DO REGISTRO MAIS RECENTE DO BANCO DE DADOS
		conect = getConnection();
		String sql = "select * from admin order by idadmin desc limit 1";
		Statement st;
		ResultSet rs;
		int id = 0;
		try {
			AdminVO admin = new AdminVO();
			st = conect.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
			admin.setId(rs.getInt("idadmin"));
			}
			id = admin.getId();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return id;
		
	}
	
	
	
	public void remover(VO admin) {
		conect = getConnection();
		String sql = "delete from admin where idadmin = ?";
		PreparedStatement ptst;
		try {
			
			ptst = conect.prepareStatement(sql);		
			ptst.setInt(1, admin.getId());
			ptst.executeUpdate();
			
			} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<VO> listar() {
		conect = getConnection();
		String sql = "select * from admin";
		Statement st;
		ResultSet rs;
		List<VO> admin = new ArrayList<VO>();
		
		try {
			st = conect.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				AdminVO adm = new AdminVO();
	
				adm.setId(rs.getInt("idadmin")); 
				adm.setName(rs.getString("nome"));
				adm.setSenha(rs.getString("senha")); 
			
				
				admin.add(adm);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return admin;
	}
	
	public void editarSenha(VO admin) {
		conect = getConnection();
		String sql = "update admin set senha = ? where id = ?";
		PreparedStatement ptst;
		try {
			
			ptst = conect.prepareStatement(sql);		
			ptst.setString(1, admin.getSenha());
			ptst.setInt(2, admin.getId());
			ptst.executeUpdate();
			
			} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	

}
