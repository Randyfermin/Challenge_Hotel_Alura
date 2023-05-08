package views;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import Utils.NumberRenderer;
import dao.HuespedDAO;
import dao.ReservasDAO;
import factory.ConnectionFactory;
import modelo.Huesped;
import modelo.Reservas;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTabbedPane;
import java.awt.Toolkit;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.PatternSyntaxException;

import javax.swing.JCheckBox;

@SuppressWarnings("serial")
public class Busqueda_Todos_Campos extends JFrame {

	private JPanel contentPane;
	private JTextField txtBuscar;
	private JTable tbHuespedes;
	private JTable tbReservas;
	private DefaultTableModel modelo;
	private DefaultTableModel modeloHuesped;
	private JLabel labelAtras;
	private JLabel labelExit;
	private JPanel OpcionesFiltroPanel  = new JPanel();
	int xMouse, yMouse;
	private int[] ActivarColumna = {7,7,7,7,7,7,7};
	private Huesped huesped = new Huesped();
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Busqueda_Todos_Campos frame = new Busqueda_Todos_Campos();
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
	public Busqueda_Todos_Campos() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Busqueda_Todos_Campos.class.getResource("/imagenes/lupa2.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 910, 571);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setUndecorated(true);
		
		txtBuscar = new JTextField();
		txtBuscar.setBounds(170, 123, 502, 28);
		txtBuscar.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPane.add(txtBuscar);
		txtBuscar.setColumns(10);
				
		JLabel lblNewLabel_4 = new JLabel("SISTEMA DE BÚSQUEDA");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setForeground(new Color(12, 138, 199));
		lblNewLabel_4.setFont(new Font("Roboto Black", Font.BOLD, 24));
		lblNewLabel_4.setBounds(170, 62, 715, 42);
		contentPane.add(lblNewLabel_4);
		
		JTabbedPane panel = new JTabbedPane(JTabbedPane.TOP);
		panel.setBackground(new Color(12, 138, 199));
		panel.setFont(new Font("Roboto", Font.PLAIN, 16));
		panel.setBounds(20, 215, 865, 282);
		contentPane.add(panel);
		
		String[] ReservascolumnNames = {"Código de Reserva",
				"Fecha Check In",
				"Fecha Check Out",
				"Valor",
				"Forma de Pago"};
		
		tbReservas = new JTable()
		{
			private static final long serialVersionUID = 1L;

	        public boolean isCellEditable(int row, int column) {                
	                return false;               
	        };
	        
		};
		
		tbReservas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbReservas.setFont(new Font("Roboto", Font.PLAIN, 16));
		
		modelo = (DefaultTableModel) tbReservas.getModel();
		
		for (String name : ReservascolumnNames)
    	{
			modelo.addColumn(name);
    	}
		
		
		TableColumnModel modelColtbReservas = tbReservas.getColumnModel();
		modelColtbReservas.getColumn(3).setCellRenderer(NumberRenderer.getCurrencyRenderer());
		
		
		JScrollPane scroll_table = new JScrollPane(tbReservas);
		panel.addTab("Reservas", new ImageIcon(Busqueda_Todos_Campos.class.getResource("/imagenes/reservado.png")), scroll_table, null);
		scroll_table.setVisible(true);
		
		int[] ReservascolumnWith = {130,200,200,140,220};
		
		for (int i = 0; i < 5; i++) {
			
			tbReservas.getColumn(ReservascolumnNames[i]).setPreferredWidth(ReservascolumnWith[i]);
			tbReservas.getColumn(ReservascolumnNames[i]).setMinWidth(ReservascolumnWith[i]);
			tbReservas.getColumn(ReservascolumnNames[i]).setMaxWidth(ReservascolumnWith[i]);	
		}
		
		ReservasDAO reservasDAO = new ReservasDAO(new ConnectionFactory().recuperaConexion());
		var reservas = reservasDAO.getReservasList();
		
		modelo = (DefaultTableModel) tbReservas.getModel();
		
		reservas.forEach(reserva -> modelo.addRow(new Object[] { 	
													reserva.getLocalizaReserva(), 
													reserva.getFechaEntrada(), 
													reserva.getFechaSalida(), 
													reserva.getValor(), 
													reserva.getFormaPago()}));
		
		String[] HuespedescolumnNames = {"Código de Huesped",
				"Nombre",
				"Apellido",
				"Fecha de Nacimiento",
				"Nacionalidad",
				"Teléfono",
				"Código de Reserva"};
		
		tbHuespedes = new JTable()
		{
			private static final long serialVersionUID = 1L;

	        public boolean isCellEditable(int row, int column) {                
	                return false;               
	        };
		};
		
		tbHuespedes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbHuespedes.setFont(new Font("Roboto", Font.PLAIN, 16));
				
		modeloHuesped = (DefaultTableModel) tbHuespedes.getModel();
		for (String name : HuespedescolumnNames)
    	{
			modeloHuesped.addColumn(name);
    	}

		
		
		JScrollPane scroll_tableHuespedes = new JScrollPane(tbHuespedes);
		panel.addTab("Huéspedes", new ImageIcon(Busqueda_Todos_Campos.class.getResource("/imagenes/pessoas.png")), scroll_tableHuespedes, null);
		scroll_tableHuespedes.setVisible(true);
		
		int[] HuespedescolumnWith = {120,125,125,120,140,100,125};
		
		for (int i = 0; i < 7; i++) {
			
			tbHuespedes.getColumn(HuespedescolumnNames[i]).setPreferredWidth(HuespedescolumnWith[i]);
			tbHuespedes.getColumn(HuespedescolumnNames[i]).setMinWidth(HuespedescolumnWith[i]);
			tbHuespedes.getColumn(HuespedescolumnNames[i]).setMaxWidth(HuespedescolumnWith[i]);
		}
			
		HuespedDAO huespedDAO = new HuespedDAO(new ConnectionFactory().recuperaConexion());
		
		var huespedes = huespedDAO.getHuespedesList();
		
		modeloHuesped = (DefaultTableModel) tbHuespedes.getModel();
		
		huespedes.forEach(huesped -> modeloHuesped.addRow(new Object[] { 	
										huesped.getId(), 
										huesped.getNombre(), 
										huesped.getApellido(), 
										huesped.getFechaNacimiento(), 
										huesped.getNacionalidad(),
										huesped.getTelefono(),
										huesped.getLocalizadorReserva()}));
		
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(Busqueda_Todos_Campos.class.getResource("/imagenes/Ha-100px.png")));
		lblNewLabel_2.setBounds(20, 51, 104, 107);
		contentPane.add(lblNewLabel_2);
		
