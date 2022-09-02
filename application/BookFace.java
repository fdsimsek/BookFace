package application;

//Firat Deniz Simsek	
import java.io.File;

//Standard Javafx imports
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.Scene;

//import for components (controls)
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

//imports for components
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

//imports for layout
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class BookFace extends Application {
	// declare components that require class
	Label lblEnterStatus;
	TextField txtfStatus;
	Button btnUpdate;

	ImageView imvProfilePhoto;
	Image img;
	Label lblName, lblStudentNum, lblProgramme;
	Button btnChangePhoto, btnEditProfile;

	Label lblFeel;
	Label lblMyStatus;
	
	//Constructor - instantiate variables
	public BookFace() {

		lblEnterStatus = new Label("How do you feel today? ");
		txtfStatus = new TextField();
		btnUpdate = new Button("Update Status");
		lblMyStatus = new Label("Today I Feel");
		//manage textfield size
		txtfStatus.setPrefWidth(400);

		btnChangePhoto = new Button("Change Photo");
		lblName = new Label("Full Name");
		lblStudentNum = new Label("Student Number");
		lblProgramme = new Label("Programme of Study");
		btnEditProfile = new Button("Edit Profile");
		btnEditProfile.setMinWidth(100);
		imvProfilePhoto = new ImageView();
		txtfStatus.setStyle("-fx-background-color: #FFF;");

	}

	public void PhotoChanger() {
		System.out.println("Trying o open image..");

		// Create a FileChooser, to let the user choose an image
		FileChooser fc = new FileChooser();
		fc.setTitle("Select an image file for your profile:");

		// Set the extension filter to show only image files
		FileChooser.ExtensionFilter imgFilter = new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.jpeg",
				"*.png");
		fc.getExtensionFilters().add(imgFilter);

		File file;

		// check if the user selects a file, and confirmed the dialog
		if ((file = fc.showOpenDialog(null)) != null) { // user chose a file

			// construct an image with the file URI as an argument
			img = new Image(file.toURI().toString()); // user chosen photo

			// We have an image. Out it into the ImageView
			imvProfilePhoto.setImage(img);

		} else
			; // user did not choose a file and confirm - do nothing
	} // PhotoChanger()
		// method for an extensive dialog

	public void showEdit() {
		System.out.println("Edit has been clicked!");

		// create secondary stage
		Stage editStage = new Stage();

		// manage dialog's default size
		editStage.setWidth(350);
		editStage.setHeight(200);

		// set the title
		editStage.setTitle("Please update your details.");

		// create a layout for the dialog (gridPane)
		GridPane gpDialog = new GridPane();

		// set the attributes of the GridPane
		gpDialog.setPadding(new Insets(10));
		gpDialog.setHgap(10);
		gpDialog.setVgap(10);

		// create controls for the dialog layout
		TextField txtfName = new TextField();
		Label lblname = new Label("First name:");
		Label lblstudent = new Label("Student Number:");
		TextField txtfstudent = new TextField();
		Label lblprogramme = new Label("Programme of Study:");
		TextField txtfprogramme = new TextField();
		Button btnCancel = new Button("Cancel");
		Button btnOk = new Button("Ok");
		
		//Edit buttons' color
		btnOk.setStyle("-fx-background-color: #76CC12;");
		btnCancel.setStyle("-fx-background-color: red;");
		
		//Edit text fields' color
		txtfName.setStyle("-fx-background-color: white;");
		txtfprogramme.setStyle("-fx-background-color: white;");
		txtfstudent.setStyle("-fx-background-color: white;");
		
		// manage button sizes
		btnCancel.setMinWidth(60);
		btnOk.setMinWidth(80);
		
		// add controls to the layout
		// row 0
		gpDialog.add(lblname, 0, 0);
		gpDialog.add(txtfName, 1, 0);
		// row 1
		gpDialog.add(lblstudent, 0, 1);
		gpDialog.add(txtfstudent, 1, 1);
		// row 2
		gpDialog.add(lblprogramme, 0, 2);
		gpDialog.add(txtfprogramme, 1, 2);
		// row 3
		gpDialog.add(btnCancel, 1, 3);
		gpDialog.add(btnOk, 1, 3);

		gpDialog.setHalignment(btnOk, HPos.RIGHT);
		// Manage button events
		btnCancel.setOnAction(ae -> editStage.close()); // close the dialog
		
		btnOk.setOnAction(ae -> {
			// local variable, store name temporarily
			String fullname = txtfName.getText();

			// get the student number
			String studentNumber = txtfstudent.getText();

			// get programme of study
			String programme = txtfprogramme.getText();

			// show the name on the main interface
			lblName.setText(fullname);

			lblStudentNum.setText(studentNumber);

			lblProgramme.setText(programme);

			// close the dialog
			editStage.close();
		});

		// create a scene for the dialog
		Scene sedit = new Scene(gpDialog); // takes in the root layout

		// set the scene for the dialog
		editStage.setScene(sedit);
		// Apply a style using a style sheet 
		sedit.getStylesheets().add("style_BookFace.css");
		// show the dialog stage
		editStage.show();

	} // showEdit()

	// init() - EVENT HANDLING
	public void init() {

		// Handle events on the Update Status button
		btnUpdate.setOnAction(event -> {
			// when buttonUpdate is clicked, what happen is everything between these
			// brackets
			System.out.println("Update button has been clicked!");
			// Show a greeting in the label
			lblMyStatus.setText("Today I Feel " + txtfStatus.getText()); // Today I Feel...
		});
		// Handle events on the Update Status button
		btnChangePhoto.setOnAction(event -> PhotoChanger());
		// Handle events on the Edit Pofile button
		btnEditProfile.setOnAction(event -> showEdit());
	} // init()

	// Start()
	@Override
	public void start(Stage primaryStage) {
		// set the title
		primaryStage.setTitle("BookFace	Social Media Application");

		// set the width and height.
		primaryStage.setHeight(550);
		primaryStage.setWidth(800);

		// Create a layout.
		BorderPane bpMain = new BorderPane();

		// Grid Pane for Left side
		GridPane gp = new GridPane();
		// Grid Pane for Right side
		GridPane gp2 = new GridPane();

		// Define image
		Image img = new Image("logo1.jpg"); // !!!
		imvProfilePhoto = new ImageView(img);
		// Image size
		imvProfilePhoto.setFitWidth(200);
		imvProfilePhoto.setFitHeight(200);

		// Set gap and padding
		gp.setHgap(20); // space between colms
		gp.setVgap(10); // space between rows
		gp.setPadding(new Insets(10));

		gp2.setHgap(10); // space between colms
		gp2.setVgap(10); // space between rows
		gp2.setPadding(new Insets(10, 80, 0, 10));
		// And components to the layout.

		// Components of left side
		gp.add(lblEnterStatus, 0, 0);
		gp.add(txtfStatus, 0, 1);
		gp.add(btnUpdate, 0, 2);
		gp.add(lblMyStatus, 0, 40);
		// Components of right side
		gp2.add(imvProfilePhoto, 0, 3);
		gp2.add(btnChangePhoto, 0, 4);
		gp2.add(lblName, 0, 7);
		gp2.add(lblStudentNum, 0, 8);
		gp2.add(lblProgramme, 0, 9);
		gp2.add(btnEditProfile, 0, 10);
		// set alignment for buttons
		gp2.setHalignment(btnChangePhoto, HPos.CENTER);
		gp2.setHalignment(btnEditProfile, HPos.RIGHT);
		bpMain.setLeft(gp);
		bpMain.setRight(gp2);

		// Create a scene.
		Scene s = new Scene(bpMain);

		// Set the scene
		primaryStage.setScene(s);

		// Apply a style using a style sheet 
		s.getStylesheets().add("style_BookFace.css");

		// Show the stage
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
