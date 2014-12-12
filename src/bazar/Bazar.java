/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bazar;

import forms.CadastroCliente;
import forms.TelaPrincipal;

/**
 *
 * @author danilo
 */
public class Bazar {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        forms.TelaPrincipal objTelaPrin = new TelaPrincipal();
//        objTelaPrin.setVisible(true);
        
        forms.CadastroCliente objCadastro = new CadastroCliente();       
        objCadastro.setVisible(true);
    }
    
}
