package Login.DAO;

import Login.Member;

public interface DatabaseServiceInter {
	public boolean open();
	public void Insert(Member member);
	public boolean Select(String id,String pw);
	public boolean Select(String id);
}
