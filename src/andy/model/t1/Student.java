package andy.model.t1;

/**
 * @author andy<andy_513@163.com>
 */
public class Student extends People {

	public Student(Person person) {
		super(person);
	}

	@Override
	protected void start() {
		System.out.println("起立");
	}

	@Override
	protected void end() {
		System.out.println("再见");
	}

	public static void main(String[] args) {
		Person employe = new Employe();
		Person person = new Teather(new Student(employe));
		person.say();
	}
	
}
