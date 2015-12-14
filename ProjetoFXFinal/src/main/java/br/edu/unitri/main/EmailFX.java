package br.edu.unitri.main;

import java.net.URL;
import java.util.ResourceBundle;

import br.edu.unitri.controler.EmailControler;
import br.edu.unitri.controler.PessoaControler;
import br.edu.unitri.enumerators.TipoEmail;
import br.edu.unitri.main.FXDialog.Type;
import br.edu.unitri.model.Email;
import br.edu.unitri.model.Pessoa;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;

/**
 * @author marcos.fernando
 *
 */
public class EmailFX implements Initializable {
	
	@FXML private TextField edEmail;
    @FXML private ComboBox<TipoEmail> cbTipoEmail;
    @FXML private ComboBox<Pessoa> cbPessoa;
    @FXML private Button btnIncluir;
    @FXML private Tab tabCadastro;
    @FXML private TabPane tabTela;
    @FXML private Button btnNovo;
    
    private PessoaControler pessoaCtr = new PessoaControler();
    private EmailControler emailCtr = new EmailControler();
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		cbTipoEmail.getItems().setAll(TipoEmail.values());
		cbPessoa.getItems().setAll(pessoaCtr.listAll());
 	}
	
	private boolean isTelaValida() {
		boolean ok = true;
		if (cbTipoEmail.getSelectionModel().getSelectedItem() == null) {
			new FXDialog(Type.WARNING, "Favor escolha o Tipo de Email!").showDialog();			
			cbTipoEmail.requestFocus();
			ok = false;			
		}
		if (ok){
			if (cbPessoa.getSelectionModel().getSelectedItem() == null) {
				new FXDialog(Type.WARNING, "Favor selecione a Pessoa!").showDialog();			
				cbPessoa.requestFocus();
				ok = false;			
			}
		}
		if (ok){
			if (edEmail.getText().isEmpty()) {
				new FXDialog(Type.WARNING, "Favor preencha a descrição do endereço de email!").showDialog();
				edEmail.requestFocus();
				ok = false;			
			}
		}
		return ok;
	}
	
	@FXML
    void btnIncluirClick(ActionEvent event) {
		if (isTelaValida()) {
			Email email = new Email(cbTipoEmail.getSelectionModel().getSelectedItem(), edEmail.getText(), cbPessoa.getSelectionModel().getSelectedItem());
			try {
				emailCtr.insert(email);
				new FXDialog(Type.INFO, "Operação realizada com sucesso!").showDialog();
				btnNovoClick(event);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
    }

    @FXML
    void btnNovoClick(ActionEvent event) {
    	cbTipoEmail.getSelectionModel().select(null);
    	cbPessoa.getSelectionModel().select(null);
    	edEmail.setText("");
    }
	
}
