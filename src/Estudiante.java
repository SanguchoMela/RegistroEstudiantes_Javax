import java.io.Serializable;
import java.lang.invoke.StringConcatFactory;

public class Estudiante implements Serializable {
    //atributos
    private static final long serialVersionUID = 1L;
    private String codigo;
    private String cedula;
    private String nombre;
    private String apellido;
    private String signo;
    private String anioNac;
    private String mesNac;
    private String diaNac;
    private String colorFav;
    private String casado;

    //constructor
    public Estudiante(String codigo, String cedula, String nombre, String apellido, String signo, String anioNac, String mesNac, String diaNac, String colorFav, String casado) {
        this.codigo = codigo;
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.signo = signo;
        this.anioNac = anioNac;
        this.mesNac = mesNac;
        this.diaNac = diaNac;
        this.colorFav = colorFav;
        this.casado = casado;
    }

    //setters y getters
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getSigno() {
        return signo;
    }

    public void setSigno(String signo) {
        this.signo = signo;
    }

    public String getAnioNac() {
        return anioNac;
    }

    public void setAnioNac(String anioNac) {
        this.anioNac = anioNac;
    }

    public String getMesNac() {
        return mesNac;
    }

    public void setMesNac(String mesNac) {
        this.mesNac = mesNac;
    }

    public String getDiaNac() {
        return diaNac;
    }

    public void setDiaNac(String diaNac) {
        this.diaNac = diaNac;
    }

    public String getColorFav() {
        return colorFav;
    }

    public void setColorFav(String colorFav) {
        this.colorFav = colorFav;
    }

    public String getCasado() {
        return casado;
    }

    public void setCasado(String casado) {
        this.casado = casado;
    }

    //método para lectura del objeto
    @Override
    public String toString(){
        return "\nCódigo: " +codigo+
                "\nCédula: " +cedula+
                "\nNombre: " +nombre+
                "\nApellido: " +apellido+
                "\nSigno: " +signo+
                "\nFecha de Nacimiento: "+anioNac+"/"+mesNac+"/"+diaNac+
                "\nColor favorito: " +colorFav+
                "\nCasado: " +casado;
    }
}
