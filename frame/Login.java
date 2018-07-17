package frame;
import javax.swing.*;
import db.ContactDao;
import java.awt.*;
import java.awt.event.*;

public class Login extends JFrame 
			implements ActionListener {
	private static final long serialVersionUID = 1L;

	JLabel lName = new JLabel("登陆账号：");
	JLabel lPw = new JLabel("登陆密码：");
	JTextField tName = new JTextField(15);
	JPasswordField tPw = new JPasswordField(15);
	JButton btnOk = new JButton("登  陆");
	JButton btnExit = new JButton("退  出");

	//初始化界面
	public Login() {
		Container c = this.getContentPane();
		c.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 8));

		c.add(lName);
		c.add(tName);
		c.add(lPw);
		c.add(tPw);
		c.add(btnOk);
		c.add(btnExit);

		// 2个按钮注册监听器
		btnOk.addActionListener(this);
		btnExit.addActionListener(this);

		this.setTitle("身份认证");
		this.setBounds(400, 300, 280, 140);		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	
	//按钮事件处理
	public void actionPerformed(ActionEvent e) {
		//退出按钮
		if (e.getSource() == btnExit) {
			System.exit(0);
		}
		//登陆按钮
		if (e.getSource() == btnOk) {
			// 先获取用户输入的帐户名和密码
			String name = tName.getText();
			char[] pwTmp = tPw.getPassword();
			String pw = new String(pwTmp);
			
			boolean flag = ContactDao.checkUser(name, pw);

			if (flag == true) {				
				this.dispose();
				new MainFrame();	//验证成功，调出主界面
			} else {
				JOptionPane.showMessageDialog(this, 
						"很遗憾，账号或密码错误！", "出错提示",
						JOptionPane.ERROR_MESSAGE);
			}
			// 清空用户之前的输入
			tName.setText("");
			tPw.setText("");
			tName.grabFocus();
		}
	}

	public static void main(String[] args) {
		new Login();
	}
}
