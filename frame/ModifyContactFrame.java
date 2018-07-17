package frame;
import javax.swing.*;
import db.ContactDao;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

class ModifyContactFrame extends JFrame 
		implements ActionListener {	

	private static final long serialVersionUID = 1L;
	JLabel l1 = new JLabel("姓  名：", JLabel.CENTER);
	JLabel l2 = new JLabel("更新性别  ", JLabel.RIGHT);
	JLabel l3 = new JLabel("更新年龄  ", JLabel.RIGHT);
	JLabel l4 = new JLabel("更新电话  ", JLabel.RIGHT);
	JLabel l5 = new JLabel("更新Email ", JLabel.RIGHT);
	JTextField tf = new JTextField(15);
	JTextField tf_sex = new JTextField(15);
	JTextField tf_age = new JTextField(15);
	JTextField tf_phone = new JTextField(15);
	JTextField tf_email = new JTextField(15);
	JScrollPane sp = new JScrollPane();
	JButton b_qry = new JButton("查    询");
	JButton b_upd = new JButton("更    新");
	JButton b_res = new JButton("重    置");

	//初始化界面
	ModifyContactFrame() {
		super("更新联系人");
		Container c = this.getContentPane();
		c.setLayout(null);
		
		JPanel p1 = new JPanel(
				new FlowLayout(FlowLayout.CENTER, 10, 5));
		p1.add(l1);
		p1.add(tf);
		p1.add(b_qry);

		JPanel p2 = new JPanel(new GridLayout(4, 2, 10, 3));
		p2.add(l2);
		p2.add(tf_sex);
		p2.add(l3);
		p2.add(tf_age);
		p2.add(l4);
		p2.add(tf_phone);
		p2.add(l5);
		p2.add(tf_email);

		JPanel p3 = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
		p3.add(b_upd);
		p3.add(b_res);
		
		sp.setBorder(BorderFactory.createTitledBorder("欲更新的联系人"));
		
		c.add(p1);
		c.add(p2);
		c.add(sp);
		c.add(p3);

		p1.setBounds(0, 0, 500, 35);
		sp.setBounds(5, 40, 480, 60);
		p2.setBounds(50, 110, 300, 120);
		p3.setBounds(150, 230, 200, 40);

		this.setBounds(300, 200, 500, 400);
		this.setResizable(false);
		this.setVisible(true);

		// 关闭操作：撤销本窗口，但不退出应用程序
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		// 注册监听器
		b_qry.addActionListener(this);
		b_upd.addActionListener(this);

		// 重置按钮事件处理
		b_res.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tf.setText("");
				tf_sex.setText("");
				tf_age.setText("");
				tf_phone.setText("");
				tf_email.setText("");
				sp.setViewportView(null);
			}
		});

	}

	//查询 、更新按钮 事件处理
	public void actionPerformed(ActionEvent arg0) {
		Object src = arg0.getSource();

		// 查询
		if (src == b_qry) {
			// 清空上一轮更新文本框中的内容
			tf_sex.setText("");
			tf_age.setText("");
			tf_phone.setText("");
			tf_email.setText("");

			// 获取查询的联系人姓名
			String name = "";
			if (tf.getText().trim().equals("")) {
				JOptionPane.showMessageDialog(this, "姓名不可为空", "警告",
						JOptionPane.ERROR_MESSAGE);
				return;
			} else {
				name = tf.getText().trim();
			}

			// 查询欲更新的联系人信息
			ResultSet rt = ContactDao.getContactByName(name);
			ResultSetTableModel rstm = new ResultSetTableModel(rt);
			JTable tb = new JTable(rstm);
			sp.setViewportView(tb);
		}

		// 更新
		if (src == b_upd) {
			String name = "";
			if (tf.getText().trim().equals("")) {
				JOptionPane.showMessageDialog(this, "姓名不可为空", "警告",
						JOptionPane.ERROR_MESSAGE);
				return;
			} else {
				name = tf.getText().trim();
			}
			String sql = "";
			String newSex = "";
			int newAge = 0;
			String newPhone = "";
			String newEmail = "";

			// 若性别非空，则更新性别
			if (!tf_sex.getText().equals("")) {

				if (!tf_sex.getText().matches("[男|女|M|F]")) {
					JOptionPane.showMessageDialog
					(this, "性别必须是：男或女，M或F，请重新输入",
							"警告", JOptionPane.ERROR_MESSAGE);
					tf_sex.setText("");
				} else {
					newSex = tf_sex.getText();
					sql = "update contact set sex='" + newSex
							+ "' where name='" + name + "'";
					ContactDao.updateContact(sql);
				}
			}

			// 若年龄非空，则更新年龄
			if (!tf_age.getText().equals("")) {
				if (!tf_age.getText().matches("[0-9]{1,3}")) {
					JOptionPane.showMessageDialog
					(this, "年龄必须是数值，请重新输入", "警告",
							JOptionPane.ERROR_MESSAGE);
					tf_age.setText("");
				} else {
					if (tf_age.getText().equals("")) {
						newAge = 0;
					} else {
						newAge = Integer.parseInt(tf_age.getText());
					}
					sql = "update contact set age=" 
						+ newAge + " where name='"
							+ name + "'";
					ContactDao.updateContact(sql);
				}

			}

			// 若电话非空，则更新电话
			if (!tf_phone.getText().equals("")) {

				if (!tf_phone.getText().matches("[0-9]{8,11}")) {
					JOptionPane.showMessageDialog
					(this, "电话号码必须是8~11位数字，请重新输入",
							"警告", JOptionPane.ERROR_MESSAGE);
					tf_phone.setText("");

				} else {
					newPhone = tf_phone.getText();
					sql = "update contact set phone='" + newPhone
							+ "' where name='" + name + "'";
					ContactDao.updateContact(sql);
				}
			}

			// 若email非空，则更新email
			if (!tf_email.getText().equals("")) {

				newEmail = tf_email.getText();
				sql = "update contact set email='" + newEmail
						+ "' where name='" + name + "'";
				ContactDao.updateContact(sql);
			}

			// 更新结束后，重新检索该联系人，显示更新后的信息
			ResultSet rt = ContactDao.getContactByName(name);
			ResultSetTableModel rstm = new ResultSetTableModel(rt);
			JTable tb = new JTable(rstm);
			sp.setViewportView(tb);
			
			tf_sex.setText("");
			tf_age.setText("");
			tf_phone.setText("");
			tf_email.setText("");
		}
	}
}
