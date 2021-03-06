package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.vo.AutoVO;
import model.vo.PecasVO;


public class PecasDAO<VO extends PecasVO> extends BaseDAO<VO> {
	public void inserir(VO peca) {
		conect = getConnection();
		String sql = "insert into pecas (nome, fabricante, preco, idauto) values (?,?,?,?)";
		PreparedStatement ptst;
		try {
			
			ptst = conect.prepareStatement(sql);		
			ptst.setString(1, peca.getName());
			ptst.setString(2, peca.getFabricante());
			ptst.setDouble(3, peca.getPreco());
			ptst.setInt(4, peca.getAuto().getId());
			ptst.execute();
			
			} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public Integer getIdFromBD() { // RETORNA O ID DO REGISTRO MAIS RECENTE DO BANCO DE DADOS
		conect = getConnection();
		String sql = "select * from pecas order by idpeca desc limit 1";
		Statement st;
		ResultSet rs;
		int id = 0;
		try {
			PecasVO pecas = new PecasVO();
			st = conect.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
			pecas.setId(rs.getInt("idpeca"));
			}
			id = pecas.getId();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return id;
		
	}
	

	public void remover(VO peca) {
		conect = getConnection();
		String sql = "delete from pecas where idpeca = ?";
		PreparedStatement ptst;
		try {
			
			ptst = conect.prepareStatement(sql);
			ptst.setInt(1, peca.getId());
			ptst.executeUpdate();
			
			} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
	public List<PecasVO> listar() {
		conect = getConnection();
		String sql = "select * from pecas inner join auto on pecas.idauto = auto.idauto";
		Statement st;
		ResultSet rs;
		List<PecasVO> pecas = new ArrayList<PecasVO>();
		
		try {
			st = conect.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				PecasVO peca = new PecasVO();
				AutoVO autoV = new AutoVO();
				
				autoV.setId(rs.getInt("idauto"));
				autoV.setPlaca(rs.getString("placa"));
				
				peca.setName(rs.getString("nome"));
				peca.setFabricante(rs.getString("fabricante"));
				peca.setPreco(rs.getDouble("preco"));
				peca.setId(rs.getInt("idpeca"));
				peca.setAuto(autoV);
				pecas.add(peca);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pecas;
	}
	
	public List<PecasVO> findByNome(VO peca) {
		conect = getConnection();
		
		String sql = "select * from pecas inner join auto on pecas.nome"
					+ " like '%" + peca.getName() + "%' and pecas.idauto = auto.idauto";
		Statement st;
		ResultSet rs;
		List<PecasVO> pecas = new ArrayList<PecasVO>();
		
		try {
			st = conect.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				PecasVO pecaV = new PecasVO();
				AutoVO autoV = new AutoVO();
				
				autoV.setId(rs.getInt("idauto"));
				autoV.setPlaca(rs.getString("placa"));
				
				pecaV.setName(rs.getString("nome"));
				pecaV.setFabricante(rs.getString("fabricante"));
				pecaV.setPreco(rs.getDouble("preco"));
				pecaV.setId(rs.getInt("idpeca"));
				pecaV.setAuto(autoV);
				pecas.add(pecaV);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pecas;
	}
	
	public List<PecasVO> findByFabricante(VO peca) {
		conect = getConnection();
		String sql = "select * from pecas where fabricante like'%" + peca.getFabricante() + "%'";
		Statement st;
		ResultSet rs;
		List<PecasVO> pecas = new ArrayList<PecasVO>();
		
		try {
			st = conect.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				PecasVO pecaV = new PecasVO();
				pecaV.setName(rs.getString("nome"));
				pecaV.setFabricante(rs.getString("fabricante"));
				pecaV.setPreco(rs.getDouble("preco"));
				pecaV.setId(rs.getInt("idpeca"));
				pecas.add(pecaV);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pecas;
	}
	
	public List<PecasVO> findByAuto(VO peca) {
		conect = getConnection();
		String sql = "select * from pecas,auto  where auto.placa = '%" + peca.getAuto().getPlaca() + "%' and pecas.idauto = auto.idauto";
		Statement st;
		ResultSet rs;
		List<PecasVO> pecas = new ArrayList<PecasVO>();
		
		try {
			st = conect.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				PecasVO pecaV = new PecasVO();
				AutoVO autoV = new AutoVO();
				
				autoV.setId(rs.getInt("idauto"));
				autoV.setPlaca(rs.getString("placa"));
				
				pecaV.setName(rs.getString("nome"));
				pecaV.setFabricante(rs.getString("fabricante"));
				pecaV.setPreco(rs.getDouble("preco"));
				pecaV.setId(rs.getInt("idpeca"));
				pecas.add(pecaV);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pecas;
	}
	public List<PecasVO> findByPreco(VO peca) {
		conect = getConnection();
		String sql = "select * from pecas,auto  where preco = '" + peca.getPreco() + "' and pecas.idauto = auto.idauto";
		Statement st;
		ResultSet rs;
		List<PecasVO> pecas = new ArrayList<PecasVO>();
		
		try {
			st = conect.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				PecasVO pecaV = new PecasVO();
				AutoVO autoV = new AutoVO();
				
				autoV.setId(rs.getInt("idauto"));
				autoV.setPlaca(rs.getString("placa"));
				
				pecaV.setName(rs.getString("nome"));
				pecaV.setFabricante(rs.getString("fabricante"));
				pecaV.setPreco(rs.getDouble("preco"));
				pecaV.setId(rs.getInt("idpeca"));
				pecas.add(pecaV);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pecas;
	}
	public List<PecasVO> findByAutoPl(AutoVO pl) {
		conect = getConnection();
		String sql = "select * from pecas,auto  where auto.placa = '%" + pl.getPlaca() + "%' and pecas.idauto = auto.idauto";
		Statement st;
		ResultSet rs;
		List<PecasVO> pecas = new ArrayList<PecasVO>();
		
		try {
			st = conect.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				PecasVO pecaV = new PecasVO();
				AutoVO autoV = new AutoVO();
				
				autoV.setId(rs.getInt("idauto"));
				autoV.setPlaca(rs.getString("placa"));
				
				pecaV.setName(rs.getString("nome"));
				pecaV.setFabricante(rs.getString("fabricante"));
				pecaV.setPreco(rs.getDouble("preco"));
				pecaV.setId(rs.getInt("idpeca"));
				pecas.add(pecaV);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pecas;
	}

	
	public void editar(VO peca) {
		conect = getConnection();
		String sql = "update pecas set nome = ?, fabricante = ?, preco = ? where idpeca = ?";
		PreparedStatement ptst;
		try {
			
			ptst = conect.prepareStatement(sql);		
			ptst.setString(1, peca.getName());
			ptst.setString(2, peca.getFabricante());
			ptst.setDouble(3, peca.getPreco());
			ptst.setInt(4, peca.getId());
			ptst.execute();
			
			} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
