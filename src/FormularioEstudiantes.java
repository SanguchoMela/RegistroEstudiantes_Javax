import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.*;
import java.util.ArrayList;

public class FormularioEstudiantes {
    private JTextField codigo_in;
    private JTextField cedula_in;
    private JTextField nombre_in;
    private JTextField apellido_in;
    private JLabel main_label;
    private JPanel rootPanel;
    private JLabel codigo_label;
    private JLabel cedula_label;
    private JLabel nombre_label;
    private JLabel apellido_label;
    private JLabel signo_label;
    private JLabel fechaNac_label;
    private JLabel color_label;
    private JLabel casado_label;
    private JButton cargarButton;
    private JButton guardarButton;
    private JButton anteriorButton;
    private JButton siguienteButton;
    private JComboBox signo_select;
    private JComboBox anio_select;
    private JComboBox mes_select;
    private JComboBox dia_select;
    private JCheckBox rojo_select;
    private JCheckBox verde_select;
    private JCheckBox ninguno_select;
    private JRadioButton si_select;
    private JRadioButton no_select;

    //lista para almacenar estudiantes
    ArrayList<Estudiante> listaEstudiantes = new ArrayList<>();
    //archivo definido
    String filePath = "data.dat";
    //variables para registrar el indice del estudiante
    private int indiceActual = 0;
    private int totalEstudiantes = 0;

    //--------------------------------------------------------------------
    public FormularioEstudiantes() {
        //Acciones de botones
        cargarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try(
                        ObjectInputStream objectIn = new ObjectInputStream(new FileInputStream(filePath))
                   ){
                    listaEstudiantes = (ArrayList<Estudiante>) objectIn.readObject();

                    totalEstudiantes = listaEstudiantes.size();
                    indiceActual = 0;

                    for(Estudiante estudiante:listaEstudiantes){
                        System.out.println(estudiante.toString());
                    }
                    JOptionPane.showMessageDialog(null,"Datos cargados correctamente","Cargar Datos",JOptionPane.INFORMATION_MESSAGE);

                } catch (IOException | ClassNotFoundException ex){
                    JOptionPane.showMessageDialog(null, "Error al cargar los datos.", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String codigo = codigo_in.getText();
                String cedula = cedula_in.getText();
                String nombre = nombre_in.getText();
                String apellido = apellido_in.getText();
                String signo = String.valueOf(signo_select.getSelectedItem());
                String anio = String.valueOf(anio_select.getSelectedItem());
                String mes = String.valueOf(mes_select.getSelectedItem());
                String dia = String.valueOf(dia_select.getSelectedItem());

                //codigo adicional para checkbox
                String colorFav = null;
                if(rojo_select.isSelected()){
                    colorFav = rojo_select.getText();
                } else if(verde_select.isSelected()){
                    colorFav = verde_select.getText();
                } else if(ninguno_select.isSelected()){
                    colorFav = ninguno_select.getText();
                }
                //codigo adicional para radiobutton
                //grupo para radiobutton
                ButtonGroup groupCasado = new ButtonGroup();
                groupCasado.add(si_select);
                groupCasado.add(no_select);
                si_select.setActionCommand("si");
                no_select.setActionCommand("no");

                String casado = groupCasado.getSelection().getActionCommand();
                //----------------------------------------------------------
                //Objeto definido
                Estudiante estudiante = new Estudiante(codigo,cedula,nombre,apellido,signo,anio,mes,dia,colorFav,casado);
                //Objeto añadido al ArrayList
                listaEstudiantes.add(estudiante);

                try(
                        ObjectOutputStream objectOut = new ObjectOutputStream(new FileOutputStream(filePath))
                    ){
                    objectOut.writeObject(listaEstudiantes);
                    JOptionPane.showMessageDialog(null,"Datos guardados correctamente","Guardar datos",JOptionPane.INFORMATION_MESSAGE);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null,"Error al guardar","Error",JOptionPane.ERROR_MESSAGE);
                }

                limpiarCampos();
            }
        });
        anteriorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(indiceActual>0){
                    indiceActual--;
                    limpiarSelecciones();
                    mostrarDatosEstudiantes();
                }
            }
        });
        siguienteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(indiceActual < totalEstudiantes-1){
                    indiceActual++;
                    limpiarSelecciones();
                    mostrarDatosEstudiantes();
                }
            }
        });
        /*
        rojo_select.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                JCheckBox source = (JCheckBox) e.getSource();
                if(source.isSelected()){
                    System.out.println();
                }
            }
        });
        */
    }

    //metodo para limpiar las selecciones
    private void limpiarCampos(){
        codigo_in.setText(null);
        cedula_in.setText(null);
        nombre_in.setText(null);
        apellido_in.setText(null);
        signo_select.setSelectedItem(null);
        anio_select.setSelectedItem(null);
        mes_select.setSelectedItem(null);
        dia_select.setSelectedItem(null);
        rojo_select.setSelected(false);
        verde_select.setSelected(false);
        ninguno_select.setSelected(false);
        //quitar la seleccion del radiobutton
        ButtonGroup groupCasado = new ButtonGroup();
        groupCasado.add(si_select);
        groupCasado.add(no_select);
        groupCasado.clearSelection();
    }

    //metodo para mostrar los datos guardados
    private void mostrarDatosEstudiantes(){
        Estudiante estudianteActual = listaEstudiantes.get(indiceActual);
        codigo_in.setText(estudianteActual.getCodigo());
        cedula_in.setText(estudianteActual.getCedula());
        nombre_in.setText(estudianteActual.getNombre());
        apellido_in.setText(estudianteActual.getApellido());
        signo_select.setSelectedItem(estudianteActual.getSigno());
        anio_select.setSelectedItem(estudianteActual.getAnioNac());
        mes_select.setSelectedItem(estudianteActual.getMesNac());
        dia_select.setSelectedItem(estudianteActual.getDiaNac());
        //Color favorito - checkbox
        String colorFav = estudianteActual.getColorFav();
        if(colorFav != null){
            if(colorFav.equals(rojo_select.getText())){
                rojo_select.setSelected(true);
            } else if(colorFav.equals(verde_select.getText())){
                verde_select.setSelected(true);
            } else if(colorFav.equals(ninguno_select.getText())){
                ninguno_select.setSelected(true);
            }
        }
        //casado - radiobutton
        String casado = estudianteActual.getCasado();
        if(casado != null){
            if(casado.equals(si_select.getActionCommand())){
                si_select.setSelected(true);
            } else if(casado.equals(no_select.getActionCommand())){
                no_select.setSelected(true);
            }
        }
    }
    //metodo para limpiar checkbox y radiobutton
    private void limpiarSelecciones(){
        rojo_select.setSelected(false);
        verde_select.setSelected(false);
        ninguno_select.setSelected(false);
        si_select.setSelected(false);
        no_select.setSelected(false);
    }

    //Main - muestra JPanel principal
    public static void main(String[] args) {
        JFrame frame = new JFrame("FormularioEstudiantes");
        frame.setContentPane(new FormularioEstudiantes().rootPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(660,260);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
    }
}
