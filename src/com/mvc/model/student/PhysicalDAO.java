package com.mvc.model.student;

import org.apache.ibatis.session.SqlSession;

public class PhysicalDAO {
	private SqlSession session;//서비스가 공유해준 것을 쓰자. 그래야 다른 DAO객체들과 하나의 커넥션으로 묶일수 있고 트랜잭션을 이용할 수 있다.


	public void setSession(SqlSession session) {
		this.session = session;
	}



	public int insert(Physical physical){
		int result = 0;
		result = session.insert("Physical.insert", physical);
		return result;
	}

}
