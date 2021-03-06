package model.dao;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Calendar;

import model.vo.OrcamentoVO;
import model.vo.ClienteVO;
import model.vo.ServicosVO;
import model.vo.AutoVO;


public class OrcamentoDAO<VO extends OrcamentoVO> extends BaseDAO<VO> {
	public void inserir(VO orc) {
		conect = getConnection();
		String sql = "insert into orcamentos (idcliente, idservico, idauto, custo, data) values (?,?,?,?,?)";
		PreparedStatement ptst;
		try {
			
			ptst = conect.prepareStatement(sql);		
			ptst.setInt(1, orc.getClientName().getId());
			ptst.setInt(2, orc.getServicos().getId());
			ptst.setInt(3, orc.getAuto().getId());
			ptst.setDouble(4, orc.getCusto());
			ptst.setDate(5, new java.sql.Date(orc.getData().getTimeInMillis()));
			ptst.execute();
			
			} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Integer getIdFromBD() { // RETORNA O ID DO REGISTRO MAIS RECENTE DO BANCO DE DADOS
		conect = getConnection();
		String sql = "select * from orcamentos order by idorm desc limit 1";
		Statement st;
		ResultSet rs;
		int id = 0;
		try {
			OrcamentoVO orc = new OrcamentoVO();
			st = conect.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
			orc.setId(rs.getInt("idorm"));
			}
			id = orc.getId();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return id;
		
	}
	
	public void remover(VO orc) {
		conect = getConnection();
		String sql = "delete from orcamentos where idorm = ?";
		PreparedStatement ptst;
		try {
			
			ptst = conect.prepareStatement(sql);		
			ptst.setInt(1, orc.getId());
			ptst.executeUpdate();
			
			} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<OrcamentoVO> listar() {
		conect = getConnection();
		String sql = "select * from orcamentos, auto, servicos, clientes where "
				+ "orcamentos.idcliente = clientes.idcliente and "
				+ "orcamentos.idauto = auto.idauto and "
				+ "orcamentos.idservico = servicos.idservico";
		Statement st;
		ResultSet rs;
		List<OrcamentoVO> orc = new ArrayList<OrcamentoVO>();
		
		try {
			st = conect.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				OrcamentoVO orcamento = new OrcamentoVO();
				ClienteVO cliente = new ClienteVO();
				ServicosVO servico = new ServicosVO();
				AutoVO auto = new AutoVO();
				
				Calendar data = Calendar.getInstance();
				
				data.setTimeInMillis(rs.getDate("data").getTime());
				
				cliente.setId(rs.getInt("idcliente"));
				cliente.setName(rs.getString("nome"));
				cliente.setCPF(rs.getString("cpf"));
				
				auto.setId(rs.getInt("idauto"));
				auto.setPlaca(rs.getString("placa"));
				
				servico.setTipo(rs.getString("tipo"));
				servico.setValor(rs.getDouble("valor"));
				servico.setId(rs.getInt("idservico"));
				
				orcamento.setCusto(rs.getDouble("custo"));
				orcamento.setData(data);
				orcamento.setId(rs.getInt("idorm"));
				orcamento.setClientName(cliente);
				orcamento.setServicos(servico);
				orcamento.setAuto(auto);
				orc.add(orcamento);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return orc;
	}
	
	public List<OrcamentoVO> findByData( String dataInit, String dataEnd) {
		conect = getConnection();
		String sql = "select * from orcamentos where data between '"+ dataInit +"' "
						+ " and '"+dataEnd+"'";
		
		Statement st;
		ResultSet rs;
		List<OrcamentoVO> orc = new ArrayList<OrcamentoVO>();
		
		try {
			st = conect.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				OrcamentoVO orcamento = new OrcamentoVO();
				ClienteVO cliente = new ClienteVO();
				ServicosVO servico = new ServicosVO();
				AutoVO auto = new AutoVO();
				Calendar data = Calendar.getInstance();
				
				data.setTimeInMillis(rs.getDate("data").getTime());
				
				cliente.setId(rs.getInt("idcliente"));
				servico.setId(rs.getInt("idservico"));
				auto.setId(rs.getInt("idauto"));
				
				orcamento.setCusto(rs.getDouble("custo"));
				orcamento.setData(data);
				orcamento.setId(rs.getInt("idorm"));
				orcamento.setClientName(cliente);
				orcamento.setServicos(servico);
				orcamento.setAuto(auto);
				orc.add(orcamento);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return orc;
	}
	
	public List<OrcamentoVO> findByCliente(VO orca) {
		conect = getConnection();
		String sql = "select * from orcamentos where idcliente = " + orca.getClientName().getId() + "";
		Statement st;
		ResultSet rs;
		List<OrcamentoVO> orc = new ArrayList<OrcamentoVO>();
		
		try {
			st = conect.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				OrcamentoVO orcamento = new OrcamentoVO();
				ClienteVO cliente = new ClienteVO();
				ServicosVO servico = new ServicosVO();
				AutoVO auto = new AutoVO();
				Calendar data = Calendar.getInstance();
				
				data.setTimeInMillis(rs.getDate("data").getTime());
				cliente.setId(rs.getInt("idcliente"));
				servico.setId(rs.getInt("idservico"));
				auto.setId(rs.getInt("idauto"));
				
				orcamento.setCusto(rs.getDouble("custo"));
				orcamento.setData(data);
				orcamento.setId(rs.getInt("idorm"));
				orcamento.setClientName(cliente);
				orcamento.setServicos(servico);
				orcamento.setAuto(auto);
				orc.add(orcamento);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return orc;
	}
	
	public List<OrcamentoVO> findByAuto(VO orca) {
		conect = getConnection();
		String sql = "select * from orcamentos where idauto = " + orca.getAuto().getId() + "";
		Statement st;
		ResultSet rs;
		List<OrcamentoVO> orc = new ArrayList<OrcamentoVO>();
		
		try {
			st = conect.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				OrcamentoVO orcamento = new OrcamentoVO();
				ClienteVO cliente = new ClienteVO();
				ServicosVO servico = new ServicosVO();
				AutoVO auto = new AutoVO();
				Calendar data = Calendar.getInstance();
				data.setTimeInMillis(rs.getDate("data").getTime());
				
				cliente.setId(rs.getInt("idcliente"));
				servico.setId(rs.getInt("idservico"));
				auto.setId(rs.getInt("idauto"));
				
				orcamento.setCusto(rs.getDouble("custo"));
				orcamento.setData(data);
				orcamento.setId(rs.getInt("idorm"));
				orcamento.setClientName(cliente);
				orcamento.setServicos(servico);
				orcamento.setAuto(auto);
				orc.add(orcamento);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return orc;
	}
	
	public void editar(VO orc) {
		conect = getConnection();
		String sql = "update orcamentos set custo = ?, data = ?, idcliente = ?, idauto = ? where idorm= ?";
		PreparedStatement ptst;
		try {
			
			ptst = conect.prepareStatement(sql);		
			ptst.setDouble(1, orc.getCusto());
			ptst.setDate(2, new java.sql.Date(orc.getData().getTimeInMillis()));
			ptst.setInt(3, orc.getClientName().getId());
			ptst.setInt(4, orc.getAuto().getId());
			ptst.setInt(5, orc.getId());
			ptst.executeUpdate();
			
			} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<OrcamentoVO> findByServico(VO orca) {
		conect = getConnection();
		String sql = "select * from orcamentos where idservico = " + orca.getServicos().getId() + "";
		Statement st;
		ResultSet rs;
		List<OrcamentoVO> orc = new ArrayList<OrcamentoVO>();
		
		try {
			st = conect.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				OrcamentoVO orcamento = new OrcamentoVO();
				ClienteVO cliente = new ClienteVO();
				ServicosVO servico = new ServicosVO();
				AutoVO auto = new AutoVO();
				Calendar data = Calendar.getInstance();
				data.setTimeInMillis(rs.getDate("data").getTime());
				
				cliente.setId(rs.getInt("idcliente"));
				servico.setId(rs.getInt("idservico"));
				auto.setId(rs.getInt("idauto"));
				
				orcamento.setCusto(rs.getDouble("custo"));
				orcamento.setData(data);
				orcamento.setId(rs.getInt("idorm"));
				orcamento.setClientName(cliente);
				orcamento.setServicos(servico);
				orcamento.setAuto(auto);
				orc.add(orcamento);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return orc;
	}

}


