package application;

import java.util.Random;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.animation.PauseTransition;
import javafx.util.Duration;

enum Gender {
	MALE, FEMALE
}

public class Game extends Application {
	private static final int SCENE_WIDTH = 1000;
	private static final int SCENE_HEIGHT = 600;
	Alert errorAlert = new Alert(AlertType.ERROR);
	Alert infoAlert = new Alert(AlertType.INFORMATION);
	Coins coins = new Coins();
	String userChoice;
	Gender selectedGenderPlayer1 = null;
	Gender selectedGenderPlayer2 = null;
	boolean isClick = false;
	GridPane coinGrid = new GridPane();
	TextField enterNumbersTF = new TextField();
	TextField selectNumbersOfCoinsTF = new TextField();
	private int[] player1Selections;
	private int[] player2Selections;
	Label playerOneNameLabel = new Label();
	Label playerTwoNameLabel = new Label();
	Button showNumber = new Button("Results");
	private GridPane playerOneNumbers = new GridPane();
	private GridPane playerTwoNumbers = new GridPane();
	private int player1Score = 0;
	private int player2Score = 0;
	private int turn = 1;
	TextField playerOneNameTF = new TextField();
	TextField playerTwoNameTF = new TextField();
	BorderPane gameBetweenTwoPlayersBP = new BorderPane();
	GridPane gridPane = new GridPane();
	private Label resultLabel = new Label();

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub

		///////////////// SCOND SCREEN

		Button selectOnePlayer = new Button("One Player");
		selectOnePlayer.setStyle("-fx-background-color: rgb(1, 223, 148); " + "-fx-background-radius: 10em; "
				+ "-fx-padding: 10px 30px; " + "-fx-border-width: 3; " + "-fx-border-color: rgb(0, 0, 0); "
				+ "-fx-font-size: 30px; " + "-fx-font-family: 'Arial'; " + "-fx-text-fill: white; "
				+ "-fx-font-weight: bold;-fx-border-color: transparent; -fx-font-family: 'Courier New';");

		Button selectTwoPlayer = new Button("Two Players");
		selectTwoPlayer.setStyle("-fx-background-color: rgb(1, 223, 148); " + "-fx-background-radius: 10em; "
				+ "-fx-padding: 10px 30px; " + "-fx-border-width: 3; " + "-fx-border-color: rgb(0, 0, 0); "
				+ "-fx-font-size: 30px; " + "-fx-font-family: 'Arial'; " + "-fx-text-fill: white; "
				+ "-fx-font-weight: bold;-fx-border-color: transparent; -fx-font-family: 'Courier New';");
		Button restartForFirstScreen = new Button("Restart");
		restartForFirstScreen.setStyle("-fx-background-color: rgb(1, 223, 148); " + "-fx-background-radius: 10em; "
				+ "-fx-padding: 10px 30px; " + "-fx-border-width: 3; " + "-fx-border-color: rgb(0, 0, 0); "
				+ "-fx-font-size: 30px; " + "-fx-font-family: 'Arial'; " + "-fx-text-fill: white; "
				+ "-fx-font-weight: bold;-fx-border-color: transparent; -fx-font-family: 'Courier New';");

		HBox selectNumberOfPlayersHB = new HBox(20);
		selectNumberOfPlayersHB.getChildren().addAll(selectOnePlayer, selectTwoPlayer);
		selectNumberOfPlayersHB.setAlignment(Pos.CENTER);
		VBox optionesVB = new VBox(20);
		optionesVB.getChildren().addAll(selectNumberOfPlayersHB, restartForFirstScreen);
		optionesVB.setAlignment(Pos.CENTER);

		BorderPane selectNumberOfPlayersBP = new BorderPane();
		selectNumberOfPlayersBP.setBottom(optionesVB);
		selectNumberOfPlayersBP.setAlignment(optionesVB, Pos.CENTER);
		selectNumberOfPlayersBP.setPadding(new Insets(130, 15, 100, 50));
		selectNumberOfPlayersBP.setStyle("-fx-background-color: rgb(0, 153, 0)");
		selectNumberOfPlayersBP.setStyle("-fx-background-image: url('selectNumberOfPlayers.jpg');"
				+ "-fx-background-size: stretch;" + "-fx-background-position: center center;");
		Scene SelectNumberOfPlayersScene = new Scene(selectNumberOfPlayersBP, SCENE_WIDTH, SCENE_HEIGHT);

		///////////////// SCREEN TWO Player

		/*
		 * 
		 * This screen appears after the user has chosen the 2 players option, in which
		 * he fills in the players’ names through the text file and chooses the gender
		 * of each player through the check box in which the enum was used as constants
		 * for time and space. After that, the user presses Enter, which verifies that
		 * the user has filled in the information so that he can move to
		 * the next screen.
		 * 
		 */

		GridPane playersInfoGridPane = new GridPane();

		Label playerNameInfoLabel = new Label("Name");
		playerNameInfoLabel.setStyle(
				"-fx-font-size: 30px;-fx-font-weight: bold;  -fx-font-family: 'Courier New'; -fx-text-fill: white;");

		Label playerOneInfoLabel = new Label("Player One");
		playerOneInfoLabel.setStyle(
				"-fx-font-size: 40px;-fx-font-weight: bold;  -fx-font-family: 'Courier New';-fx-text-fill: white;");

		playerOneNameTF.setPromptText("Name");
		playerOneNameTF.setStyle(
				"-fx-border-color: rgb(21, 32, 50); -fx-border-width: 4; -fx-font-size: 15;-fx-font-family: 'Courier New';-fx-text-fill: rgb(21, 32, 50);");

		Label playerTwoInfoLabel = new Label("Player Two");
		playerTwoInfoLabel.setStyle(
				"-fx-font-size: 40px;-fx-font-weight: bold;-fx-font-family: 'Courier New';-fx-text-fill: white;");
		playerTwoNameTF.setPromptText("Name");
		playerTwoNameTF.setStyle(
				"-fx-border-color:rgb(21, 32, 50); -fx-border-width: 4; -fx-font-size: 15;-fx-font-family: 'Courier New';-fx-text-fill: rgb(21, 32, 50);");

