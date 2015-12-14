package br.edu.unitri.main;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import br.edu.unitri.controler.EmailControler;
import br.edu.unitri.controler.EnderecoControler;
import br.edu.unitri.controler.PessoaControler;
import br.edu.unitri.controler.TelefoneControler;
import br.edu.unitri.main.FXDialog.Type;
import br.edu.unitri.model.Email;
import br.edu.unitri.model.Endereco;
import br.edu.unitri.model.Pessoa;
import br.edu.unitri.model.Telefone;
import br.edu.unitri.util.GenericTable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class ContatoFX implements Initializable {


	@FXML private TableView<Email> tbEmails;
    @FXML private TableView<Pessoa> tbPessoas;
    @FXML private TabPane tabTela;
    @FXML private TableView<Telefone> tbTelefones;
	@FXML private TableView<Endereco> tbEnderecos;
	@FXML private TextField edNomePessoa;

    private ObservableList<Endereco> enderecosList = FXCollections.observableArrayList();
	private ObservableList<Telefone> telefonesList = FXCollections.observableArrayList();
	private ObservableList<Email> emailList = FXCollections.observableArrayList();
	private ObservableList<Pessoa> contatosList = FXCollections.observableArrayList();

	private PessoaControler pessoaCtr = new PessoaControler();
	private TelefoneControler telefoneCtr = new TelefoneControler();
	private EmailControler emailCtr = new EmailControler();
	private EnderecoControler enderecoCtr = new EnderecoControler();
	
	private Pessoa contato = new Pessoa();
	
	private void popularDados() {
		contatosList.clear();
		tbPessoas.getItems().clear();
		try {
			contatosList.addAll(pessoaCtr.listAll());
		} catch (Exception e) {
			new FXDialog(Type.ERROR, e.getCause().getMessage()).showDialog();
		}
		tbPessoas.setItems(contatosList);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		popularDados();
		tbPessoas.getColumns().addAll(new GenericTable<Pessoa>().tableColunas(Pessoa.class));		
		tbEnderecos.getColumns().addAll(new GenericTable<Endereco>().tableColunas(Endereco.class));		
		tbEmails.getColumns().addAll(new GenericTable<Email>().tableColunas(Email.class));		
		tbTelefones.getColumns().addAll(new GenericTable<Telefone>().tableColunas(Telefone.class));		
		
		tbPessoas.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if (event.getClickCount() > 1) {
					contato = tbPessoas.getSelectionModel().getSelectedItem();
					tabTela.getSelectionModel().select(1);
					listarDadosContato(contato);
				}
			}
		});
	}

	protected void listarDadosContato(Pessoa contato2) {
		tbEmails.getItems().clear();
		tbEnderecos.getItems().clear();
		tbTelefones.getItems().clear();
		
		enderecosList.clear();
		emailList.clear();
		telefonesList.clear();		

		List<Telefone> telefones = telefoneCtr.listAll();
		List<Email> emails = emailCtr.listAll();
		List<Endereco> enderecos = enderecoCtr.listAll();
		
		for (Endereco endereco : enderecos) {
			if (endereco.getPessoa().equals(contato2)) { 
				enderecosList.add(endereco);
			}
		}
		for (Email email : emails) {
			if (email.getPessoa().equals(contato2)) { 
				emailList.add(email);
			} 
		}
		for (Telefone telefone : telefones) {
			if (telefone.getPessoa().equals(contato2)) { 
				telefonesList.add(telefone);
			} 
		}
		tbEmails.setItems(emailList);
		tbEnderecos.setItems(enderecosList);
		tbTelefones.setItems(telefonesList);
		edNomePessoa.setText(contato2.getNome());
		
	}

}
