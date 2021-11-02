/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evaluacion3;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
            Lst_Entrada.getItems().add(new MenuItem(p.getName()));
        });
        
        Lst_PlatoFuerte.getItems().clear();
        PlatosFuerte.forEach((pf)->{
        Lst_PlatoFuerte.getItems().add(new MenuItem(pf.getName()));
        });
        
        Lst_Postre.getItems().clear();
        Postres.forEach((pp) -> {
        Lst_Postre.getItems().add(new MenuItem(pp.getName()));
        });
        
        Lst_Bebida.getItems().clear();
        Bebidas.forEach((pb) -> {
        Lst_Bebida.getItems().add(new MenuItem(pb.getName()));
        });
        
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    Txt_CantidadEntrada.setText("0");
    Txt_CantidadPlatoFuerte.setText("0");
    Txt_CantidadPostre.setText("0");
    Txt_CantidadBebida.setText("0");
    
    Lst_Entrada.setText("Ninguno");
    Lst_Bebida.setText("Ninguno");
    Lst_PlatoFuerte.setText("Ninguno");
    Lst_Postre.setText("Ninguno");
    Btn_CrearPlato.setOnAction((event) -> {
        try {
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
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(null, "Ingrese solo NUMEROS en el Precio!");
        }
       
    });
    
    Lst_Entrada.setOnMouseClicked((event) -> {
        for (int i = 0; i < PlatosEntrada.size(); i++) {
            //Lst_Entrada.getItems().get(i).setOnAction((e) -> {
            String s = Lst_Entrada.getItems().get(i).getText();
            Lst_Entrada.getItems().get(i).setOnAction(new EventHandler<ActionEvent>() {
                @Override public void handle(ActionEvent e) {
                Lst_Entrada.setText(s);
                }
            }); 
        }  
    });
    
    Lst_PlatoFuerte.setOnMouseClicked((event) -> {
        for (int i = 0; i < PlatosFuerte.size(); i++) {
            //Lst_Entrada.getItems().get(i).setOnAction((e) -> {
            String s = Lst_PlatoFuerte.getItems().get(i).getText();
            Lst_PlatoFuerte.getItems().get(i).setOnAction(new EventHandler<ActionEvent>() {
                @Override public void handle(ActionEvent e) {
                Lst_PlatoFuerte.setText(s);
                }
            }); 
        }  
    });
    
    Lst_Postre.setOnMouseClicked((event) -> {
        for (int i = 0; i < Postres.size(); i++) {
            //Lst_Entrada.getItems().get(i).setOnAction((e) -> {
            String s = Lst_Postre.getItems().get(i).getText();
            Lst_Postre.getItems().get(i).setOnAction(new EventHandler<ActionEvent>() {
                @Override public void handle(ActionEvent e) {
                Lst_Postre.setText(s);
                }
            }); 
        }  
    });
    
    Lst_Bebida.setOnMouseClicked((event) -> {
        for (int i = 0; i < Bebidas.size(); i++) {
            //Lst_Entrada.getItems().get(i).setOnAction((e) -> {
            String s = Lst_Bebida.getItems().get(i).getText();
            Lst_Bebida.getItems().get(i).setOnAction(new EventHandler<ActionEvent>() {
                @Override public void handle(ActionEvent e) {
                Lst_Bebida.setText(s);
                }
            }); 
        }  
    });
    
    Btn_FacturaPedido.setOnAction((event) -> {
        
        try {
          ArrayList names = new ArrayList<String>();
       ArrayList amount = new ArrayList<Integer>();
       
       names.add("Plato de Entrada:  "+Lst_Entrada.getText());
       names.add("Plato Fuerte:      "+Lst_PlatoFuerte.getText());
       names.add("Postre:            "+Lst_Postre.getText());
       names.add("Bebida:            "+Lst_Bebida.getText());
       
       amount.add(Integer.parseInt(Txt_CantidadEntrada.getText()));
       amount.add(Integer.parseInt(Txt_CantidadPlatoFuerte.getText()));
       amount.add(Integer.parseInt(Txt_CantidadPostre.getText()));
       amount.add(Integer.parseInt(Txt_CantidadBebida.getText()));  

       
       
        String factura="";
        float total=0;
        
        for (int i = 0; i <=3; i++) {
            if(!names.get(i).equals("Ninguno")) {
               if ((int)amount.get(i)!=0) {
                   factura += (String)names.get(i)+" Cantidad: "+String.valueOf(amount.get(i))+"\n";  
                   for (Plato p : Totales) {
                       String n = names.get(i).toString().substring(names.get(i).toString().length()-p.getName().length(), names.get(i).toString().length());
                      if(p.getName().equals(n)){
                          total += p.getPrice()*Float.parseFloat(amount.get(i).toString());
                      } 
                   }
               }
           }

        }
        if(factura.equals("")){
            factura="\n\n  No hay pedidos!";

            TxtArea_Factura.setText(factura);
        }else{
            if(Check_ClienteFiel.isSelected()==true){
                factura+="\n"+"Valor Parcial del Peido:  "+total+"\n\n";
                factura+="Por ser Cliente Fiel de Nuestra Tienda Tiene un Descuento del 12%\nValor Total del Pedido: $"+(total*0.88);
                TxtArea_Factura.setText(factura);
            }else{factura+="\n"+"Valor Total del Peido:  $"+total+"\n\n";
                TxtArea_Factura.setText(factura);}
        }    
        
          
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Debe Ingresar Solo NUMEROS en las Cantidades!");
        }

       
       
       
    });
    
    
    } 
}