		Label playerGenderInfoLabel = new Label("Gender");
		playerGenderInfoLabel.setStyle(
				"-fx-font-size: 30px;-fx-font-weight: bold;-fx-font-family: 'Courier New';-fx-text-fill: white;");

		CheckBox player1Male = new CheckBox("Male");
		player1Male.setFont(Font.font("Courier New", FontWeight.BOLD, 15));
		CheckBox player1Female = new CheckBox("Female");
		player1Female.setFont(Font.font("Courier New", FontWeight.BOLD, 15));
		VBox player1Box = new VBox(10, player1Male, player1Female);
		player1Box.setAlignment(Pos.CENTER_LEFT);

		// Player 2 section
		CheckBox player2Male = new CheckBox("Male");
		player2Male.setFont(Font.font("Courier New", FontWeight.BOLD, 15));
		CheckBox player2Female = new CheckBox("Female");
		player2Female.setFont(Font.font("Courier New", FontWeight.BOLD, 15));
		VBox player2Box = new VBox(10, player2Male, player2Female);
		player2Box.setAlignment(Pos.CENTER_LEFT);

		Button enterPlayersInofButton = new Button("Enter");
		enterPlayersInofButton.setStyle(
				"-fx-border-width: 3px;-fx-border-color: white;-fx-background-color: rgb(21, 32, 50);  -fx-font-family: 'Courier New';"
						+ " -fx-padding: 10px 10px;"
						+ "-fx-border-width: 3; -fx-font-size: 40px ;-fx-text-fill: white;-fx-font-weight: bold;");

		Button backButtonInfoSceneButton = new Button("Back");
		backButtonInfoSceneButton.setStyle(
				"-fx-border-width: 3px;-fx-border-color: white;-fx-background-color: rgb(21, 32, 50);  -fx-font-family: 'Courier New';"
						+ " -fx-padding: 10px 10px;"
						+ "-fx-border-width: 3; -fx-font-size: 40px ;-fx-text-fill: white;-fx-font-weight: bold;");

		backButtonInfoSceneButton.setOnAction(event -> {
			primaryStage.setScene(SelectNumberOfPlayersScene);
			userChoice = "";
			enterNumbersTF.clear();
			selectNumbersOfCoinsTF.clear();
			coinGrid.getChildren().clear();
		});

		playersInfoGridPane.add(playerNameInfoLabel, 0, 1);
		playersInfoGridPane.add(playerOneInfoLabel, 1, 0);
		playersInfoGridPane.add(playerOneNameTF, 1, 1);
		playersInfoGridPane.add(playerTwoInfoLabel, 2, 0);
		playersInfoGridPane.add(playerTwoNameTF, 2, 1);
		playersInfoGridPane.add(playerGenderInfoLabel, 0, 2);
		playersInfoGridPane.add(player1Box, 1, 2);
		playersInfoGridPane.add(player2Box, 2, 2);

		playersInfoGridPane.add(enterPlayersInofButton, 3, 0);
		playersInfoGridPane.add(backButtonInfoSceneButton, 3, 1);

		playersInfoGridPane.setVgap(30);
		playersInfoGridPane.setHgap(50);

		playersInfoGridPane.setAlignment(Pos.CENTER);
		BorderPane playersInfoBP = new BorderPane();
		playersInfoBP.setCenter(playersInfoGridPane);
		playersInfoBP.setAlignment(playersInfoGridPane, Pos.CENTER_LEFT);

		playersInfoBP.setStyle("-fx-background-color: rgb(1, 223, 148)");

		playersInfoBP.setPadding(new Insets(0, 500, 300, 100));
		Scene twoPlayerInfoScene = new Scene(playersInfoBP, SCENE_WIDTH, SCENE_HEIGHT);
		selectTwoPlayer.setOnAction(event -> {
			userChoice = "Two player";
			primaryStage.setScene(twoPlayerInfoScene);
			playerOneNameTF.setText("");
			playerTwoNameTF.setText("");
			player1Male.setSelected(false);
			player1Female.setSelected(false);
			player2Male.setSelected(false);
			player2Female.setSelected(false);
			primaryStage.setScene(twoPlayerInfoScene);
			enterNumbersTF.clear();
			selectNumbersOfCoinsTF.clear();
			coinGrid.getChildren().clear();
			isClick = false;
		});

		EventHandler<MouseEvent> backHandler = event -> {

			if (userChoice == "Two player") {
				primaryStage.setScene(twoPlayerInfoScene);
			} else {
				primaryStage.setScene(SelectNumberOfPlayersScene);
			}
		};

		//////////////// FORTH SCREEN

		/*
		 * This scene will appear to the user whether it is a single
		 * player or two players. In this scene, the user will be shown the method of
		 * choosing the source of the numbers for the coins, which is 3 methods:
		 * manually, randomly, and reading from a file. He will determine his choice by
		 * means of the radio button, then he will click on the circle to confirm his
		 * choice. After that, the textField will appear according to his choice. If he
		 * chooses manually, two textFields will appear for him, the first to choose the
		 * number of coins so that it is an even number, and the second to enter the
		 * currency numbers with the same number in the first textField. However, if he
		 * chooses randomly, only the first textField will appear for him. However, if
		 * he chooses the file, he will not be asked to enter any information. When he
		 * finishes entering all the information, he must click enter.
		 * 
		 * 
		 * There are some possibilities that the user can do
		 * 
		 * first. He can click on the circle to confirm his choice without choosing the
		 * source of the numbers. Therefore, a screen will appear explaining this by
		 * using the alert type of error.
		 * 
		 * Secondly, if the user chooses the random option and does not enter the number
		 * of numbers, an error alert screen will appear, as when he chooses the manual
		 * option. In addition, the second input, which is the numbers, will be verified
		 * in the same way as the first.
		 * 
		 * 
		 * 
		 * Each coin number will appear in a box.
		 * 
		 * 
		 */

		GridPane randomNumbersGP = new GridPane();
		randomNumbersGP.setHgap(5);
		randomNumbersGP.setVgap(5);
//		randomNumbersGP.setPrefSize(800, 400);

		Label selectNumbersLabel = new Label("What source of coins do you want?");
		selectNumbersLabel.setStyle(
				"-fx-font-size: 40px; -fx-font-weight: bold; -fx-font-family: 'Courier New'; -fx-text-fill: white;");

