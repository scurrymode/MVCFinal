package com.mvc.model.student;

import org.apache.ibatis.session.SqlSession;

public class PhysicalDAO {
	private SqlSession session;//���񽺰� �������� ���� ����. �׷��� �ٸ� DAO��ü��� �ϳ��� Ŀ�ؼ����� ���ϼ� �ְ� Ʈ������� �̿��� �� �ִ�.


	public void setSession(SqlSession session) {
		this.session = session;
	}



	public int insert(Physical physical){
		int result = 0;
		result = session.insert("Physical.insert", physical);
		return result;
	}

}