		JPanel header = new JPanel();
		header.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				headerMouseDragged(e);
			     
			}
		});
		header.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				headerMousePressed(e);
			}
		});
		header.setLayout(null);
		header.setBackground(Color.WHITE);
		header.setBounds(0, 0, 910, 36);
		contentPane.add(header);
		
		JPanel btnAtras = new JPanel();
		btnAtras.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				btnAtras.setBackground(new Color(12, 138, 199));
				labelAtras.setForeground(Color.white);
			}			
			@Override
			public void mouseExited(MouseEvent e) {
				 btnAtras.setBackground(Color.white);
			     labelAtras.setForeground(Color.black);
			}
		});
		btnAtras.setLayout(null);
		btnAtras.setBackground(Color.WHITE);
		btnAtras.setBounds(0, 0, 53, 36);
		header.add(btnAtras);
		
		labelAtras = new JLabel("<");
		labelAtras.setHorizontalAlignment(SwingConstants.CENTER);
		labelAtras.setFont(new Font("Roboto", Font.PLAIN, 23));
		labelAtras.setBounds(0, 0, 53, 36);
		btnAtras.add(labelAtras);
		
		JPanel btnexit = new JPanel();
		btnexit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();
			}
			@Override
			public void mouseEntered(MouseEvent e) { //Al usuario pasar el mouse por el botón este cambiará de color
				btnexit.setBackground(Color.red);
				labelExit.setForeground(Color.white);
			}			
			@Override
			public void mouseExited(MouseEvent e) { //Al usuario quitar el mouse por el botón este volverá al estado original
				 btnexit.setBackground(Color.white);
			     labelExit.setForeground(Color.black);
			}
		});
		btnexit.setLayout(null);
		btnexit.setBackground(Color.WHITE);
		btnexit.setBounds(857, 0, 53, 36);
		header.add(btnexit);
		
		labelExit = new JLabel("X");
		labelExit.setHorizontalAlignment(SwingConstants.CENTER);
		labelExit.setForeground(Color.BLACK);
		labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));
		labelExit.setBounds(0, 0, 53, 36);
		btnexit.add(labelExit);
		
		JSeparator separator_1_2 = new JSeparator();
		separator_1_2.setForeground(new Color(12, 138, 199));
		separator_1_2.setBackground(new Color(12, 138, 199));
		separator_1_2.setBounds(170, 156, 502, 2);
		contentPane.add(separator_1_2);
		
		JPanel btnEditar = new JPanel();
		btnEditar.setLayout(null);
		btnEditar.setBackground(new Color(12, 138, 199));
		btnEditar.setBounds(635, 508, 122, 35);
		btnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		btnEditar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				DefaultTableModel model = (DefaultTableModel) tbHuespedes.getModel();
		        int row = tbHuespedes.getSelectedRow();
		        if (row >= 0)
		        {
		        	if (panel.getSelectedIndex() == 0)
	                {
	                	
	                }
	                else
	                {
			        		huesped.setId(Integer.valueOf(model.getValueAt(tbHuespedes.convertRowIndexToModel(row), 0).toString()));
							huesped.setNombre(model.getValueAt(tbHuespedes.convertRowIndexToModel(row), 1).toString());
							huesped.setApellido(model.getValueAt(tbHuespedes.convertRowIndexToModel(row), 2).toString());
							
							String vFechaNac = model.getValueAt(tbHuespedes.convertRowIndexToModel(row), 3).toString();
							
							huesped.setFechaNacimiento(Date.valueOf(vFechaNac));
							
							huesped.setNacionalidad(model.getValueAt(tbHuespedes.convertRowIndexToModel(row), 4).toString());
							huesped.setTelefono(model.getValueAt(tbHuespedes.convertRowIndexToModel(row), 5).toString());
											
							EditorHuesped editorHuesped = new EditorHuesped();
							editorHuesped.setHuesped(huesped);
							editorHuesped.setVisible(true);
							dispose();
	                }
		        	
							
		        }	
			}
		});
		contentPane.add(btnEditar);
		
		JLabel lblEditar = new JLabel("EDITAR");
		lblEditar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEditar.setForeground(Color.WHITE);
		lblEditar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblEditar.setBounds(0, 0, 122, 35);
		btnEditar.add(lblEditar);
		
		JPanel btnEliminar = new JPanel();
		btnEliminar.setLayout(null);
		btnEliminar.setBackground(new Color(12, 138, 199));
		btnEliminar.setBounds(767, 508, 122, 35);
		btnEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		btnEliminar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				DefaultTableModel model = (DefaultTableModel) tbHuespedes.getModel();
		        int row = tbHuespedes.getSelectedRow();
		        if (row >= 0)
		        {
		        	if (panel.getSelectedIndex() == 0)
	                {
	                	
	                }
	                else
	                {
	                	huesped.setId(Integer.valueOf(model.getValueAt(tbHuespedes.convertRowIndexToModel(row), 0).toString()));
	                	HuespedDAO huespedDAO = new HuespedDAO(new ConnectionFactory().recuperaConexion());
	                	huespedDAO.DesabilitarHuesped(huesped);
	                	
	                	Exito exito = new Exito(Busqueda_Todos_Campos.this);
						exito.setVisible(true);
			            dispose();	
	                	
	                }		
		        }	
			}
		});
		contentPane.add(btnEliminar);
		
		JLabel lblEliminar = new JLabel("ELIMINAR");
		lblEliminar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEliminar.setForeground(Color.WHITE);
		lblEliminar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblEliminar.setBounds(0, 0, 122, 35);
		btnEliminar.add(lblEliminar);
		
		setResizable(false);
		
		panel.addChangeListener(new ChangeListener()
		{

			@Override
			public void stateChanged(ChangeEvent e) {
				removeComponenets();
				CargarOpcionesFiltro((panel.getSelectedIndex() == 0)? ReservascolumnNames: HuespedescolumnNames);
				txtBuscar.setText("");
			}
			
		});
		
		
		//==========UTILIZAR UN FILTRO COMO UNA BUSQUEDA. 
		
		txtBuscar.getDocument().addDocumentListener(new DocumentListener() {
			  @Override
			  public void changedUpdate(DocumentEvent e) {
				  if(filtroActivado())
					  actualizarTablaViews();
			  }
			  @Override
			  public void removeUpdate(DocumentEvent e) {
				  if(filtroActivado())
					  actualizarTablaViews();
			  }
			  @Override
			  public void insertUpdate(DocumentEvent e) {
				  if(filtroActivado())
					  actualizarTablaViews();
			  }

			  private void actualizarTablaViews() {
		        
		    	TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(((DefaultTableModel) ((panel.getSelectedIndex() == 0)? tbReservas.getModel(): tbHuespedes.getModel()))); 
	                RowFilter<TableModel, Object> rf = null;
	                List<RowFilter<Object,Object>> rfs = 
	                new ArrayList<RowFilter<Object,Object>>();

	                try {
	                    String text = txtBuscar.getText();
	                    String[] textArray = text.split(" ");

	                    for (int i = 0; i < textArray.length; i++) {
	                        
	                    	rfs.add(RowFilter.regexFilter("(?i)" + textArray[i], ActivarColumna[0], 
	                    															ActivarColumna[1],
	                    																ActivarColumna[2], 
	                    																	ActivarColumna[3], 
	                    																		ActivarColumna[4], 
	                    																			ActivarColumna[5], 
	                    																				ActivarColumna[6]));
	                    	
	                    }

	                    rf = RowFilter.andFilter(rfs);

	                } catch (PatternSyntaxException e) {
	                    return;
	                }

	                sorter.setRowFilter(rf);
	                if (panel.getSelectedIndex() == 0)
	                {
	                	tbReservas.setRowSorter(sorter);
	                }
	                else
	                {
	                	tbHuespedes.setRowSorter(sorter);
	                }
			    }
			});
		
		CargarOpcionesFiltro(ReservascolumnNames);
	}
		
		//Código que permite mover la ventana por la pantalla según la posición de "x" y "y"
	 	private void headerMousePressed(java.awt.event.MouseEvent evt) {
	        xMouse = evt.getX();
	        yMouse = evt.getY();
	    }

	    private void headerMouseDragged(java.awt.event.MouseEvent evt) {
	        int x = evt.getXOnScreen();
	        int y = evt.getYOnScreen();
	        this.setLocation(x - xMouse, y - yMouse);
	    }
	    
	    private void CargarOpcionesFiltro(String[] Opciones) {
	    	
	    	OpcionesFiltroPanel.setBounds(20, 169, 865, 35);
			contentPane.add(OpcionesFiltroPanel);
			
			
			int i = 0;
	    	for (String element : Opciones)
	    	{
	    		JCheckBox jchechBox = new JCheckBox(element);
	    		jchechBox.setName("jchechBox_"+i);
	    		
	    		jchechBox.addItemListener(new ItemListener() {

	                @Override
	                public void itemStateChanged(ItemEvent e) {
	                	int posicion = Integer.parseInt(jchechBox.getName().substring(10)); 
	                    ActivarColumna[posicion] = (e.getStateChange() == ItemEvent.SELECTED ? posicion : 7);
	                }
	            });	
	    		OpcionesFiltroPanel.add(jchechBox);
	    		i++;
		    }	    	
		}
	    private void removeComponenets() {
	    	//Get the components in the panel
	    	Component[] componentList = OpcionesFiltroPanel.getComponents();

	    	//Loop through the components
	    	for(Component c : componentList){

	    	    //Find the components you want to remove
	    	    if(c instanceof JCheckBox){

	    	        //Remove it
	    	    	OpcionesFiltroPanel.remove(c);
	    	    }
	    	}

	    	//IMPORTANT
	    	OpcionesFiltroPanel.revalidate();
	    	OpcionesFiltroPanel.repaint();
	    }
	    
	    private boolean filtroActivado() {
	    	boolean vReturn = false;
	    	//Get the components in the panel
	    	Component[] componentList = OpcionesFiltroPanel.getComponents();

	    	//Loop through the components
	    	for(Component c : componentList){

	    	    //Find the components you want to remove
	    	    if(c instanceof JCheckBox){

	    	        if (((JCheckBox) c).isSelected())
	    	        {
	    	        	vReturn = true;
	    	        	break;
	    	        }
	    	    }
	    	}
	    	return vReturn;
	    }
}
