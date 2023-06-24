package k69;

import java.awt.Color;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class GUIThuCong extends JFrame{
	private int width;
	private int height;
	private JLabel lbName;
	private JTextField tfName;
	private JButton btnClick;
	
	public GUIThuCong() {
		this.width = 640;
		this.height = 320;
	}

	public GUIThuCong(int width, int height) throws HeadlessException {
		super();
		this.width = width;
		this.height = height;
	}

	public void setAppIcon(String iconPath) {
		ImageIcon icon = new ImageIcon(iconPath);
		this.setIconImage(icon.getImage());
	}
	
	public void init(String title) {
		this.setSize(width, height);
		this.setTitle(title);
		this.setLocationRelativeTo(null);
		this.setAppIcon("images\\ga.jpg");
		this.getContentPane().setBackground(new Color(255, 0, 170));
		addViews();
		addEvents();
		this.setLayout(null);
		this.setVisible(true);
	}
	
	public void addViews() {
		lbName = new JLabel("Full name: ");
		lbName.setBounds(100, 50, 150, 30);
		lbName.setForeground(new Color(51, 153, 255));
		lbName.setFont(new Font("Serif", Font.PLAIN, 16));
		this.add(lbName);  
		
		tfName = new JTextField();
		tfName.setBounds(200, 50, 150, 30);
		this.add(tfName);
		
		btnClick = new JButton("Submit");
		btnClick.setBounds(200, 100, 150, 30);
		this.add(btnClick);
	}
	
	public void addEvents() {
		btnClick.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String name = tfName.getText();
				if(!name.equals("")) {
					JOptionPane.showMessageDialog(getContentPane(), "Hello, " + name, "Welcome", JOptionPane.PLAIN_MESSAGE, new ImageIcon("images\\smile.png"));
				} else {
					JOptionPane.showMessageDialog(getContentPane(), "Please enter your name!", "Welcome", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		WindowListener windowListener = new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				super.windowClosing(e);
				int confirm = JOptionPane.showConfirmDialog(getContentPane(), 
						"Ban thuc su muon thoat?", "Exit", JOptionPane.WARNING_MESSAGE, JOptionPane.YES_NO_OPTION);
				if(confirm == JOptionPane.YES_OPTION) {
					System.out.println("Ket thuc chuong trinh...");
					System.exit(1);
				} else {
					setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
				}
			}
		};
		this.addWindowListener(windowListener);
	}
	
	public static void main(String[] args) {
		GUIThuCong app = new GUIThuCong();
		app.init("My Application");
	}
}
