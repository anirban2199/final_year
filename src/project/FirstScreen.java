package project;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class FirstScreen extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
	protected JButton start = new JButton("Insert your card here");
	static JFrame jf;
	public FirstScreen() {
		setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));
		setLayout(new BorderLayout(10, 10));
		JPanel jpCenter = new JPanel();
		add(jpCenter, BorderLayout.CENTER);
		jpCenter.setLayout(new FlowLayout());
		jpCenter.add(start);
		start.addActionListener(this);
	}
	public static void main(String[] args) {
		JPanel jp = new FirstScreen();
		jf = new JFrame("Welcome");
		jf.setContentPane(jp);
		jf.setResizable(false);
		jf.pack();	
		
		Dimension d1 = jf.getSize();
		
		Toolkit t = Toolkit.getDefaultToolkit();
		Dimension d2 = t.getScreenSize();
		
		int x = (d2.width - d1.width) / 2;
		int y = (d2.height - d1.height) / 2;
		
		jf.setLocation(x, y);
		jf.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		PinNumber.main(null);
		jf.dispose();
	}
}