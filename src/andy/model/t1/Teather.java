package andy.model.t1;

/**
 * @author Andy<andy_513@163.com>
 */
public class Teather extends People {
	
	public Teather(Person person) {
		super(person);
	}

	@Override
	protected void start() {
		System.out.println("上课");
	}

	@Override
	protected void end() {
		System.out.println("下课");
	}
	
	

}