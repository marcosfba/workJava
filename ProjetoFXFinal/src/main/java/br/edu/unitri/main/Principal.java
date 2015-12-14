/**
 * 
 */
package br.edu.unitri.main;

import br.edu.unitri.main.FXDialog.Type;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

/**
 * @author Marcos
 *
 */
public class Principal extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		try {
			new FormFX<PrincipalControler>("TelaPrincipal.fxml", primaryStage, "Operações utilizando Rest- Aplicação cliente", true);
		} catch (Exception e) {
			new FXDialog(Type.ERROR, e.getCause().getMessage()).showDialog();
		    e.printStackTrace();
		}	
	}
	
	@Override
	public void stop() throws Exception {		 	
		 Platform.exit();	
	};
	
	public static void main(String[] args) {
		launch(args);
	}

}
