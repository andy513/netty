package andy.swing;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @author Andy<andy_513@163.com>
 */
public class TestJFrame {
	JFrame mainFrame = null;
	JLabel headerLabel = null;
	JLabel statusLabel = null;
	JPanel controlPanel = null;
	JLabel msglabel = null;

	public TestJFrame() {
		long time = System.nanoTime();
		prepareGUI();
		System.out.println(System.nanoTime() - time);
	}

	public static void main(String[] args) {
		TestJFrame swingContainerDemo = new TestJFrame();
		swingContainerDemo.showJFrameDemo();
	}

	private void prepareGUI() {
		mainFrame = new JFrame("Java Swing Examples");
		mainFrame.setSize(400, 400);
		window_center(mainFrame);
		mainFrame.setLayout(new GridLayout(3, 1));
		mainFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent) {
				System.out.println("关闭程序");
				System.exit(0);
			}
		});
		headerLabel = new JLabel("", JLabel.CENTER);
		statusLabel = new JLabel("", JLabel.CENTER);
		statusLabel.setSize(350, 100);
		msglabel = new JLabel("Welcome to TutorialsPoint SWING Tutorial.", JLabel.CENTER);
		controlPanel = new JPanel();
		controlPanel.setLayout(new FlowLayout());
		mainFrame.add(headerLabel);
		mainFrame.add(controlPanel);
		mainFrame.add(statusLabel);
		mainFrame.setVisible(true);
		// mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}

	/**
	 * 居中显示
	 * 
	 * @param mainFrame
	 */
	private void window_center(JFrame mainFrame) {
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		int x = (int) (toolkit.getScreenSize().getWidth() - mainFrame.getWidth()) / 2;
		int y = (int) (toolkit.getScreenSize().getHeight() - mainFrame.getHeight()) / 2;
		mainFrame.setLocation(x, y);
	}

	private void showJFrameDemo() {
		headerLabel.setText("Container in action: JFrame");

		final JFrame frame = new JFrame();
		frame.setSize(300, 300);
		frame.setLayout(new FlowLayout());
		frame.add(msglabel);
		window_center(frame);
		// frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent) {
				frame.dispose();
			}
		});

		JButton okButton = new JButton("Open a Frame");
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				statusLabel.setText("A Frame shown to the user.");
				frame.setVisible(true);
			}
		});
		controlPanel.add(okButton);
		mainFrame.setVisible(true);
	}
}
