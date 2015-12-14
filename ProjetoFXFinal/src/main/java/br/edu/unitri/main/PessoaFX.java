package br.edu.unitri.main;

import java.net.URL;
import java.util.ResourceBundle;

import br.edu.unitri.controler.PessoaControler;
import br.edu.unitri.enumerators.TipoPessoa;
import br.edu.unitri.main.FXDialog.Type;
import br.edu.unitri.model.Pessoa;
import br.edu.unitri.util.ConverterUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;

/**
 * @author marcos.fernando
 *
 */
public class PessoaFX implements Initializable {

	@FXML private ComboBox<TipoPessoa> cbTipoPessoa;
	@FXML private Button btnIncluir;
	@FXML private TextField txtNome;
	@FXML private Tab tabCadastro;
	@FXML private DatePicker dtNascimento;
	@FXML private TabPane tabTela;
	@FXML private Button btnNovo;
	
	private PessoaControler pessoaCtr = new PessoaControler();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
       cbTipoPessoa.getItems().setAll(TipoPessoa.values());
	}
	
	private boolean isTelaValida() {
		boolean ok = true;
		if (cbTipoPessoa.getSelectionModel().getSelectedItem() == null) {
			new FXDialog(Type.WARNING, "Favor escolha o Tipo de Pessoa!").showDialog();			
			cbTipoPessoa.requestFocus();
			ok = false;			
		}
		if (ok){
			if (dtNascimento.getValue() == null) {
				new FXDialog(Type.WARNING, "Favor preencher a data de nascimento da Pessoa!").showDialog();
				dtNascimento.requestFocus();
				ok = false;			
			}
		}
		if (ok){
			if (txtNome.getText().isEmpty()) {
				new FXDialog(Type.WARNING, "Favor preencha o nome da pessoa!").showDialog();
				txtNome.requestFocus();
				ok = false;			
			}
		}
		return ok;
	}

	@FXML
	void btnIncluirClick(ActionEvent event) {
		if (isTelaValida()) {
			Pessoa pessoa = new Pessoa(null, cbTipoPessoa.getSelectionModel().getSelectedItem(), txtNome.getText(), ConverterUtil.localDateToDate(dtNascimento.getValue()));
			try {
				pessoaCtr.insert(pessoa);
				new FXDialog(Type.INFO, "Operação realizada com sucesso!").showDialog();
				btnNovoClick(event);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@FXML
	void btnNovoClick(ActionEvent event) {
		 txtNome.setText("");
		 dtNascimento.setValue(null);
		 cbTipoPessoa.getSelectionModel().select(null);
	}

}
