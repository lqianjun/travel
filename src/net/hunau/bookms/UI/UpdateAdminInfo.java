package net.hunau.bookms.UI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import net.hunau.bookms.bean.Admin;
import net.hunau.bookms.dao.AdminDAO;
import net.hunau.bookms.dao.UserDAO;

public class UpdateAdminInfo extends JFrame {

	private JPanel contentPane;
	private JTextField C_username;
	private JPasswordField C_password;
	private JPasswordField C_rePassword;
	private JTextField C_userNo;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminDAO ad=new AdminDAO();
					Admin admin=ad.getAdmin("admin", "admin");
					UpdateAdminInfo frame = new UpdateAdminInfo(admin);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public UpdateAdminInfo(Admin admin) {
		setResizable(false);
		setBounds(800, 300, 394, 310);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JLabel label = new JLabel("\u7F16\u8F91\u4FE1\u606F");
		label.setFont(new Font("宋体", Font.PLAIN, 20));
		label.setBounds(163, 13, 89, 18);
		contentPane.add(label);
		
		JLabel lblNewLabel = new JLabel("*\u7528\u6237\u540D\uFF1A");
		lblNewLabel.setBounds(71, 49, 72, 18);
		contentPane.add(lblNewLabel);
		
		JLabel label_1 = new JLabel("*\u5BC6  \u7801\uFF1A");
		label_1.setBounds(71, 84, 72, 18);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("*\u518D\u6B21\u8F93\u5165\uFF1A");
		label_2.setBounds(71, 119, 84, 21);
		contentPane.add(label_2);
		
		C_username = new JTextField();
		C_username.setEditable(false);
		C_username.setBounds(149, 44, 163, 23);
		contentPane.add(C_username);
		C_username.setColumns(10);
		
		C_password = new JPasswordField();
		C_password.setBounds(149, 81, 163, 21);
		contentPane.add(C_password);
		
		C_rePassword = new JPasswordField();
		C_rePassword.setBounds(149, 117, 163, 23);
		contentPane.add(C_rePassword);
		
		JLabel lblid = new JLabel("*\u7BA1\u7406\u5458Id\uFF1A");
		lblid.setBounds(71, 154, 84, 21);
		contentPane.add(lblid);
		
		C_userNo = new JTextField();
		C_userNo.setEditable(false);
		C_userNo.setColumns(10);
		C_userNo.setBounds(149, 152, 163, 23);
		contentPane.add(C_userNo);
		
		//将用户信息输出到文本框中
		C_userNo.setText(admin.getAdminId());
		C_password.setText(admin.getPassword());
		C_rePassword.setText(admin.getPassword());
		C_username.setText(admin.getUsername());
		
		JButton btnNewButton = new JButton("\u63D0\u4EA4");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

/**************************************************【对输入数据进行格式检查start】************************************************************/
			if(C_username.getText().isEmpty()) {		//判断用户名是否为空
				JOptionPane.showMessageDialog(UpdateAdminInfo.this, new String("请输入用户名！"));return;
			}
			if((new String(C_password.getPassword())).isEmpty())
			{
				JOptionPane.showMessageDialog(UpdateAdminInfo.this, new String("请输入密码！"));return;
			}
			if((new String(C_rePassword.getPassword())).isEmpty())
			{
				JOptionPane.showMessageDialog(UpdateAdminInfo.this, new String("请输入二次密码！"));return;
			}
			//判断两次密码是否一致
			if(!(new String(C_password.getPassword())).equals((new String(C_rePassword.getPassword()))))
			{
				JOptionPane.showMessageDialog(null, new String("两次输入密码不一致！"));
				C_password.setText("");
				C_rePassword.setText("");
				return;
				
			}

/**************************************************【对输入数据进行格式检查end】************************************************************/
			AdminDAO ad=new AdminDAO();
			admin.setAdminId(C_userNo.getText().trim());
			admin.setUsername(C_username.getText().trim());
			admin.setPassword(new String(C_password.getPassword()).trim());
			ad.updateAdmin(admin);; 	//将用户添加进数据库
			//查找数据库中密码是否已更新
			if(ad.getAdmin(admin.getUsername(), new String(admin.getPassword()))!=null)
			{
				JOptionPane.showMessageDialog(null, new String("更新成功！"));
			}
			else
			{
				JOptionPane.showMessageDialog(null, new String("数据异常！"));
			}
				
			
		 }
		});
		btnNewButton.setBounds(122, 211, 63, 32);
		contentPane.add(btnNewButton);
		//返回按钮设置
		JButton button = new JButton("\u8FD4\u56DE");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Login().setVisible(true);//跳转至登录界面
				dispose();
			}
		});
		button.setBounds(214, 211, 63, 32);
		contentPane.add(button);
	}

}
