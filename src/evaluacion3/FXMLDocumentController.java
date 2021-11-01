/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evaluacion3;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javax.swing.JOptionPane;


/**
 *
 * @author RONNY PANTOJA
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Button Btn_CrearPlato;

    @FXML
    private Button Btn_FacturaPedido;

    @FXML
    private CheckBox Check_ClienteFiel;

    @FXML
    private ListView<String> List_Platos;

    @FXML
    private MenuButton Lst_Bebida;

    @FXML
    private MenuButton Lst_Entrada;

    @FXML
    private MenuButton Lst_PlatoFuerte;

    @FXML
    private MenuButton Lst_Postre;

    @FXML
    private RadioButton Rbtn_Bebida;

    @FXML
    private RadioButton Rbtn_Entrada;

    @FXML
    private RadioButton Rbtn_PlatoFuerte;

    @FXML
    private RadioButton Rbtn_Postre;

    @FXML
    private ToggleGroup TipoPlato;

    @FXML
    private TextArea TxtArea_Factura;

    @FXML
    private TextField Txt_CantidadBebida;

    @FXML
    private TextField Txt_CantidadEntrada;

    @FXML
    private TextField Txt_CantidadPlatoFuerte;

    @FXML
    private TextField Txt_CantidadPostre;

    @FXML
    private TextField Txt_Nombre;

    @FXML
    private TextField Txt_Precio;
    
    //  Listas Generales de los Platos
    ArrayList<Plato> PlatosEntrada = new ArrayList<Plato>();
    ArrayList<Plato> PlatosFuerte = new ArrayList<Plato>();
    ArrayList<Plato> Bebidas = new ArrayList<Plato>();
    ArrayList<Plato> Postres = new ArrayList<Plato>();
    ObservableList<Plato> Totales = FXCollections.observableArrayList();
    
    
    // ------------------------------   CREACION DE PLATOS --------------------------------
    // Validar Datos TxtFields
    public boolean validarDatosCP(){
        Boolean band = true;
        if(Txt_Nombre.getText().equals("")||Txt_Precio.getText().equals("")){
            band = false;
        }
    return band;
    }
    // Limpiar Datos 
    public void clearDatosCP(){
        Txt_Nombre.setText(null);
        Txt_Precio.setText(null);
        Rbtn_Bebida.setSelected(false);
        Rbtn_Entrada.setSelected(true);
        Rbtn_PlatoFuerte.setSelected(false);
        Rbtn_Postre.setSelected(false);
        Txt_Nombre.requestFocus();
    }
    
    // Imprimir los datos en ListView
    public void printPlatosCP(){
        ObservableList<String> Datos = FXCollections.observableArrayList();
        Totales.forEach((p) -> {
            Datos.add(p.getName()+";    "+p.getCategory()+";     $"+p.getPrice());
        });
        List_Platos.setItems(Datos);
    
    }
    // --------------------------------------- CREACION DE PEDIDOS ----------------------------------------
    public void cargarPlatosP(){
        
        Lst_Entrada.getItems().clear();
        PlatosEntrada.forEach((p) -> {
            Lst_Entrada.getItems().add(new MenuItem(p.getName()+"; $"+p.getPrice()));
        });
        
        Lst_PlatoFuerte.getItems().clear();
        PlatosFuerte.forEach((pf)->{
        Lst_PlatoFuerte.getItems().add(new MenuItem(pf.getName()+"; $"+pf.getPrice()));
        });
        
        Lst_Postre.getItems().clear();
        Postres.forEach((pp) -> {
        Lst_Postre.getItems().add(new MenuItem(pp.getName()+"; $"+pp.getPrice()));
        });
        
        Lst_Bebida.getItems().clear();
        Bebidas.forEach((pb) -> {
        Lst_Bebida.getItems().add(new MenuItem(pb.getName()+"; $"+pb.getPrice()));
        });
        
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    Lst_Entrada.setText("Ninguna");
    Lst_Bebida.setText("Ninguna");
    Lst_PlatoFuerte.setText("Ninguna");
    Lst_Postre.setText("Ninguna");
    
        try {
        Btn_CrearPlato.setOnAction((event) -> {    
            String category="";
        if(validarDatosCP()){
            if(Txt_Precio.getText().matches("[0-9.]+")){
            if(Rbtn_Entrada.isSelected()){
                category="Entrada";
                Plato p = new Plato(Txt_Nombre.getText(), Float.valueOf(Txt_Precio.getText()), category);
                PlatosEntrada.add(p);
                Totales.add(p);
            }
            if(Rbtn_PlatoFuerte.isSelected()){
                category="Plato Fuerte";
                Plato p = new Plato(Txt_Nombre.getText(), Float.valueOf(Txt_Precio.getText()), category);
                PlatosFuerte.add(p);
                Totales.add(p);
            }
            if(Rbtn_Postre.isSelected()){
                category="Postre";
                Plato p = new Plato(Txt_Nombre.getText(), Float.valueOf(Txt_Precio.getText()), category);
                Postres.add(p);
                Totales.add(p);
            }
            if(Rbtn_Bebida.isSelected()){
                category="Bebida";
                Plato p = new Plato(Txt_Nombre.getText(), Float.valueOf(Txt_Precio.getText()), category);
                Bebidas.add(p);
                Totales.add(p);
            }           
            clearDatosCP();
            printPlatosCP();
        }else{JOptionPane.showMessageDialog(null, "Ingrese SOLO numeros en el PRECIO!");}
        }else{JOptionPane.showMessageDialog(null, "Ingrese los Datos!");
              Txt_Nombre.requestFocus();}
        cargarPlatosP();
    });
        } catch (Exception e) {
            //TODO: handle exception
        }
        
    
    Lst_Entrada.setOnAction((event) -> {
        Lst_Entrada.getItems().get(1).setOnAction((ev) -> {
            System.out.println("SISA");
        //Lst_Entrada.setText(Lst_Entrada.getItems().get(0).getText());
        
        });
    });
    
    
    
    } 
}
