package frame;
import java.awt.*;
import javax.swing.*;
import db.ContactDao;
import java.awt.event.*;
import java.sql.*;

public class DeleteContactFrame extends JFrame 
	implements ActionListener {
	private static final long serialVersionUID = 1L;
	JLabel l1 = new JLabel("姓  名：", JLabel.CENTER);
	JTextField tf = new JTextField(15);
	JScrollPane sp = new JScrollPane();
	JLabel msg = new JLabel("", JLabel.CENTER);
	
	JButton b_qry = new JButton("查    询");
	JButton b_del = new JButton("删    除");
	
	//初始化界面
	public DeleteContactFrame() {
		super("删除联系人");
		Container c = this.getContentPane();
		c.setLayout(null);

		JPanel p = new JPanel
		(new FlowLayout(FlowLayout.CENTER, 10, 5));
		p.add(l1);
		p.add(tf);
		p.add(b_qry);
		p.add(b_del);

		sp.setBorder(BorderFactory.
				createTitledBorder("欲删除的联系人"));

		msg.setForeground(Color.RED);

		c.add(p);
		c.add(sp);
		c.add(msg);

		p.setBounds(0, 0, 500, 35);
		sp.setBounds(5, 40, 480, 60);
		msg.setBounds(5, 110, 480, 50);

		this.setBounds(350, 200, 500, 300);
		this.setResizable(false);
		this.setVisible(true);

		// 关闭操作：撤销本窗口，但不退出应用程序
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		// 注册监听器
		b_qry.addActionListener(this);
		b_del.addActionListener(this);
	}

	//按钮 事件处理
	public void actionPerformed(ActionEvent arg0) {
		Object src = arg0.getSource();

		// 查询
		if (src == b_qry) {
			//清空上一轮的反馈信息
			msg.setText("");
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

		// 删除
		if (src == b_del) {
			//获取欲删除联系人的姓名
			String name = "";
			if (tf.getText().trim().equals("")) {
				JOptionPane.showMessageDialog
				(this, "姓名不可为空", "警告",
				JOptionPane.ERROR_MESSAGE);
				return;
			} else {
				name = tf.getText().trim();
			}

			//确认对话框
			int option = JOptionPane.
			showConfirmDialog(null, "确定要删除该联系人吗？",
			"确认删除",JOptionPane.OK_CANCEL_OPTION);

			//在确认对话框选择了“是”，则执行删除，否则不执行删除
			if (option == JOptionPane.OK_OPTION) { // 确定删除
				String sql = "delete from contact" +
						" where name='" + name + "'";
				int result = ContactDao.updateContact(sql);
				if (result == 1) {
					msg.setText("删除成功！");
					sp.setViewportView(null);					
				} else {
					msg.setText("删除失败");
				}
			} else { // 取消删除
				return;
			}
		}
	}
}
