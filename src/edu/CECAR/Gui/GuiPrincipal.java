/**
 * Clase: GuiPrincipal.java
 *
 * Version: 1.0
 *
 * Fecha Creacion: 26/08/2014
 *
 * Fecha Modificacion:
 *
 * Autor: Carlos Tuiran
 *
 * Copyrigth: CECAR 
 */

package edu.CECAR.Gui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Properties;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import edu.CECAR.Logica.Analizar;
import edu.CECAR.Logica.Atributo;
import edu.CECAR.Logica.Buscar;
import edu.CECAR.Logica.Palabra;

public final class GuiPrincipal extends JFrame {

	private JPanel jpContenedor;
	private JTextField jtPalabra;
	private JTextField jtValor;
	private JTextArea jtaLista;
	private JTextField jtUbicacion;
	private JTextField jtUbicacionAnalizar;
	private JRadioButton jrAmbas;
	private JRadioButton jrAutor;
	private JRadioButton jrFecha;
	private JTextArea jtaPerfiles;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GuiPrincipal frame = new GuiPrincipal();
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
	public GuiPrincipal() {
		setTitle("OADA (Organizador de Archivos y Detector de Amenazas)");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 342);
		jpContenedor = new JPanel();
		jpContenedor.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(jpContenedor);
		jpContenedor.setLayout(null);


		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 11, 414, 282);
		jpContenedor.add(tabbedPane);

		JPanel panel = new JPanel();
		tabbedPane.addTab("Organizar", null, panel, null);
		panel.setLayout(null);

		jtUbicacion = new JTextField();
		jtUbicacion.setEditable(false);
		jtUbicacion.setBounds(127, 10, 272, 20);
		panel.add(jtUbicacion);
		jtUbicacion.setColumns(10);
		try {
			jtUbicacion.setText(new File(".").getCanonicalPath());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		JButton jbExaminar = new JButton("Examinar");
		jbExaminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser archivo;

				archivo = new JFileChooser();

				archivo.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int seleccion = archivo.showSaveDialog(null);
				if (seleccion == JFileChooser.APPROVE_OPTION) {
					jtUbicacion.setText(archivo.getSelectedFile().getAbsolutePath());
				}
			}
		});
		jbExaminar.setFont(new Font("Tahoma", Font.BOLD, 16));
		jbExaminar.setBounds(10, 7, 112, 23);
		panel.add(jbExaminar);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "Ordenar por", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(10, 36, 389, 53);
		panel.add(panel_2);
		panel_2.setLayout(null);

		ButtonGroup grupo=new ButtonGroup();

		jrFecha = new JRadioButton("Fecha");
		jrFecha.setSelected(true);
		jrFecha.setFont(new Font("Tahoma", Font.BOLD, 16));
		jrFecha.setBounds(6, 19, 102, 23);
		panel_2.add(jrFecha);
		grupo.add(jrFecha);

		jrAutor = new JRadioButton("Autor");
		jrAutor.setFont(new Font("Tahoma", Font.BOLD, 16));
		jrAutor.setBounds(110, 19, 94, 23);
		panel_2.add(jrAutor);
		grupo.add(jrAutor);

		jrAmbas = new JRadioButton("Ambas");
		jrAmbas.setFont(new Font("Tahoma", Font.BOLD, 16));
		jrAmbas.setBounds(206, 19, 109, 23);
		panel_2.add(jrAmbas);
		grupo.add(jrAmbas);

		JButton jbOrganizar = new JButton("Organizar");
		jbOrganizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				eliminar("organizado");
				ArrayList<String> lista=new ArrayList<>();
				lista=Buscar.buscar(jtUbicacion.getText());

				for (String s : lista) {
					String url="";
					String anio=Atributo.anio(s);
					String mes=Atributo.mes(s);
					String autor=Atributo.autor(s);

					if(jrFecha.isSelected()){
						url="organizado\\"+anio+"\\"+mes;
					}else{
						if(jrAutor.isSelected()){
							url="organizado\\"+autor;
						}else{
							url="organizado\\"+anio+"\\"+mes+"\\"+autor;
						}	
					}
					File folder=new File(url);
					folder.mkdirs();

					Path FROM = Paths.get(s);
					Path TO = Paths.get(url+"\\"+new File(s).getName());
					//sobreescribir el fichero de destino, si existe, y copiar
					// los atributos, incluyendo los permisos rwx
					CopyOption[] options = new CopyOption[]{
							StandardCopyOption.REPLACE_EXISTING,
							StandardCopyOption.COPY_ATTRIBUTES
					}; 
					try {
						Files.copy(FROM, TO, options);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

				JOptionPane.showMessageDialog(null, "Organizacion Completa");

			}
		});
		jbOrganizar.setFont(new Font("Tahoma", Font.BOLD, 16));
		jbOrganizar.setBounds(10, 93, 163, 23);
		panel.add(jbOrganizar);

		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Listado de Palabras", null, panel_1, null);
		panel_1.setLayout(null);

		JLabel lblPalabra = new JLabel("Palabra:");
		lblPalabra.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPalabra.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblPalabra.setBounds(10, 11, 113, 19);
		panel_1.add(lblPalabra);

		jtPalabra = new JTextField();
		jtPalabra.setFont(new Font("Tahoma", Font.BOLD, 16));
		jtPalabra.setBounds(142, 10, 257, 20);
		panel_1.add(jtPalabra);
		jtPalabra.setColumns(10);

		JLabel lblValor = new JLabel("Valor:");
		lblValor.setHorizontalAlignment(SwingConstants.RIGHT);
		lblValor.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblValor.setBounds(10, 36, 113, 14);
		panel_1.add(lblValor);

		jtValor = new JTextField();
		jtValor.setFont(new Font("Tahoma", Font.BOLD, 16));
		jtValor.setBounds(142, 33, 134, 20);
		panel_1.add(jtValor);
		jtValor.setColumns(10);

		JButton jbAgregar = new JButton("Agregar");
		jbAgregar.setFont(new Font("Tahoma", Font.BOLD, 16));
		jbAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if(jtPalabra.getText().equals("")){
					JOptionPane.showMessageDialog(null, "Ingrese la palabra");
					jtPalabra.requestFocus();
				}else{
					if(jtValor.getText().equals("")){
						JOptionPane.showMessageDialog(null, "Ingrese el valor");
						jtValor.requestFocus();
					}else{
						int valor=0;
						try{
							valor=Integer.parseInt(jtValor.getText());
							if(valor>=-10 && valor<=10 ){
								Palabra.agregarPalabra(jtPalabra.getText(), valor);
								jtaLista.setText("");
								Properties prop= Palabra.listaPalabras();
								Enumeration<?> e =prop.propertyNames();
								while (e.hasMoreElements()) {
									String key = (String) e.nextElement();
									String value = prop.getProperty(key);
									jtaLista.append(key+" -> "+value+"\n");
								}

								JOptionPane.showMessageDialog(null, "Palabra agregada correctamente");

								jtPalabra.setText("");
								jtValor.setText("");
								jtPalabra.requestFocus();
							}else{
								JOptionPane.showMessageDialog(null, "El valor debe ser de -10 hasta 10");
								jtValor.requestFocus();
							}

						}catch(Exception e){
							JOptionPane.showMessageDialog(null, "El valor debe ser numerico");
							jtValor.requestFocus();
						}
					}
				}
			}
		});
		jbAgregar.setBounds(286, 32, 113, 23);
		panel_1.add(jbAgregar);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 86, 389, 157);
		panel_1.add(scrollPane);

		jtaLista = new JTextArea();
		jtaLista.setFont(new Font("Monospaced", Font.PLAIN, 16));
		jtaLista.setEditable(false);
		jtaLista.setLineWrap(true);
		scrollPane.setViewportView(jtaLista);

		JLabel lblPalabra_1 = new JLabel("Palabra - > Valor");
		lblPalabra_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblPalabra_1.setBounds(10, 61, 214, 14);
		panel_1.add(lblPalabra_1);

		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("Analizar", null, panel_3, null);
		panel_3.setLayout(null);

		jtUbicacionAnalizar = new JTextField();
		jtUbicacionAnalizar.setEditable(false);
		jtUbicacionAnalizar.setBounds(132, 10, 267, 20);
		panel_3.add(jtUbicacionAnalizar);
		jtUbicacionAnalizar.setColumns(10);
		try {
			jtUbicacionAnalizar.setText(new File("organizado").getCanonicalPath());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		JButton jbAnalizar = new JButton("Analizar");
		jbAnalizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				jtaPerfiles.setText("");
				ArrayList<String> listadearchivos=Buscar.buscar(jtUbicacionAnalizar.getText());
				Properties prop=new Properties();
				for (String string : listadearchivos) {
					String autor=Atributo.autor(string);
					String texto="";

					try{
						// Abrimos el archivo
						FileInputStream fstream = new FileInputStream(string);
						// Creamos el objeto de entrada
						DataInputStream entrada = new DataInputStream(fstream);
						// Creamos el Buffer de Lectura
						BufferedReader buffer = new BufferedReader(new InputStreamReader(entrada));
						String strLinea;
						// Leer el archivo linea por linea
						while ((strLinea = buffer.readLine()) != null)   {
							// Imprimimos la línea por pantalla
							texto=texto+" "+strLinea;
						}
						// Cerramos el archivo
						entrada.close();
					}catch (Exception e){ //Catch de excepciones
						System.err.println("Ocurrio un error: " + e.getMessage());
					}
					int valorArchivo=Analizar.anaizar(texto);

					String temp=prop.getProperty(autor);
					if(temp==null){
						prop.setProperty(autor, String.valueOf(valorArchivo));
					}else{
						int i=Integer.parseInt(temp)+valorArchivo;
						prop.setProperty(autor, String.valueOf(i));

					}
				}


				Enumeration<?> e =prop.propertyNames();
				while (e.hasMoreElements()) {
					String key = (String) e.nextElement();
					String value = prop.getProperty(key);
					int v=Integer.parseInt(value);
					v=v%10;
					String s="";
					if(v>=-10 && v<-5){
						s="Pésimo";
					}else{
						if(v>=-5 && v<0){
							s="Malo";
						}else{
							if(v>=0 && v<5){
								s="Aceptable";
							}else{

								s="Bueno";

							}
						}
					}
					jtaPerfiles.append(key+" -> "+s+"\n");
				}

				JOptionPane.showMessageDialog(null, "Analisis Completo");

			}
		});
		jbAnalizar.setFont(new Font("Tahoma", Font.BOLD, 16));
		jbAnalizar.setBounds(104, 36, 115, 23);
		panel_3.add(jbAnalizar);

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(10, 70, 389, 173);
		panel_3.add(scrollPane_2);

		jtaPerfiles = new JTextArea();
		jtaPerfiles.setLineWrap(true);
		jtaPerfiles.setFont(new Font("Monospaced", Font.PLAIN, 16));
		jtaPerfiles.setEditable(false);
		scrollPane_2.setViewportView(jtaPerfiles);

		JLabel lblUbicacion = new JLabel("Ubicacion:");
		lblUbicacion.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUbicacion.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblUbicacion.setBounds(10, 13, 112, 14);
		panel_3.add(lblUbicacion);

		Properties prop= Palabra.listaPalabras();
		Enumeration<?> e =prop.propertyNames();
		while (e.hasMoreElements()) {
			String key = (String) e.nextElement();
			String value = prop.getProperty(key);
			jtaLista.append(key+" -> "+value+"\n");
		}
	}

	public  void eliminar(String directorio){
		File dir=new File(directorio);
		File[] listFiles = dir.listFiles();

		if(listFiles!=null){
			for (int i=0;i<listFiles.length;i++){
				if(listFiles[i].isDirectory()){
					eliminar(listFiles[i].toPath().toString());
				}
				listFiles[i].delete();					
			}
		}
	}
}
