package andy.model;

/**
 * @author andy<andy_513@163.com>
 */
public class ManagerB extends Manager {

	public ManagerB(Project project) {
		super(project);
	}

	/**
	 * 项目经理自己的事情：做早期工作
	 */
	public void doEarlyWork() {
		System.out.println("项目经理B 在做需求分析");
		System.out.println("项目经理B 在做详细设计");
	}

	/**
	 * 项目经理做收尾工作
	 */
	public void doEndWork() {
		System.out.println("项目经理B 在做收尾工作");
	}

	public static void main(String[] args) {
		Project employe = new Employe(); // 代码工人
		Project managerA = new ManagerA(employe); // 项目经理
		Project managerB = new ManagerB(managerA); // 项目经理
		// 以经理的名义将编码完成，功劳都是经理的，实际编码的是工人
//		managerA.doCoding();
		managerB.doCoding();
		
		System.out.println("\n\n\n");
		Project manager = new ManagerB(new ManagerA(employe));
		manager.doCoding();
	}
}
