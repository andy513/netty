package andy.model.t1;

/**
 * @author Andy<andy_513@163.com>
 */
public class People implements Person {
	
	Person person;
	
	public People(Person person) {
		this.person = person;
	}

	@Override
	public void say() {
		start();
		person.say();
		end();
	}
	
	protected void start(){
	}
	
	protected void end(){
	}

}
