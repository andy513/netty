package andy.test;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * @author andy<andy_513@163.com>
 */
public class TestMehotd {

	private static void other() {
		JFrame frame = new JFrame("测试中");
		frame.setSize(500, 500);
		frame.setLayout(new GridLayout(3, 1));
		frame.setLayout(new FlowLayout());
		frame.setVisible(true);
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent) {
				System.exit(0);
			}
		});
		JLabel headerLabel = new JLabel("", JLabel.CENTER);
		JButton button1 = new JButton("点我!");
		JButton button2 = new JButton("也点我!");

		frame.getContentPane().add(button1);
		frame.getContentPane().add(button2);
		// 这里addActionListener方法的参数是ActionListener，是一个函数式接口
		// 使用lambda表达式方式
		button1.addActionListener(e -> {
			System.out.println("这里是Lambda实现方式");
		});
		// 使用方法引用方式
		button2.addActionListener(TestMehotd::doSomething);
		System.out.println(TestMehotd.doSomething());
	}

	/**
	 * 这里是函数式接口ActionListener的实现方法
	 * 
	 * @param e
	 */
	private static void doSomething(ActionEvent e) {
		System.out.println("这里是方法引用实现方式");
	}

	private static String doSomething() {
		return "这里是方法引用实现方式";
	}
}
