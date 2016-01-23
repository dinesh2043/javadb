package Database;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class GUI extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private DBConnector dbConnector;

	private JPanel mainPanel;

	private JPanel infoPanel;
	private JScrollPane scrollPane;
	private JPanel grid;

	private JPanel buttonPanelForShow;
	private JPanel buttonPanelForSaveShow;
	private JPanel buttonPanelForUpdate;

	private JPanel panelForNameInput;
	private JPanel panelForTable;

	private JPanel cardContent;
	private JPanel cardButton;

	protected static JTextField firstNameText;
	protected static JTextField lastNameText;
	protected static JTextField ageText;

	private JButton saveData;
	private JButton updateData;

	private JButton updateDataGrid;
	private JButton showData;
	private JButton deleteData;
	private JButton goBack;
	private JLabel firstNameLabel;
	private JLabel lastNameLabel;
	private JLabel ageLabel;
	private JLabel infoLabel;
	private int idArray[];
	private String firstNameArray[];
	private String lastNameArray[];
	private String ageArray[];
	private int updateId;

	// private JLabel actionLabel;

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void createGUI() {

		System.out.println("hello");
		// TODO Auto-generated method stub
		Container window = getContentPane();
		window.setLayout(new FlowLayout());
		window.setBackground(Color.BLACK);

		mainPanel = new JPanel();
		mainPanel.setBackground(Color.BLACK);
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
		scrollPane = new JScrollPane(mainPanel);
		scrollPane.setPreferredSize(new Dimension(600, 300));
		// cords layout

		cardContent = new JPanel(new CardLayout());
		cardButton = new JPanel(new CardLayout());

		panelForTable = new JPanel();
		panelForTable.setBackground(Color.white);
		// panelForTable.setPreferredSize(new Dimension(600, 100));

		// panelForTable.setPreferredSize(new Dimension(400,50));
		// panelForTable.setLayout(new GridLayout(1,2));
		// scrollPane = new JScrollPane(panelForTable);

		buttonPanelForSaveShow = new JPanel();
		buttonPanelForSaveShow.setBackground(Color.white);

		buttonPanelForSaveShow.setPreferredSize(new Dimension(600, 70));
		buttonPanelForSaveShow.setLayout(new GridLayout(1, 2));

		buttonPanelForUpdate = new JPanel();
		buttonPanelForUpdate.setPreferredSize(new Dimension(600, 70));
		buttonPanelForUpdate.setLayout(new GridLayout(1, 1));

		buttonPanelForShow = new JPanel();
		buttonPanelForShow.setBackground(Color.white);
		buttonPanelForShow.setPreferredSize(new Dimension(600, 70));
		buttonPanelForShow.setLayout(new GridLayout(1, 1));

		panelForNameInput = new JPanel();
		panelForNameInput.setBackground(Color.white);
		panelForNameInput.setPreferredSize(new Dimension(600, 100));
		panelForNameInput.setLayout(new GridLayout(3, 1));

		infoPanel = new JPanel();
		infoPanel.setBackground(Color.white);
		infoPanel.setPreferredSize(new Dimension(600, 50));

		firstNameLabel = new JLabel("First Name", JLabel.RIGHT);
		lastNameLabel = new JLabel("Last Name", JLabel.RIGHT);

		firstNameText = new JTextField(JTextField.LEFT);
		firstNameText.setPreferredSize(new Dimension(200, 70));
		lastNameText = new JTextField(JTextField.LEFT);
		lastNameText.setPreferredSize(new Dimension(200, 70));
		ageText = new JTextField(JTextField.LEFT);
		ageText.setPreferredSize(new Dimension(200, 70));
		ageLabel = new JLabel("Age", JLabel.RIGHT);

		infoLabel = new JLabel("Personal Information to Save in Database",
				JLabel.CENTER);
		// actionLabel = new JLabel();

		saveData = new JButton("Save Data");
		saveData.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				CardLayout cl = (CardLayout) (cardContent.getLayout());
				cl.show(cardContent, "card1");
				CardLayout c2 = (CardLayout) (cardButton.getLayout());
				c2.show(cardButton, "SaveShow");
				dbConnector = new DBConnector();
				if (!(firstNameText.getText()).isEmpty()
						&& !(lastNameText.getText()).isEmpty()
						&& !(ageText.getText()).isEmpty()) {
					if ((ageText.getText()).matches("[0-9]+")
							&& Integer.parseInt(ageText.getText()) < 130) {
						JOptionPane.showMessageDialog(null, dbConnector.save(
								firstNameText.getText(),
								lastNameText.getText(), ageText.getText()));
						firstNameText.setText("");
						lastNameText.setText("");
						ageText.setText("");
					} else {
						JOptionPane.showMessageDialog(null,
								"Age should be valid value");
					}
				} else {
					JOptionPane.showMessageDialog(null,
							"All of the fields are required fields");
				}

			}
		});
		updateData = new JButton("Update Data");
		updateData.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				dbConnector = new DBConnector();
				if (!(firstNameText.getText()).isEmpty()
						&& !(lastNameText.getText()).isEmpty()
						&& !(ageText.getText()).isEmpty()) {
					if ((ageText.getText()).matches("[0-9]+")
							&& Integer.parseInt(ageText.getText()) < 130) {
						JOptionPane.showMessageDialog(null, dbConnector.update(
								firstNameText.getText(),
								lastNameText.getText(), ageText.getText(),
								updateId));
						firstNameText.setText("");
						lastNameText.setText("");
						ageText.setText("");
						CardLayout cl = (CardLayout) (cardContent.getLayout());
						cl.show(cardContent, "card1");
						CardLayout c2 = (CardLayout) (cardButton.getLayout());
						c2.show(cardButton, "SaveShow");
						panelForTable.remove(grid);
					} else {
						JOptionPane.showMessageDialog(null,
								"Age should be valid value");
					}
				} else {
					JOptionPane.showMessageDialog(null,
							"All of the fields are required fields");
				}

			}
		});
		goBack = new JButton("Go Back");
		goBack.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("Go Back");
				CardLayout cl = (CardLayout) (cardContent.getLayout());
				cl.show(cardContent, "card1");
				CardLayout c2 = (CardLayout) (cardButton.getLayout());
				c2.show(cardButton, "SaveShow");
				panelForTable.remove(grid);

			}
		});

		showData = new JButton("Show Data");
		showData.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dbConnector = new DBConnector();
				CardLayout cl = (CardLayout) (cardContent.getLayout());
				cl.show(cardContent, "card2");
				CardLayout c2 = (CardLayout) (cardButton.getLayout());
				c2.show(cardButton, "Show");
				ArrayList<Contact> contactData = new ArrayList<Contact>();
				contactData = dbConnector.show();
				crateTableHader(contactData.size());
				idArray = new int[contactData.size()];
				firstNameArray = new String[contactData.size()];
				lastNameArray = new String[contactData.size()];
				ageArray = new String[contactData.size()];
				System.out.println("Show all Data");
				int i = 0;
				for (Contact contact : dbConnector.show()) {
					fillTable(contact, i);
					i++;
					// System.out.println(contact.id);
					// System.out.println(contact.firstName);
					// System.out.println(contact.lastName);
					// System.out.println(contact.age);
				}

				panelForTable.add(grid);
				// scrollPane.add(panelForTable);

			}

		}

		);

		// updateData = new JButton("Update Data");
		// deleteData = new JButton("Delete Data");
		// goBack = new JButton("Go Back");

		window.add(scrollPane);
		mainPanel.add(infoPanel);
		mainPanel.add(cardContent);
		mainPanel.add(cardButton);

		// scrollPane = new JScrollPane(mainPanel);

		cardContent.add(panelForNameInput, "card1");
		cardContent.add(panelForTable, "card2");

		cardButton.add(buttonPanelForSaveShow, "SaveShow");
		cardButton.add(buttonPanelForShow, "Show");
		cardButton.add(buttonPanelForUpdate, "Update");
		// panelForTable.hide();

		infoPanel.add(infoLabel);
		// infoPanel.add(actionLabel);

		buttonPanelForSaveShow.add(saveData);
		buttonPanelForSaveShow.add(showData);

		buttonPanelForUpdate.add(updateData);

		buttonPanelForShow.add(goBack);
		// buttonPanelForSaveShow.add(deleteData);

		panelForNameInput.add(firstNameLabel);
		panelForNameInput.add(firstNameText);
		// panelForNameInput.add(firstNameRead);

		panelForNameInput.add(lastNameLabel);
		panelForNameInput.add(lastNameText);
		// panelForNameInput.add(lastNameRead);

		panelForNameInput.add(ageLabel);
		panelForNameInput.add(ageText);

	}

	// label to create panelForTable
	JLabel Id;
	JLabel Age;
	JLabel firstName;
	JLabel lastName;
	JLabel update;
	JLabel delete;

	public void crateTableHader(int i) {
		Id = new JLabel("ID", JLabel.LEFT);
		Age = new JLabel("Age", JLabel.LEFT);
		firstName = new JLabel("First Name", JLabel.LEFT);
		lastName = new JLabel("Last Name", JLabel.LEFT);
		update = new JLabel("Update", JLabel.LEFT);
		delete = new JLabel("Delete", JLabel.LEFT);
		grid = new JPanel(new GridLayout(i + 1, 5));
		grid.add(Id);

		grid.add(firstName);
		grid.add(lastName);
		grid.add(Age);
		grid.add(update);
		grid.add(delete);
	}

	public void fillTable(Contact contact, int i) {

		idArray[i] = contact.id;
		firstNameArray[i] = contact.firstName;
		lastNameArray[i] = contact.lastName;
		ageArray[i] = contact.age;
		Id = new JLabel();
		firstName = new JLabel();
		lastName = new JLabel();
		Age = new JLabel();
		// updateData = new JButton("Update");
		updateDataGrid = new JButton("Update");
		updateDataGrid.setName("" + i);
		updateDataGrid.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JButton u = (JButton) e.getSource();
				String name = u.getName();
				firstNameText.setText(firstNameArray[Integer.parseInt(name)]);
				lastNameText.setText(lastNameArray[Integer.parseInt(name)]);
				ageText.setText(ageArray[Integer.parseInt(name)]);
				updateId = idArray[Integer.parseInt(name)];
				System.out.println("Hello update" + Integer.parseInt(name));

				CardLayout cl = (CardLayout) (cardContent.getLayout());
				cl.show(cardContent, "card1");
				CardLayout c2 = (CardLayout) (cardButton.getLayout());
				c2.show(cardButton, "Update");
				panelForTable.remove(grid);
			}

		});

		deleteData = new JButton("Delete");
		deleteData.setName("" + i);
		deleteData.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JButton d = (JButton) e.getSource();
				String name = d.getName();
				JOptionPane.showMessageDialog(null,
						dbConnector.delete(idArray[Integer.parseInt(name)]));
				CardLayout cl = (CardLayout) (cardContent.getLayout());
				cl.show(cardContent, "card1");
				CardLayout c2 = (CardLayout) (cardButton.getLayout());
				c2.show(cardButton, "SaveShow");
				System.out.println("Hello Delete" + Integer.parseInt(name));
				panelForTable.remove(grid);
			}

		});

		Id.setText(String.valueOf(contact.id));

		firstName.setText(contact.firstName);
		lastName.setText(contact.lastName);
		Age.setText(contact.age);
		grid.add(Id);

		grid.add(firstName);
		grid.add(lastName);
		grid.add(Age);
		grid.add(updateDataGrid);
		grid.add(deleteData);

	}

}