		RadioButton selectNumbersManualRB = new RadioButton("Manual");
		selectNumbersManualRB.setStyle("-fx-font-size: 20px; -fx-padding: 10px 20px;-fx-font-family: 'Courier New';");

		RadioButton selectNumbersRandomRB = new RadioButton("Random");
		selectNumbersRandomRB.setStyle("-fx-font-size: 20px; -fx-padding: 10px 20px;-fx-font-family: 'Courier New';");

		ToggleGroup selectNumbersTG = new ToggleGroup();
		selectNumbersManualRB.setToggleGroup(selectNumbersTG);
		selectNumbersRandomRB.setToggleGroup(selectNumbersTG);

		selectNumbersOfCoinsTF.setVisible(false);
		selectNumbersOfCoinsTF.setPromptText("Even number of coins");
		selectNumbersOfCoinsTF.setStyle(" -fx-border-width: 4; -fx-font-size: 15");

		enterNumbersTF.setVisible(false);
		enterNumbersTF.setPromptText("Coins numbers are in the form n1,n2,n3,...");
		enterNumbersTF.setStyle(" -fx-border-width: 4; -fx-font-size: 15");

		selectNumbersTG.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue == selectNumbersManualRB || newValue == selectNumbersRandomRB) {
				selectNumbersOfCoinsTF.setVisible(true);
				if (newValue == selectNumbersManualRB) {
					enterNumbersTF.setVisible(true);
				} else
					enterNumbersTF.setVisible(false);

			} else {
				selectNumbersOfCoinsTF.setVisible(false);
				enterNumbersTF.setVisible(false);

			}
			RadioButton selectedRadioButton = (RadioButton) newValue;
			String selectedNumbersOptionString = selectedRadioButton.getText();
		});

		Button ConfirmButton = new Button("Confirm Action");

		ConfirmButton
				.setStyle("-fx-border-width: 3px;-fx-border-color: transparent;-fx-background-color: rgb(21, 32, 50);"
						+ " -fx-background-radius: 10em;-fx-padding: 10px 10px;"
						+ "-fx-border-width: 3; -fx-font-size: 15px ;-fx-text-fill: white;-fx-font-weight: bold; -fx-font-family: 'Courier New';");

		Button nextButton = new Button("Next");

		nextButton.setStyle("-fx-border-width: 3px;-fx-border-color: transparent;-fx-background-color: rgb(21, 32, 50);"
				+ " -fx-background-radius: 10em;-fx-padding: 10px 10px;"
				+ "-fx-border-width: 3; -fx-font-size: 15px ;-fx-text-fill: white;-fx-font-weight: bold; -fx-font-family: 'Courier New';");

		Button backButton = new Button("Back");
		backButton.setStyle("-fx-border-width: 3px;-fx-border-color: transparent;-fx-background-color: rgb(21, 32, 50);"
				+ " -fx-background-radius: 10em;-fx-padding: 10px 10px;"
				+ "-fx-border-width: 3; -fx-font-size: 15px ;-fx-text-fill: white;-fx-font-weight: bold; -fx-font-family: 'Courier New';");
		HBox selectNumbersHB = new HBox(15);
		selectNumbersHB.getChildren().addAll(selectNumbersManualRB, selectNumbersRandomRB, ConfirmButton, nextButton,
				backButton);
		selectNumbersHB.setAlignment(Pos.CENTER_LEFT);

		VBox selectNumbersVB = new VBox(50);

		selectNumbersVB.setAlignment(Pos.CENTER_LEFT);

		EventHandler<MouseEvent> clickHandler = event -> {

			if (selectNumbersManualRB.isSelected()) {
				if (selectNumbersOfCoinsTF.getText().isEmpty()) {
					errorAlert.setTitle("Error in choosing the number of coins");
					errorAlert.setContentText("You must choose an even number to complete the process.");
					errorAlert.show();
				} else if (enterNumbersTF.getText().isEmpty()) {
					errorAlert.setTitle("Error in entering coins numbers");
					errorAlert.setContentText(
							"Please enter the numbers according to the required format to complete the process.");
					errorAlert.show();
				} else {
					try {
						if (Integer.parseInt(selectNumbersOfCoinsTF.getText()) % 2 == 0) {
							coins.setN(Integer.parseInt(selectNumbersOfCoinsTF.getText()));
							String text = enterNumbersTF.getText();
							String[] numbers = text.split(",");

							int[] intArray = new int[numbers.length];
							if (intArray.length == Integer.parseInt(selectNumbersOfCoinsTF.getText())) {
								for (int i = 0; i < numbers.length; i++) {
									intArray[i] = Integer.parseInt(numbers[i].trim());
								}
								coins.setCoins(intArray);

								randomNumbersGP.getChildren().clear();

								int columnIndex = 0;
								int rowIndex = 0;

								GridPane coinGrid = new GridPane();
								for (int i = 0; i < coins.getN(); i++) {
									int number = coins.getCoins()[i];
									Text numberText = new Text(String.valueOf(number));
									numberText.setStyle(
											"-fx-font-size: 24px; -fx-font-weight: bold; -fx-font-family: 'Courier New';");

									StackPane framedNumberSP = new StackPane(numberText);
									framedNumberSP.setPadding(new Insets(10));
									framedNumberSP.setStyle("-fx-border-color: rgb(21, 32, 50); -fx-border-width: 2;");
									framedNumberSP.setPrefWidth(80);
									framedNumberSP.setPrefHeight(80);

									coinGrid.add(framedNumberSP, columnIndex, rowIndex);
									columnIndex++;

									if (columnIndex == 7) {
										rowIndex++;
										columnIndex = 0;
									}
								}

								ScrollPane scrollPane = new ScrollPane();
								scrollPane.setContent(coinGrid);
								scrollPane.setFitToWidth(true);
								scrollPane.setFitToHeight(true);

								int rowHeight = 90;
								int visibleRows = 7;
								scrollPane.setPrefHeight(rowHeight * visibleRows);

								scrollPane.setPrefWidth(600);

								randomNumbersGP.getChildren().add(scrollPane);
								randomNumbersGP.setPrefWidth(800);
								randomNumbersGP.setPrefHeight(600);
								scrollPane.setStyle(
										"-fx-background: rgb(1, 223, 148); -fx-background-color: rgb(1, 223, 148);");

								coinGrid.setPrefWidth(800);
								coinGrid.setPrefHeight(600);

							} else {
								errorAlert.setTitle("Error in entering coins numbers");
								errorAlert.setContentText(
										"The number of coins entered does not match the selected value. Please check.");
								errorAlert.show();
							}
						} else {
							errorAlert.setTitle("Error in entering the number of coins");
							errorAlert.setContentText("You must enter an even number for the number of coins.");
							errorAlert.show();
						}
					} catch (Exception e) {
						exceptionAlertMethod(e);
					}
				}
				isClick = true;
			} else if (selectNumbersRandomRB.isSelected()) {
				if (selectNumbersOfCoinsTF.getText().isEmpty()) {
					errorAlert.setTitle("Error in choosing the number of coins");
					errorAlert.setContentText("You must choose an even number to complete the process.");
					errorAlert.show();
				} else {
					if (Integer.parseInt(selectNumbersOfCoinsTF.getText()) % 2 == 0) {
						coins.setN(Integer.parseInt(selectNumbersOfCoinsTF.getText()));

						int[] randomNumbers = new int[Integer.parseInt(selectNumbersOfCoinsTF.getText())];

						randomNumbersGP.getChildren().clear();

						Random random = new Random();
						int columnIndex = 0;
						int rowIndex = 0;

						for (int i = 0; i < randomNumbers.length; i++) {
							int randomNumber = random.nextInt(100) + 1;
							randomNumbers[i] = randomNumber;

							Text numberText = new Text(String.valueOf(randomNumber));
							numberText.setStyle(
									"-fx-font-size: 24px; -fx-font-weight: bold; -fx-font-family: 'Courier New';");

							StackPane framedNumberSP = new StackPane(numberText);
							framedNumberSP.setPadding(new Insets(10));
							framedNumberSP.setStyle("-fx-border-color: rgb(21, 32, 50); -fx-border-width: 2;");
							framedNumberSP.setPrefWidth(80);
							framedNumberSP.setPrefHeight(80);
							coinGrid.add(framedNumberSP, columnIndex, rowIndex);
							columnIndex++;

							if (columnIndex == 7) {
								rowIndex++;
								columnIndex = 0;
							}
						}

						ScrollPane scrollPane = new ScrollPane();
						scrollPane.setContent(coinGrid);
						scrollPane.setFitToWidth(true);
						scrollPane.setFitToHeight(true);

						int rowHeight = 90;
						int visibleRows = 7;
						scrollPane.setPrefHeight(rowHeight * visibleRows);

						scrollPane.setPrefWidth(600);

						randomNumbersGP.getChildren().add(scrollPane);
						randomNumbersGP.setPrefWidth(800);
						randomNumbersGP.setPrefHeight(600);
						scrollPane
								.setStyle("-fx-background: rgb(1, 223, 148); -fx-background-color: rgb(1, 223, 148);");

						coinGrid.setPrefWidth(800);
						coinGrid.setPrefHeight(600);

						coins.setCoins(randomNumbers);
					} else {
						errorAlert.setTitle("Error in entering the number of coins");
						errorAlert.setContentText("You must enter an even number for the number of coins.");
						errorAlert.show();
					}
					isClick = true;
				}
			} else {
				errorAlert.setTitle("Error while selecting the number source for coins");
				errorAlert.setContentText("You must choose the source of numbers for coins to complete the process.");
				errorAlert.show();
			}
		};

		ConfirmButton.setOnMouseClicked(clickHandler);

		selectNumbersVB.getChildren().addAll(selectNumbersLabel, selectNumbersHB, selectNumbersOfCoinsTF,
				enterNumbersTF, randomNumbersGP);
		BorderPane selectNumbersBP = new BorderPane();
		selectNumbersBP.setLeft(selectNumbersVB);
		selectNumbersBP.setAlignment(selectNumbersVB, Pos.CENTER_LEFT);
		selectNumbersBP.setPadding(new Insets(0, 500, 300, 100));
		selectNumbersBP.setStyle("-fx-background-color: rgb(1, 223, 148)");

		Scene selectSourceNumbersScene = new Scene(selectNumbersBP, SCENE_WIDTH, SCENE_HEIGHT);

		selectOnePlayer.setOnAction(event -> {
			userChoice = "One player";
			primaryStage.setScene(selectSourceNumbersScene);
			enterNumbersTF.clear();
			selectNumbersOfCoinsTF.clear();
			coinGrid.getChildren().clear();
			isClick = false;
		});

		enterPlayersInofButton.setOnAction(event -> {
			if (playerOneNameTF.getText().isEmpty() || playerTwoNameTF.getText().isEmpty()) {
				errorAlert.setTitle("Error entering player name");
				errorAlert.setContentText("You must enter the player's name to complete the process.");
				errorAlert.show();
			} else if ((!player1Male.isSelected() && !player1Female.isSelected())
					|| (!player2Male.isSelected() && !player2Female.isSelected())) {
				errorAlert.setTitle("Error selecting player gender");
				errorAlert.setContentText("You must select the player's gender to complete the process.");
				errorAlert.show();
			} else {
				if (player1Male.isSelected()) {
					selectedGenderPlayer1 = Gender.MALE;
				} else if (player1Female.isSelected()) {
					selectedGenderPlayer1 = Gender.FEMALE;
				}
				if (player2Male.isSelected()) {
					selectedGenderPlayer2 = Gender.MALE;
				} else if (player2Female.isSelected()) {
					selectedGenderPlayer2 = Gender.FEMALE;
				}
				infoAlert.setTitle("Players Data");
				infoAlert.setContentText("Player data has been entered successfully.\nPlayer One: "
						+ playerOneNameTF.getText() + "," + (selectedGenderPlayer1 == Gender.MALE ? "Male" : "Female")
						+ ".\nPlayer Two:" + playerTwoNameTF.getText() + ","
						+ (selectedGenderPlayer2 == Gender.MALE ? "Male" : "Female"));
				infoAlert.showAndWait();
				primaryStage.setScene(selectSourceNumbersScene);
			}
		});

		//////////////// THE GAME IS BETWEEN TWO PLAYERS SCREEN

		/*
		 * 
		 * 
		 * 
		 * */

		BorderPane onePlayerGameBP = new BorderPane();
		Scene onePlayerGameScene = new Scene(onePlayerGameBP, SCENE_WIDTH, SCENE_HEIGHT);
		Scene gameBetweenTwoPlayersScene = new Scene(gameBetweenTwoPlayersBP, SCENE_WIDTH, SCENE_HEIGHT);
		Button BackGameButton = new Button("Back");

		BackGameButton.setOnAction(event -> {
			primaryStage.setScene(selectSourceNumbersScene);
			enterNumbersTF.clear();
			selectNumbersOfCoinsTF.clear();
			coinGrid.getChildren().clear();
			isClick = false;
		});

		BackGameButton
				.setStyle("-fx-border-width: 3px;-fx-border-color: transparent;-fx-background-color: rgb(21, 32, 50);"
						+ " -fx-background-radius: 10em;-fx-padding: 10px 10px;"
						+ "-fx-border-width: 3; -fx-font-size: 15px ;-fx-text-fill: white;-fx-font-weight: bold; -fx-font-family: 'Courier New';");

		Button RestatGameButton = new Button("Restart");

		RestatGameButton.setOnAction(event -> {
			playerOneNameTF.setText("");
			playerTwoNameTF.setText("");
			player1Male.setSelected(false);
			player1Female.setSelected(false);
			player2Male.setSelected(false);
			player2Female.setSelected(false);
			primaryStage.setScene(twoPlayerInfoScene);
			enterNumbersTF.clear();
			selectNumbersOfCoinsTF.clear();
			coinGrid.getChildren().clear();
			isClick = false;
		});

		RestatGameButton
				.setStyle("-fx-border-width: 3px;-fx-border-color: transparent;-fx-background-color: rgb(21, 32, 50);"
						+ " -fx-background-radius: 10em;-fx-padding: 10px 10px;"
						+ "-fx-border-width: 3; -fx-font-size: 15px ;-fx-text-fill: white;-fx-font-weight: bold; -fx-font-family: 'Courier New';");

		EventHandler<MouseEvent> nextHandler = event -> {

			if (isClick) {
				showConfirmationDialog();

				if (userChoice.equals("Two player")) {
					Image onePlayerImage = selectedGenderPlayer1 == Gender.MALE ? new Image("/male.png")
							: new Image("/female.png");
					Image twoPlayerImage = selectedGenderPlayer2 == Gender.MALE ? new Image("/male.png")
							: new Image("/female.png");

					// Create ImageViews for player images
					ImageView onePlayerImageView = new ImageView(onePlayerImage);
					ImageView twoPlayerImageView = new ImageView(twoPlayerImage);
					onePlayerImageView.setFitWidth(200);
					onePlayerImageView.setFitHeight(200);
					twoPlayerImageView.setFitWidth(200);
					twoPlayerImageView.setFitHeight(200);

					playerOneNameLabel.setText(playerOneNameTF.getText());
					playerOneNameLabel.setStyle(
							"-fx-font-size: 30px;-fx-font-weight: bold;-fx-text-fill: white;-fx-font-family: 'Courier New';");

					playerTwoNameLabel.setText(playerTwoNameTF.getText());
					playerTwoNameLabel.setStyle(
							"-fx-font-size: 30px;-fx-font-weight: bold;-fx-text-fill: white;-fx-font-family: 'Courier New';");

					GridPane twoPlayerGameGP = new GridPane();

					HBox hBox = new HBox(20);
					hBox.getChildren().addAll(BackGameButton, RestatGameButton, showNumber);
					twoPlayerGameGP.add(hBox, 0, 0);

					twoPlayerGameGP.add(onePlayerImageView, 0, 1);
					twoPlayerGameGP.add(twoPlayerImageView, 1, 1);
					twoPlayerGameGP.add(playerOneNameLabel, 0, 2);
					twoPlayerGameGP.add(playerOneNumbers, 1, 3);
					twoPlayerGameGP.add(playerTwoNameLabel, 1, 2);
					twoPlayerGameGP.add(playerTwoNumbers, 1, 3);

					twoPlayerGameGP.setHgap(200);
					twoPlayerGameGP.setVgap(20);

					showNumber.setVisible(false);
					resultLabel.setVisible(false);

					try {
						if (!selectNumbersOfCoinsTF.getText().isEmpty() || !enterNumbersTF.getText().isEmpty()) {
							StackPane pane = new StackPane();
							displayCoinsInGridPaneIn2Player(gameBetweenTwoPlayersBP, gameBetweenTwoPlayersScene, coins,
									pane);

							showNumber.setOnAction(eventShowResult -> {
								showNumber.setVisible(false);

								resultLabel.setVisible(true);

								String winner = (player1Score > player2Score) ? "Player 1 Wins!" : "Player 2 Wins!";
								String message = winner + "\nPlayer 1's Score: " + player1Score + "\nPlayer 2's Score: "
										+ player2Score;

								resultLabel.setText(message);
								resultLabel.setStyle(
										"-fx-font-size: 25px; -fx-font-weight: bold; -fx-text-fill: white; -fx-font-family: 'Courier New';");

								VBox resultVBox = new VBox(50);
								resultVBox.getChildren().addAll(resultLabel);
								resultVBox.setAlignment(Pos.TOP_CENTER);

								gameBetweenTwoPlayersBP.setCenter(resultVBox);
								gameBetweenTwoPlayersBP.setAlignment(resultVBox, Pos.TOP_CENTER);

							});

						}
					} catch (Exception e) {
						exceptionAlertMethod(e);
					}

					showNumber.setStyle(
							"-fx-border-width: 3px;-fx-border-color: transparent;-fx-background-color: rgb(21, 32, 50);"
									+ " -fx-background-radius: 10em;-fx-padding: 10px 10px;"
									+ "-fx-border-width: 3; -fx-font-size: 15px;-fx-text-fill: white;-fx-font-weight: bold; -fx-font-family: 'Courier New';");

					gameBetweenTwoPlayersBP.setStyle("-fx-background-color: rgb(1, 223, 148)");
					gameBetweenTwoPlayersBP.setLeft(twoPlayerGameGP);

					gameBetweenTwoPlayersBP.setPadding(new Insets(50, 100, 500, 200));
					primaryStage.setScene(gameBetweenTwoPlayersScene);
					primaryStage.setTitle("Two Player Game Scene");

				} else {
					primaryStage.setScene(onePlayerGameScene);
					primaryStage.setTitle("One Player Game");
				}
			} else {
				errorAlert.setTitle("Event confirmation error");
				errorAlert.setContentText("You must first press the Confirm Action button.");
				errorAlert.show();
			}
		};

		nextButton.setOnMouseClicked(nextHandler);

		///////////////// ONE PLAYER SCREEN

		selectOnePlayer.setOnAction(event -> {
			primaryStage.setScene(selectSourceNumbersScene);
			userChoice = "One player";
			primaryStage.setScene(selectSourceNumbersScene);
		});

		onePlayerGameBP.setStyle("-fx-background-color: rgb(1, 223, 148)");

		Image onePlayerImage = new Image("/computer.png");
		Image twoPlayerImage = new Image("/male.png");

		ImageView onePlayerImageView = new ImageView(onePlayerImage);
		ImageView TwoPlayerImageView = new ImageView(twoPlayerImage);
		onePlayerImageView.setFitWidth(200);
		onePlayerImageView.setFitHeight(200);
		TwoPlayerImageView.setFitWidth(150);
		TwoPlayerImageView.setFitHeight(150);

		HBox picturesForGameHB = new HBox();
		picturesForGameHB.setSpacing(200);
		picturesForGameHB.setAlignment(Pos.CENTER_LEFT);

		picturesForGameHB.getChildren().addAll(onePlayerImageView, TwoPlayerImageView);

		Button showNumbersbutton = new Button("Show numbers");
		showNumbersbutton
				.setStyle("-fx-border-width: 3px;-fx-border-color: transparent;-fx-background-color: rgb(21, 32, 50);"
						+ " -fx-background-radius: 10em;-fx-padding: 10px 10px;"
						+ "-fx-border-width: 3; -fx-font-size: 15px ;-fx-text-fill: white;-fx-font-weight: bold; -fx-font-family: 'Courier New';");
		showNumbersbutton.setOnAction(event -> {
			try {
				if (!selectNumbersOfCoinsTF.getText().isEmpty() || !enterNumbersTF.getText().isEmpty()) {
					StackPane pane = new StackPane();
					displayCoinsInGridPane(onePlayerGameBP, onePlayerGameScene, coins, pane);
				}

			} catch (Exception e) {
				exceptionAlertMethod(e);
			}
		});

		Button showDPTableButton = new Button("Show DP Table");
		showDPTableButton
				.setStyle("-fx-border-width: 3px;-fx-border-color: transparent;-fx-background-color: rgb(21, 32, 50);"
						+ " -fx-background-radius: 10em;-fx-padding: 10px 10px;"
						+ "-fx-border-width: 3; -fx-font-size: 15px ;-fx-text-fill: white;-fx-font-weight: bold; -fx-font-family: 'Courier New';");

		Button backToGameBetweenTwoPlayersSceneButton = new Button("Back");
		backToGameBetweenTwoPlayersSceneButton.setStyle("-fx-border-width: 3px; " + "-fx-border-color: transparent; "
				+ "-fx-background-color: rgb(21, 32, 50);" + " -fx-background-radius: 10em; "
				+ "-fx-padding: 10px 10px; " + "-fx-border-width: 3; " + "-fx-font-size: 15px; "
				+ "-fx-text-fill: white; " + "-fx-font-weight: bold; " + "-fx-font-family: 'Courier New';");
		backToGameBetweenTwoPlayersSceneButton.setOnAction(event -> {
			primaryStage.setScene(onePlayerGameScene);
			enterNumbersTF.clear();
			selectNumbersOfCoinsTF.clear();
			coinGrid.getChildren().clear();
			isClick = false;
		});

		showDPTableButton.setOnAction(event -> {
			BorderPane dpTableBorderPane = new BorderPane();
			String style = "-fx-background-color: rgb(1, 223, 148); -fx-font-family: 'Courier New'; -fx-text-fill: white;";
			dpTableBorderPane.setStyle(style);
			dpTableBorderPane.setPadding(new Insets(50, 100, 100, 0));

			BorderPane dpTableWithScrollPane = createDPTableWithScrollPane(coins.getDp());
			dpTableWithScrollPane.setStyle("-fx-background-color: rgb(1, 223, 148)");
			dpTableBorderPane.setCenter(dpTableWithScrollPane);
			dpTableBorderPane.setBottom(backToGameBetweenTwoPlayersSceneButton);

			dpTableBorderPane.setAlignment(backToGameBetweenTwoPlayersSceneButton, Pos.CENTER);
			Scene dpTableScene = new Scene(dpTableBorderPane, SCENE_WIDTH, SCENE_HEIGHT);

			dpTableScene.setFill(Color.rgb(1, 223, 148));
			primaryStage.getScene().setFill(Color.rgb(1, 223, 148));

			primaryStage.setScene(dpTableScene);
			primaryStage.setTitle("Title");
		});

		Button expectedResultButton = new Button("Expected Result");
		expectedResultButton
				.setStyle("-fx-border-width: 3px;-fx-border-color: transparent;-fx-background-color: rgb(21, 32, 50);"
						+ " -fx-background-radius: 10em;-fx-padding: 10px 10px;"
						+ "-fx-border-width: 3; -fx-font-size: 15px ;-fx-text-fill: white;-fx-font-weight: bold; -fx-font-family: 'Courier New';");

		Label expectedResultLabel = new Label();
		expectedResultLabel.setStyle(
				"-fx-font-size: 15px; -fx-font-weight: bold; -fx-font-family: 'Courier New'; -fx-text-fill: white;");
		expectedResultButton.setOnAction(e -> {
			int result = coins.calculateDP();
			expectedResultLabel.setVisible(true);
			expectedResultLabel.setText("Expected Result: " + result);
		});

		Button backSelectSourceNumbersSceneButton = new Button("Back");
		backSelectSourceNumbersSceneButton
				.setStyle("-fx-border-width: 3px;-fx-border-color: transparent;-fx-background-color: rgb(21, 32, 50);"
						+ " -fx-background-radius: 10em;-fx-padding: 10px 10px;"
						+ "-fx-border-width: 3; -fx-font-size: 15px ;-fx-text-fill: white;-fx-font-weight: bold; -fx-font-family: 'Courier New';");
		backSelectSourceNumbersSceneButton.setOnAction(event -> {
			enterNumbersTF.clear();
			selectNumbersOfCoinsTF.clear();
			coinGrid.getChildren().clear();
			primaryStage.setScene(selectSourceNumbersScene);
			isClick = false;
			expectedResultLabel.setVisible(false);
			gridPane.getChildren().clear();

		});

		Button restartButton = new Button("Restart");
		restartButton
				.setStyle("-fx-border-width: 3px;-fx-border-color: transparent;-fx-background-color: rgb(21, 32, 50);"
						+ " -fx-background-radius: 10em;-fx-padding: 10px 10px;"
						+ "-fx-border-width: 3; -fx-font-size: 15px ;-fx-text-fill: white;-fx-font-weight: bold; -fx-font-family: 'Courier New';");
		restartButton.setOnAction(event -> {
			enterNumbersTF.clear();
			selectNumbersOfCoinsTF.clear();
			coinGrid.getChildren().clear();
			primaryStage.setScene(selectSourceNumbersScene);
			isClick = false;
		});

		HBox showButtonsHB = new HBox();
		showButtonsHB.getChildren().addAll(showNumbersbutton, showDPTableButton, expectedResultButton,
				expectedResultLabel, backSelectSourceNumbersSceneButton, restartButton);
		showButtonsHB.setSpacing(30);

		onePlayerGameBP.setLeft(picturesForGameHB);
		onePlayerGameBP.setPadding(new Insets(50, 100, 500, 200));
		onePlayerGameBP.setTop(showButtonsHB);

		onePlayerGameBP.setLeft(picturesForGameHB);

		backButton.setOnAction(event -> {
			if (userChoice == "Two player") {
				primaryStage.setScene(twoPlayerInfoScene);

			} else {

				primaryStage.setScene(SelectNumberOfPlayersScene);
				enterNumbersTF.clear();
				selectNumbersOfCoinsTF.clear();
				coinGrid.getChildren().clear();
			}

		});

		//////////////// FIRST SCREEN

		Button startGameButton = new Button("START");
		startGameButton.setStyle(
				"-fx-border-width: 5px;-fx-border-color: rgb(0, 0, 0);-fx-background-color: transparent; -fx-background-radius: 50em;-fx-padding: 20px 40px;-fx-font-family: 'Courier New';-fx-border-width: 3; -fx-font-size: 40px ;-fx-text-fill: white;-fx-font-weight: bold;");

		startGameButton.setOnAction(event -> {
			primaryStage.setScene(SelectNumberOfPlayersScene);
			primaryStage.setTitle("Player selection stage");

		});
		BorderPane startGameBP = new BorderPane();
		startGameBP.setCenter(startGameButton);
		startGameBP.setStyle("-fx-background-image: url('startGameImage.jpg');-fx-background-size: cover;");
		startGameBP.setAlignment(startGameButton, Pos.CENTER);
		startGameBP.setPadding(new Insets(200, 15, 15, 50));

		Scene startGameScene = new Scene(startGameBP, SCENE_WIDTH, SCENE_HEIGHT);
		restartForFirstScreen.setOnAction(event -> {
			primaryStage.setScene(startGameScene);
			primaryStage.setTitle("Game");

		});

		primaryStage.setScene(startGameScene);
		primaryStage.setTitle("Game");
		primaryStage.show();
	}

	public void exceptionAlertMethod(Exception e) {
		Alert exceptionAlert = new Alert(AlertType.ERROR);
		exceptionAlert.setTitle("Exception Dialog");
		exceptionAlert.setHeaderText("Look, an Exception Dialog");
		exceptionAlert.setContentText(e.getMessage());

		Label labelException = new Label("The exception stacktrace was:");
		TextArea textArea = new TextArea(e.toString());
		BorderPane borderPaneException = new BorderPane();
		borderPaneException.setTop(labelException);
		borderPaneException.setCenter(textArea);

		exceptionAlert.getDialogPane().setExpandableContent(borderPaneException);
		exceptionAlert.show();
	}

	public void displayCoinsInGridPane(BorderPane borderPane, Scene scene, Coins coins, StackPane framedNumberSP) {
		gridPane.setHgap(5);
		gridPane.setVgap(5);

		int columnIndex = 0;
		int rowIndex = 0;
		int MAX_COLUMNS = 10;
		for (int i = 0; i < coins.getN(); i++) {
			final int number = coins.getCoins()[i];
			Text numberText = new Text(String.valueOf(number));
			StackPane numberPane = new StackPane(numberText);
			numberPane.setPadding(new Insets(10));
			numberPane.setStyle("-fx-border-color: rgb(21, 32, 50); -fx-border-width: 5; -fx-font-size: 16px;");

			gridPane.add(numberPane, columnIndex, rowIndex);
			columnIndex++;
			if (columnIndex == MAX_COLUMNS) {
				rowIndex++;
				columnIndex = 0;
			}
		}

		borderPane.setRight(gridPane);
		scene.setRoot(borderPane);
	}

	private void showConfirmationDialog() {
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Confirmation");
		alert.setHeaderText("Are you sure?");
		alert.setContentText("Do you want to proceed with this action? Click 'OK' to confirm or 'Cancel' to abort.");

		ButtonType result = alert.showAndWait().orElse(ButtonType.CANCEL);
	}

	private BorderPane createDPTableWithScrollPane(int[][] dp) {
		ScrollPane scrollPane = new ScrollPane();
		scrollPane.setFitToHeight(true);
		scrollPane.setFitToWidth(false);
		scrollPane.setStyle("-fx-background-color: rgb(1, 223, 148);");

		GridPane grid = new GridPane();
		int rows = dp.length;
		int cols = dp[0].length;

		grid.setStyle("-fx-background-color: rgb(1, 223, 148);");

		// Add column headers (i values)
		for (int i = 0; i < rows; i++) {
			Label label = new Label("i=" + i);
			label.setStyle(
					"-fx-padding: 10px; -fx-font-size: 16px; -fx-font-weight: bold; -fx-font-family: 'Courier New';-fx-text-fill: white;");
			label.setPrefWidth(60);
			label.setPrefHeight(40);
			label.setAlignment(Pos.CENTER);
			grid.add(label, 0, i + 1); // Add i values in the first column
		}

		// Add row headers (j values)
		for (int j = 0; j < cols; j++) {
			Label label = new Label("j=" + j);
			label.setStyle(
					"-fx-padding: 10px; -fx-font-size: 16px; -fx-font-weight: bold; -fx-font-family: 'Courier New';-fx-text-fill: white;");
			label.setPrefWidth(60);
			label.setPrefHeight(40);
			label.setAlignment(Pos.CENTER);
			grid.add(label, j + 1, 0); // Add j values in the first row
		}

		// Add DP values
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				Label label = new Label(String.valueOf(dp[i][j]));
				label.setStyle(
						"-fx-padding: 10px; -fx-font-size: 16px; -fx-font-weight: bold; -fx-font-family: 'Courier New';");
				label.setPrefWidth(60);
				label.setPrefHeight(40);
				label.setAlignment(Pos.CENTER);
				grid.add(label, j + 1, i + 1); // Add dp values in the table cells
			}
		}

		grid.setAlignment(Pos.TOP_LEFT);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setStyle("-fx-background-color: rgb(1, 223, 148);");
		scrollPane.setContent(grid);

		Label titleLabel = new Label("DP TABLE\n\n");
		titleLabel.setStyle(
				"-fx-font-size: 40px; -fx-font-weight: bold; -fx-font-family: 'Courier New';  -fx-text-fill:rgb(21, 32, 50);");
		titleLabel.setAlignment(Pos.CENTER);

		VBox tableContainer = new VBox(10, titleLabel, scrollPane);
		tableContainer.setPadding(new Insets(0, 0, 0, 100));
		tableContainer.setAlignment(Pos.TOP_LEFT);
		tableContainer.setStyle("-fx-background-color: rgb(1, 223, 148);");

		BorderPane borderPane = new BorderPane();
		borderPane.setCenter(tableContainer);
		borderPane.setStyle("-fx-background-color: rgb(1, 223, 148);");

		return borderPane;
	}

	private void displayCoinsInGridPaneIn2Player(BorderPane borderPane, Scene scene, Coins coins, StackPane pane) {
		GridPane gridPane = new GridPane();
		gridPane.setHgap(5);
		gridPane.setVgap(5);

		int columnIndex = 0;
		int rowIndex = 0;
		int MAX_COLUMNS = 10;

		player1Selections = new int[coins.getN()];
		player2Selections = new int[coins.getN()];

		int[] coinArray = coins.getCoins();
		for (int i = 0; i < coins.getN(); i++) {
			final int coin = coinArray[i];

			Text coinText = new Text(String.valueOf(coin));
			coinText.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-font-family: 'Courier New';");

			StackPane coinPane = new StackPane(coinText);
			coinPane.setPadding(new Insets(5));
			coinPane.setStyle("-fx-border-color: rgb(21, 32, 50); -fx-border-width: 5;");
			coinPane.setPrefWidth(60);
			coinPane.setPrefHeight(60);

			gridPane.add(coinPane, columnIndex, rowIndex);
			columnIndex++;

			if (columnIndex == MAX_COLUMNS) {
				rowIndex++;
				columnIndex = 0;
			}

			coinPane.setOnMouseClicked(event -> {
				if (coin == coinArray[0] || coin == coinArray[coins.getN() - 1]) {
					boolean removed = coins.removeCoin(coin);
					if (removed) {
						if (turn == 1) {
							player1Score += coin;
						} else {
							player2Score += coin;
						}
						turn = (turn == 1) ? 2 : 1;

						updateGrid(gridPane, coins);
					} else {
						showAlert("Invalid Selection", "This coin was already removed!");
					}
				} else {
					showAlert("Invalid Selection", "You can only select the first or last coin!");
				}
			});

		}

		borderPane.setRight(gridPane);
		scene.setRoot(borderPane);
	}

	private void initializeCoins(int length) {
		coins = new Coins(length);
		coins.initalizationCoins();
		player1Selections = new int[length];
		player2Selections = new int[length];
	}

	private void updateGrid(GridPane gridPane, Coins coins) {
		gridPane.getChildren().clear();
		displayCoinsInGridPaneIn2Player((BorderPane) gridPane.getParent(), gridPane.getScene(), coins, null);
		if (coins.getN() == 0) {
			PauseTransition pause = new PauseTransition(Duration.seconds(0.5));
			pause.setOnFinished(event -> {
				showNumber.setVisible(true);

			});
			pause.play();
		}
	}

	private String arrayToString(int[] array, int size) {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for (int i = 0; i < size; i++) {
			sb.append(array[i]);
			if (i < size - 1)
				sb.append(", ");
		}
		sb.append("]");
		return sb.toString();
	}

	private void showResults() {

		Label player1ResultLabel = new Label("Player 1: " + playerOneNameTF.getText() + " - Score: " + player1Score);
		player1ResultLabel.setStyle(
				"-fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: white; -fx-font-family: 'Courier New';");

		Label player2ResultLabel = new Label("Player 2: " + playerTwoNameTF.getText() + " - Score: " + player2Score);
		player2ResultLabel.setStyle(
				"-fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: white; -fx-font-family: 'Courier New';");

		String winner = (player1Score > player2Score) ? "Player 1 Wins!" : "Player 2 Wins!";
		Label winnerLabel = new Label(winner);
		winnerLabel.setStyle(
				"-fx-font-size: 30px; -fx-font-weight: bold; -fx-text-fill: gold; -fx-font-family: 'Courier New';");

		VBox resultVBox = new VBox(20);
		resultVBox.getChildren().addAll(player1ResultLabel, player2ResultLabel, winnerLabel);

		gameBetweenTwoPlayersBP.setCenter(resultVBox);
	}

	private void showAlert(String title, String message) {
		Alert alert = new Alert(Alert.AlertType.WARNING);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.showAndWait();
	}

}
