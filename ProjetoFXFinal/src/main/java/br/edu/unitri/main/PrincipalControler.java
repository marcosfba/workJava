package br.edu.unitri.main;

import java.net.URL;
import java.util.ResourceBundle;

import br.edu.unitri.main.FXDialog.Type;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

public class PrincipalControler implements Initializable {

	@FXML private MenuItem menuItemTelefones;
    @FXML private MenuItem menuItemEmails;
    @FXML private MenuItem menuItemAbout;
    @FXML private MenuItem menuItemEnderecos;
    @FXML private MenuItem menuItemPessoa;
    @FXML private MenuItem menuItemContato;
    
    @FXML
    void menuItemFecharClick(ActionEvent event) {
		boolean ok = new FXDialog(Type.CONFIRM,"Tem certeza que deseja encerrar a aplicação?").showDialog();
		if (ok) {
			System.exit(0);
		}
    }

    @FXML
    void menuItemPessoaClick(ActionEvent event) {
		Stage telaPessoa = new Stage();
		try {
			new FormFX<PessoaFX>("Pessoa.fxml", telaPessoa, "Cadastro da Pessoa - Cliente Rest", false);
		} catch (Exception e) {
			new FXDialog(Type.ERROR, e.getCause().getMessage()).showDialog();
		}
    }

    @FXML
    void menuItemEnderecosClick(ActionEvent event) {
		Stage telaEndereco = new Stage();
		try {
			new FormFX<EnderecoFX>("Endereco.fxml", telaEndereco, "Cadastro de Endereços - Cliente Rest", false);
		} catch (Exception e) {
			new FXDialog(Type.ERROR, e.getCause().getMessage()).showDialog();
		}
    }

    @FXML
    void menuItemEmailsClick(ActionEvent event) {
		Stage telaEmail = new Stage();
		try {
			new FormFX<EmailFX>("Email.fxml", telaEmail, "Cadastro de Emails - Cliente Rest", false);
		} catch (Exception e) {
			new FXDialog(Type.ERROR, e.getCause().getMessage()).showDialog();
		}
    }

    @FXML
    void menuItemTelefonesClick(ActionEvent event) {
		Stage telaTelefone = new Stage();
		try {
			new FormFX<TelefoneFX>("Telefone.fxml", telaTelefone, "Cadastro de Telefones - Cliente Rest", false);
		} catch (Exception e) {
			new FXDialog(Type.ERROR, e.getCause().getMessage()).showDialog();
		}
    }

    @FXML
    void menuItemContatoClick(ActionEvent event) {
		Stage telaContato = new Stage();
		try {
			new FormFX<ContatoFX>("Contato.fxml", telaContato, "Contato Completo - Cliente Rest", false);
		} catch (Exception e) {
			new FXDialog(Type.ERROR, e.getCause().getMessage()).showDialog();
		}
    }

	@FXML
	void menuItemAboutClick(ActionEvent event) {
		Stage telaAbout = new Stage();
		try {
			new FormFX<AboutFX>("About.fxml", telaAbout, "Sobre o Desenvolvedor", false);
		} catch (Exception e) {
			new FXDialog(Type.ERROR, e.getCause().getMessage()).showDialog();
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}
}
