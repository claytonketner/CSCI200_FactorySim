import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;


public class KitAssemblyManager extends JPanel implements ActionListener{
	private JButton btnKA;
	private JButton btnBreak;
	private JPanel buttonPanel;
	private JPanel mainPanel;
	private CardLayout cardlayout;
	private KitAssemblyGraphics pnlkag;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	public KitAssemblyManager(){
		setLayout( new BorderLayout() );
		cardlayout = new CardLayout();
		mainPanel = new JPanel();
		mainPanel.setLayout(cardlayout);
		btnKA = new JButton("Kit Assembly View");
		//nothing to do now...
		btnBreak = new JButton("Break Panel");
		btnKA.addActionListener(this);
		btnBreak.addActionListener(this);
		pnlkag = new KitAssemblyGraphics();
		buttonPanel = new JPanel();
		buttonPanel.add(btnKA);
		buttonPanel.add(btnBreak);
		mainPanel.add(pnlkag,"kag");	
		add( buttonPanel, BorderLayout.SOUTH );
		add( mainPanel, BorderLayout.CENTER );
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == btnKA){
			cardlayout.first(mainPanel);
			
		}
		if(e.getSource() == btnBreak){
			cardlayout.last(mainPanel);
			
		}
	}
	
	public KitAssemblyGraphics getKitAssemblyGraphics(){
		
		return pnlkag;
	}

}
