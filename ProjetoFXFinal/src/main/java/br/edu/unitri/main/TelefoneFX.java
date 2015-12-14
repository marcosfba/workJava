package br.edu.unitri.main;

import java.net.URL;
import java.util.ResourceBundle;

import br.edu.unitri.controler.PessoaControler;
import br.edu.unitri.controler.TelefoneControler;
import br.edu.unitri.enumerators.TipoTelefone;
import br.edu.unitri.main.FXDialog.Type;
import br.edu.unitri.model.Pessoa;
import br.edu.unitri.model.Telefone;
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
public class TelefoneFX implements Initializable {
	
	@FXML private ComboBox<Pessoa> cbPessoa;
    @FXML private Button btnIncluir;
    @FXML private TextField edCodArea;
    @FXML private ComboBox<TipoTelefone> cbTipoTelefone;
    @FXML private TextField edNumRamal;
    @FXML private Tab tabCadastro;
    @FXML private TextField edNumTelefone;
    @FXML private TabPane tabTela;
    @FXML private TextField edNumDdd;
    @FXML private Button btnNovo;
	
    private PessoaControler pessoaCtr = new PessoaControler();
    private TelefoneControler telefoneCtr = new TelefoneControler();
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		cbTipoTelefone.getItems().setAll(TipoTelefone.values());
		cbPessoa.getItems().setAll(pessoaCtr.listAll());
	}
	
	private boolean isTelaValida() {
		boolean ok = true;
		if (cbTipoTelefone.getSelectionModel().getSelectedItem() == null) {
			new FXDialog(Type.WARNING, "Favor escolha o Tipo de Telefone!").showDialog();			
			cbTipoTelefone.requestFocus();
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
			if (edCodArea.getText().isEmpty()) {
				new FXDialog(Type.WARNING, "Favor preencha a descrição do código de area!").showDialog();
				edCodArea.requestFocus();
				ok = false;			
			}
		}
		if (ok){
			if (edNumTelefone.getText().isEmpty()) {
				new FXDialog(Type.WARNING, "Favor preencha o número do Telefone!").showDialog();
				edNumTelefone.requestFocus();
				ok = false;			
			}
		}
		if (ok){
			if (edNumDdd.getText().isEmpty()) {
				new FXDialog(Type.WARNING, "Favor preencha o número do DDD referente a telefone!").showDialog();
				edNumDdd.requestFocus();
				ok = false;			
			}
		}
		if (ok){
			if (edNumRamal.getText().isEmpty()) {
				new FXDialog(Type.WARNING, "Favor preencha o número do ramal!").showDialog();
				edNumRamal.requestFocus();
				ok = false;			
			}
		}
		return ok;
	}
	
    @FXML
    void btnIncluirClick(ActionEvent event) {
		if (isTelaValida()) {
			Telefone telefone = new Telefone(cbTipoTelefone.getSelectionModel().getSelectedItem(), edNumDdd.getText(), edNumTelefone.getText(), 
					edNumRamal.getText(), edCodArea.getText(), cbPessoa.getSelectionModel().getSelectedItem());
			try {
				telefoneCtr.insert(telefone);
				new FXDialog(Type.INFO, "Operação realizada com sucesso!").showDialog();
				btnNovoClick(event);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
    }
    
    @FXML
    void btnNovoClick(ActionEvent event) {
    	cbTipoTelefone.getSelectionModel().select(null);
    	cbPessoa.getSelectionModel().select(null);
    	edCodArea.setText("");
    	edNumTelefone.setText("");
    	edNumDdd.setText("");
    	edNumRamal.setText("");
    	cbTipoTelefone.requestFocus();
    }
	
}
