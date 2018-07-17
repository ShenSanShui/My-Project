package frame;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import db.Contact;
import db.ContactDao;

public class InsertContactFrame extends JFrame 
		implements ActionListener {

	private static final long serialVersionUID = 1L;
	JLabel l1 = new JLabel("姓名 *");
	JLabel l2 = new JLabel("性   别");
	JLabel l3 = new JLabel("年   龄");
	JLabel l4 = new JLabel("电   话");
	JLabel l5 = new JLabel(" EMail ");
	JTextField tf1 = new JTextField(15);
	JTextField tf2 = new JTextField(15);
	JTextField tf3 = new JTextField(15);
	JTextField tf4 = new JTextField(15);
	JTextField tf5 = new JTextField(15);
	JLabel msg1 = new JLabel("  注意：带*的必填！");
	JLabel msg2 = new JLabel();
	JButton btnIns = new JButton("添 加");
	JButton btnRes = new JButton("重 置");
	
	// 初始化界面
	public InsertContactFrame() {
		super("添加联系人");
		Container c = this.getContentPane();
		c.setLayout(null);

		JPanel p1 = new JPanel(new GridLayout(1, 2));
		JPanel p2 = new JPanel
			(new FlowLayout(FlowLayout.CENTER, 10, 5));
		JPanel p3 = new JPanel
			(new FlowLayout(FlowLayout.CENTER, 5, 15));

		p1.add(msg1);
		p1.add(msg2);

		p2.add(l1);
		p2.add(tf1);
		p2.add(l2);
		p2.add(tf2);
		p2.add(l3);
		p2.add(tf3);
		p2.add(l4);
		p2.add(tf4);
		p2.add(l5);
		p2.add(tf5);

		p3.add(btnIns);
		p3.add(btnRes);
		
		c.add(p1);
		c.add(p2);
		c.add(p3);

		p1.setBounds(0, 0, 360, 30);
		p2.setBounds(0, 30, 240, 170);
		p3.setBounds(240, 30, 100, 150);

		msg2.setForeground(Color.BLUE);

		this.setBounds(350, 200, 360, 210);		
		this.setResizable(false);
		this.setVisible(true);		

		//修改默认关闭操作为 DISPOSE_ON_CLOSE
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		// 按钮注册监听器
		btnIns.addActionListener(this);
		btnRes.addActionListener(this);
	}

	//按钮事件处理
	public void actionPerformed(ActionEvent arg0) {

		JButton src = (JButton) arg0.getSource();
		
		// 添加
		if (src == btnIns) {
			msg2.setText(""); // 清空上一轮添加的反馈信息
			/*
			 * 取联系人信息，封装成Contact对象 
			 * 将Contact对象插入到数据库
			 */
			Contact aFriend = new Contact();

			// 姓名不可为空			
			String name = null;			
			if (tf1.getText().trim().equals("")) {
				JOptionPane.showMessageDialog
				(this, "带 * 的不可为空！", "警告",
						JOptionPane.ERROR_MESSAGE);
				return;			
			}else{				
				name =  tf1.getText().trim();
			}			

			// 性别只能用 男、女、M、F 表示
			String sex = "";
			if (!tf2.getText().equals("")
					&& !tf2.getText().matches("[男|女|M|F]")) {
				JOptionPane.showMessageDialog(this, "性别必须是：男或女，M或F，请重新输入",
						"警告", JOptionPane.ERROR_MESSAGE);
				tf2.setText("");
				return;
			} else {
				sex = tf2.getText();
			}

			// 年龄必须是整数
			int age = 0;
			if (!tf3.getText().equals("")
					&& !tf3.getText().matches("[0-9]{1,3}")) {
				JOptionPane.showMessageDialog
				(this, "年龄必须是数值，请重新输入", "警告",
						JOptionPane.ERROR_MESSAGE);
				tf3.setText("");
				return;
			} else {
				if (tf3.getText().equals("")) {
					age = 0;
				} else {
					age = Integer.parseInt(tf3.getText());
				}
			}

			// 电话号码必须是数字，且共 8~11位
			String phone = "";
			if (!tf4.getText().equals("")
					&& !tf4.getText().matches("[0-9]{8,11}")) {
				JOptionPane.showMessageDialog(this, "电话号码必须是8~11位数字，请重新输入",
						"警告", JOptionPane.ERROR_MESSAGE);
				tf4.setText("");
				return;
			} else {
				phone = tf4.getText();
			}

			String email = tf5.getText();

			// 根据用户输入的联系人信息，得到1个具体的联系人实例
			aFriend.setName(name);
			aFriend.setSex(sex);
			aFriend.setAge(age);
			aFriend.setPhone(phone);
			aFriend.setEmail(email);

			// 准备 insert 语句
			//调用ContactDao的insertContact()方法实现录入联系人
			String sql = "insert into contact" +
					"(name,sex,age,phone,email) " +
					"values(?,?,?,?,?)";
			int result = ContactDao.insertContact(sql, aFriend);
			
			// 根据insertContact()方法的执行结果，在界面上显示相应的结果信息
			if (result == 1) {
				msg2.setText("添加联系人成功！");
				tf1.setText("可添加新联系人了");
				tf2.setText("");
				tf3.setText("");
				tf4.setText("");
				tf5.setText("");
			} else {
				msg2.setText("添加联系人失败！！！");
				tf1.setText("");
				tf2.setText("");
				tf3.setText("");
				tf4.setText("");
				tf5.setText("");
			}
		}

		// 重置
		if (src == btnRes) {
			tf1.setText("");
			tf2.setText("");
			tf3.setText("");
			tf4.setText("");
			tf5.setText("");
			msg2.setText("");
		}	
	}
}
