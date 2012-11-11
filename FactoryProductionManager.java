import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;


public class FactoryProductionManager extends JPanel implements ActionListener{
	private CardLayout cardlayout = new CardLayout();
	private FactoryProductionSchedulePanel fpsp;
	private FactoryProductioButtonPanel fpbp;
	private FactoryProductionViewPanel fpvp;
	private JPanel mainpanel;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	public FactoryProductionManager(){
		setLayout(new BorderLayout());
		mainpanel = new JPanel();
		mainpanel.setLayout(cardlayout);
		fpsp = new FactoryProductionSchedulePanel();
		fpbp = new FactoryProductioButtonPanel();
		fpvp = new FactoryProductionViewPanel();
		mainpanel.add(fpsp,"fpsp");
		mainpanel.add(fpvp,"fpvbp");
		fpbp.btnSwitchSchedule.addActionListener(this);
		fpbp.btnSwitchView.addActionListener(this);
		add(mainpanel,BorderLayout.CENTER);
		add(fpbp,BorderLayout.SOUTH);
		
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == fpbp.btnSwitchSchedule){
			cardlayout.first(mainpanel);
			
		}
		if(e.getSource() == fpbp.btnSwitchView){
			cardlayout.last(mainpanel);
			
		}
	}

}
