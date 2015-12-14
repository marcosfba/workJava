package br.edu.unitri.main;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import br.edu.unitri.controler.EnderecoControler;
import br.edu.unitri.controler.PessoaControler;
import br.edu.unitri.enumerators.TipoEndereco;
import br.edu.unitri.main.FXDialog.Type;
import br.edu.unitri.model.Endereco;
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
public class EnderecoFX implements Initializable {
	
	@FXML private ComboBox<String> cbEstado;
    @FXML private ComboBox<TipoEndereco> cbTipoEndereco;
    @FXML private ComboBox<Pessoa> cbPessoa;
    @FXML private Tab tabCadastro;
    @FXML private TextField edCidade;
    @FXML private TextField edCep;
    @FXML private TextField edComplemento;
    @FXML private TextField edEndereco;
    @FXML private Button btnIncluir;
    @FXML private TextField edNumero;
    @FXML private TextField edPais;
    @FXML private TabPane tabTela;
    @FXML private Button btnNovo;
    
    private PessoaControler pessoaCtr = new PessoaControler();
    private EnderecoControler enderecoCtr = new EnderecoControler();
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		cbTipoEndereco.getItems().setAll(TipoEndereco.values());
		cbEstado.getItems().setAll(getListaUfs());
		cbPessoa.getItems().setAll(pessoaCtr.listAll());
 	}
	
	private boolean isTelaValida() {
		boolean ok = true;
		if (cbTipoEndereco.getSelectionModel().getSelectedItem() == null) {
			new FXDialog(Type.WARNING, "Favor escolha o Tipo de Endereço!").showDialog();			
			cbTipoEndereco.requestFocus();
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
			if (edEndereco.getText().isEmpty()) {
				new FXDialog(Type.WARNING, "Favor preencha a descrição do endereço!").showDialog();
				edEndereco.requestFocus();
				ok = false;			
			}
		}
		if (ok){
			if (cbEstado.getSelectionModel().getSelectedItem() == null) {
				new FXDialog(Type.WARNING, "Favor selecione a UF!").showDialog();			
				cbEstado.requestFocus();
				ok = false;			
			}
		}
		if (ok){
			if (edCidade.getText().isEmpty()) {
				new FXDialog(Type.WARNING, "Favor preencha a descrição da cidade!").showDialog();
				edCidade.requestFocus();
				ok = false;			
			}
		}
		if (ok){
			if (edPais.getText().isEmpty()) {
				new FXDialog(Type.WARNING, "Favor preencha a descrição do país!").showDialog();
				edPais.requestFocus();
				ok = false;			
			}
		}
		if (ok){
			if (edCep.getText().isEmpty()) {
				new FXDialog(Type.WARNING, "Favor preencha o número do CEP!").showDialog();
				edCep.requestFocus();
				ok = false;			
			}
		}
		if (ok){
			if (edNumero.getText().isEmpty()) {
				new FXDialog(Type.WARNING, "Favor preencha o número do endereço!").showDialog();
				edNumero.requestFocus();
				ok = false;			
			}
		}
		return ok;
	}

    @FXML
    void btnIncluirClick(ActionEvent event) {
		if (isTelaValida()) {
			Endereco endereco = new Endereco(cbTipoEndereco.getSelectionModel().getSelectedItem(), edPais.getText(), cbEstado.getSelectionModel().getSelectedItem(),
					edCidade.getText(), edEndereco.getText(), Integer.valueOf(edNumero.getText()), edComplemento.getText(), edCep.getText(), cbPessoa.getSelectionModel().getSelectedItem());
			try {
				enderecoCtr.insert(endereco);
				new FXDialog(Type.INFO, "Operação realizada com sucesso!").showDialog();
				btnNovoClick(event);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
    }

    @FXML
    void btnNovoClick(ActionEvent event) {
    	cbEstado.getSelectionModel().select(null);
    	cbTipoEndereco.getSelectionModel().select(null);
    	cbPessoa.getSelectionModel().select(null);
    	edCidade.setText("");
    	edCep.setText("");
    	edComplemento.setText("");
    	edEndereco.setText("");
    	edNumero.setText("");
    	edPais.setText("");
    }

	private List<String> getListaUfs() {
		String uf[] = { "AC", "AL", "AM", "AP", "BA", "CE", "DF", "ES", "GO", "MA", "MG", "MS", "MT", "PA", "PB", "PE",
				"PI", "PR", "RJ", "RN", "RO", "RR", "RS", "SC", "SE", "SP", "TO" };
		List<String> items = new ArrayList<String>();
		for (int i = 0; i < uf.length; i++) {
			items.add(uf[i]);
		}
		return items;
	}
}
