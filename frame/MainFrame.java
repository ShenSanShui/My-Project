package frame;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MainFrame extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	
	JMenuBar menubar = new JMenuBar();
	JMenu m_contactManager = new JMenu("联系人管理");
	JMenu m_systemManager = new JMenu("系统管理");
	JMenuItem mi_query = new JMenuItem("检索联系人");
	JMenuItem mi_insert = new JMenuItem("添加联系人");
	JMenuItem mi_modify = new JMenuItem("修改联系人");
	JMenuItem mi_delete = new JMenuItem("删除联系人");
	JMenuItem mi_exit = new JMenuItem("退出系统");
	
	JToolBar toolbar = new JToolBar();
	JButton b_qry = new JButton(new ImageIcon("imgs/search.png"));
	JButton b_ins = new JButton(new ImageIcon("imgs/input.png"));
	JButton b_upd = new JButton(new ImageIcon("imgs/update.jpg"));
	JButton b_del = new JButton(new ImageIcon("imgs/delete.png"));
	JButton b_exit = new JButton(new ImageIcon("imgs/exit.jpg"));
	JLabel l_img = new JLabel(new ImageIcon("imgs/img.jpg"));

	//初始化界面
	public MainFrame() {
		super("联系人管理");
		this.buildMenuBar();
		this.setJMenuBar(menubar);		
		this.buildToolBar();		
		Container c = this.getContentPane();		
		c.add(toolbar, BorderLayout.NORTH);
		c.add(l_img, BorderLayout.CENTER);
		this.setBounds(200,100,500, 400);
		this.setVisible(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// 设置菜单项快捷键
		mi_query.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q,
				InputEvent.CTRL_MASK, false));
		mi_insert.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I,
				InputEvent.CTRL_MASK, false));
		mi_modify.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U,
				InputEvent.CTRL_MASK, false));
		mi_delete.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D,
				InputEvent.CTRL_MASK, false));
		mi_exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,
				InputEvent.CTRL_MASK, false));

		//注册监听器
		mi_query.addActionListener(this);
		mi_insert.addActionListener(this);
		mi_modify.addActionListener(this);
		mi_delete.addActionListener(this);
		mi_exit.addActionListener(this);
		
		b_qry.addActionListener(this);
		b_ins.addActionListener(this);
		b_upd.addActionListener(this);
		b_del.addActionListener(this);
		b_exit.addActionListener(this);

	}

	// 创建菜单
	void buildMenuBar() {
		menubar.add(m_contactManager);
		menubar.add(m_systemManager);
		m_contactManager.add(mi_query);
		m_contactManager.add(mi_insert);
		m_contactManager.add(mi_modify);
		m_contactManager.add(mi_delete);
		m_systemManager.add(mi_exit);
	}
	
	//创建工具栏
	void buildToolBar() {
		toolbar.setFloatable(false);
		toolbar.add(b_qry);
		toolbar.addSeparator();
		toolbar.add(b_ins);
		toolbar.add(b_upd);
		toolbar.add(b_del);
		toolbar.addSeparator();
		toolbar.add(b_exit);
		b_qry.setToolTipText("检索联系人");
		b_ins.setToolTipText("添加联系人");
		b_upd.setToolTipText("修改联系人");
		b_del.setToolTipText("删除联系人");
		b_exit.setToolTipText("退出系统");
	}

	//事件处理
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();
		
		//退出
		if (src == mi_exit  || src==b_exit) {
			System.exit(0);
		}
		//检索
		if (src == mi_query || src==b_qry) {
			 new QueryContactFrame();
		}
		//添加
		if (src == mi_insert || src==b_ins) {
			new InsertContactFrame();
		}		
		//修改
		if (src == mi_modify || src==b_upd) {
			new ModifyContactFrame();
		}
		//删除
		if (src == mi_delete || src==b_del) {			
			new DeleteContactFrame();
		}
	}
}
