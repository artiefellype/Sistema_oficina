package model.dao;

import model.vo.ClienteVO;

public class testBD {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BaseDAO client = new BaseDAO();
		System.out.println(client.getConnection());
		
		
		ClienteDAO clientD = new ClienteDAO();
		ClienteVO clientV = new ClienteVO();
		
		clientV.setName("irineu");
		clientV.setCPF("11r2344-32");
		clientV.setEndereco("Padr fabio");
		
		
		clientD.inserir(clientV);
		
		
	}

}
