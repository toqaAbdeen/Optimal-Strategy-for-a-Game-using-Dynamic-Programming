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
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

enum Gender {
	MALE, FEMALE
}

public class GameFinalVer extends Application {
	private static final int SCENE_WIDTH = 1000;
	private static final int SCENE_HEIGHT = 600;
	Alert errorAlert = new Alert(AlertType.ERROR);
	Alert warningAlert = new Alert(AlertType.WARNING);
	Alert infoAlert = new Alert(AlertType.INFORMATION);
	Alert exceptionAlert = new Alert(AlertType.ERROR);
	Coins coins = new Coins();
	String userChoice;
	int rowIndex = 0;
	int columnIndex = 0;
	private boolean playerturn = false; // this flag to know which player should play ---> 0 to player one ,1 to player
										// two
	boolean isPlayerOneTurn = true;
	Gender selectedGenderPlayer1 = null;
	Gender selectedGenderPlayer2 = null;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub

		///////////////// SCOND SCREEN

		Button selectOnePlayer = new Button("One Player");
		selectOnePlayer.setStyle("-fx-background-color: rgb(172, 230, 0); " + "-fx-background-radius: 10em; "
				+ "-fx-padding: 10px 30px; " + "-fx-border-width: 3; " + "-fx-border-color: rgb(0, 0, 0); "
				+ "-fx-font-size: 30px; " + "-fx-font-family: 'Arial'; " + "-fx-text-fill: white; "
				+ "-fx-font-weight: bold;-fx-border-color: transparent;");

		Button selectTwoPlayer = new Button("Tow Playeres");
		selectTwoPlayer.setStyle("-fx-background-color: rgb(172, 230, 0); " + "-fx-background-radius: 10em; "
				+ "-fx-padding: 10px 30px; " + "-fx-border-width: 3; " + "-fx-border-color: rgb(0, 0, 0); "
				+ "-fx-font-size: 30px; " + "-fx-font-family: 'Arial'; " + "-fx-text-fill: white; "
				+ "-fx-font-weight: bold;-fx-border-color: transparent;");
		Button restartForFirstScreen = new Button("Restart");
		restartForFirstScreen.setStyle("-fx-background-color: rgb(172, 230, 0); " + "-fx-background-radius: 10em; "
				+ "-fx-padding: 10px 30px; " + "-fx-border-width: 3; " + "-fx-border-color: rgb(0, 0, 0); "
				+ "-fx-font-size: 30px; " + "-fx-font-family: 'Arial'; " + "-fx-text-fill: white; "
				+ "-fx-font-weight: bold;-fx-border-color: transparent;");

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
		playerNameInfoLabel.setStyle("-fx-font-size: 30px;-fx-font-weight: bold;");// fx-font-family: 'Arial';

		Label playerOneInfoLabel = new Label("Player One");
		playerOneInfoLabel.setStyle("-fx-font-size: 40px;-fx-font-weight: bold;");// fx-font-family: 'Arial';

		TextField playerOneNameTF = new TextField();
		playerOneNameTF.setPromptText("Name");
		playerOneNameTF.setStyle("-fx-border-color: rgb(0, 0, 0); -fx-border-width: 4; -fx-font-size: 15");

		Label playerTwoInfoLabel = new Label("Player Two");
		playerTwoInfoLabel.setStyle("-fx-font-size: 40px;-fx-font-weight: bold;");// fx-font-family: 'Arial';

		TextField playerTwoNameTF = new TextField();
		playerTwoNameTF.setPromptText("Name");
		playerTwoNameTF.setStyle("-fx-border-color: rgb(0, 0, 0); -fx-border-width: 4; -fx-font-size: 15");

		Label playerGenderInfoLabel = new Label("Gender");
		playerGenderInfoLabel.setStyle("-fx-font-size: 30px;-fx-font-weight: bold;");// fx-font-family: 'Arial';

		CheckBox player1Male = new CheckBox("Male");
		player1Male.setFont(Font.font("", FontWeight.BOLD, 15));
		CheckBox player1Female = new CheckBox("Female");
		player1Female.setFont(Font.font("", FontWeight.BOLD, 15));
		VBox player1Box = new VBox(10, player1Male, player1Female);
		player1Box.setAlignment(Pos.CENTER_LEFT);

		// Player 2 section
		CheckBox player2Male = new CheckBox("Male");
		player2Male.setFont(Font.font("", FontWeight.BOLD, 15));
		CheckBox player2Female = new CheckBox("Female");
		player2Female.setFont(Font.font("", FontWeight.BOLD, 15));
		VBox player2Box = new VBox(10, player2Male, player2Female);
		player2Box.setAlignment(Pos.CENTER_LEFT);

		Button enterPlayersInofButton = new Button("Enter");
		enterPlayersInofButton.setStyle(
				"-fx-border-width: 3px;-fx-border-color: rgb(0, 0, 0);-fx-background-color: rgb(179, 255, 224);"
						+ " -fx-padding: 10px 10px;"
						+ "-fx-border-width: 3; -fx-font-size: 40px ;-fx-text-fill: black;-fx-font-weight: bold;");

		playersInfoGridPane.add(playerNameInfoLabel, 0, 1);
		playersInfoGridPane.add(playerOneInfoLabel, 1, 0);
		playersInfoGridPane.add(playerOneNameTF, 1, 1);
		playersInfoGridPane.add(playerTwoInfoLabel, 2, 0);
		playersInfoGridPane.add(playerTwoNameTF, 2, 1);
		playersInfoGridPane.add(playerGenderInfoLabel, 0, 2);
		playersInfoGridPane.add(player1Box, 1, 2);
		playersInfoGridPane.add(player2Box, 2, 2);

		playersInfoGridPane.add(enterPlayersInofButton, 3, 0);

		playersInfoGridPane.setVgap(30);
		playersInfoGridPane.setHgap(50);

		playersInfoGridPane.setAlignment(Pos.CENTER);
		BorderPane playersInfoBP = new BorderPane();
		playersInfoBP.setCenter(playersInfoGridPane);
		playersInfoBP.setAlignment(playersInfoGridPane, Pos.CENTER_LEFT);

		playersInfoBP.setStyle("-fx-background-color: rgb(210, 255, 77)");

		playersInfoBP.setPadding(new Insets(0, 500, 300, 100));
		Scene twoPlayerInfoScene = new Scene(playersInfoBP, SCENE_WIDTH, SCENE_HEIGHT);
		selectTwoPlayer.setOnAction(event -> {
			userChoice = "Two player";
			primaryStage.setScene(twoPlayerInfoScene);
		});
		BorderPane test = new BorderPane();
		Scene onePlayerSelectedScene = new Scene(test);
		selectOnePlayer.setOnAction(event -> {
			userChoice = "One player";
			primaryStage.setScene(onePlayerSelectedScene);

		});

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
		selectNumbersLabel.setStyle("-fx-font-size: 40px;-fx-font-weight: bold;");// fx-font-family: 'Arial';

		RadioButton selectNumbersManualRB = new RadioButton("Manual");
		selectNumbersManualRB.setStyle("-fx-font-size: 20px; -fx-padding: 10px 20px;");

		RadioButton selectNumbersRandomRB = new RadioButton("Random");
		selectNumbersRandomRB.setStyle("-fx-font-size: 20px; -fx-padding: 10px 20px;");

		RadioButton selectNumbersFileRB = new RadioButton("File");
		selectNumbersFileRB.setStyle("-fx-font-size: 20px; -fx-padding: 10px 20px;");

		ToggleGroup selectNumbersTG = new ToggleGroup();
		selectNumbersManualRB.setToggleGroup(selectNumbersTG);
		selectNumbersRandomRB.setToggleGroup(selectNumbersTG);
		selectNumbersFileRB.setToggleGroup(selectNumbersTG);

		TextField selectNumbersOfCoinsTF = new TextField();
		selectNumbersOfCoinsTF.setVisible(false);
		selectNumbersOfCoinsTF.setPromptText("Even number of coins");
		selectNumbersOfCoinsTF.setStyle(" -fx-border-width: 4; -fx-font-size: 15");

		TextField enterNumbersTF = new TextField();
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
			System.out.println("Selected Numbers Option: " + selectedNumbersOptionString);
		});

//		Text confirmOptionsText = new Text("Confirm Options");
//		confirmOptionsText.setFont(Font.font("", FontWeight.BOLD, 15));
//
//		Circle confirmOptionsCircle = new Circle(100, 100, 75);
//		confirmOptionsCircle.setFill(Color.rgb(179, 255, 224));
//
//		StackPane confirmOptionsSP = new StackPane(confirmOptionsCircle, confirmOptionsText);

		Text nextText = new Text("Next");
		nextText.setFont(Font.font("", FontWeight.BOLD, 15));

		Circle enterCircle = new Circle(100, 100, 40);
		enterCircle.setFill(Color.rgb(179, 255, 224));

		StackPane enterSP = new StackPane(enterCircle, nextText);

		HBox selectNumbersHB = new HBox(15);
		selectNumbersHB.getChildren().addAll(selectNumbersManualRB, selectNumbersRandomRB, selectNumbersFileRB,
				enterSP);
		selectNumbersHB.setAlignment(Pos.CENTER_LEFT);

		VBox selectNumbersVB = new VBox(50);

		selectNumbersVB.setAlignment(Pos.CENTER_LEFT);

		EventHandler<MouseEvent> clickHandler = event -> {
			System.out.println("Circle clicked!");
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
							coins.setNumberOfCoins(Integer.parseInt(selectNumbersOfCoinsTF.getText()));
							String text = enterNumbersTF.getText();
							String[] numbers = text.split(",");

							int[] intArray = new int[numbers.length];
							System.out.println("intArray.length  : " + intArray.length);
							System.out.println("coins.getNumberOfCoins(): " + coins.getNumberOfCoins());
							if (intArray.length == Integer.parseInt(selectNumbersOfCoinsTF.getText())) {
								for (int i = 0; i < numbers.length; i++) {
									intArray[i] = Integer.parseInt(numbers[i].trim());
								}
								coins.setCoins(intArray);
								System.out.println(coins.toString());
//								confirmOptionsCircle.setFill(Color.rgb(214, 214, 194));
								randomNumbersGP.getChildren().clear();

								for (int i = 0; i < coins.getNumberOfCoins(); i++) {
									int number = coins.getCoins()[i];
									Text numberText = new Text(String.valueOf(number));
									StackPane framedNumberSP = new StackPane(numberText);
									framedNumberSP.setPadding(new Insets(10));
									framedNumberSP.setStyle("-fx-border-color: black; -fx-border-width: 5;");
									randomNumbersGP.add(framedNumberSP, columnIndex, rowIndex);
									columnIndex++;

									if (columnIndex == 10) {
										rowIndex++;
										columnIndex = 0;
									}
								}

							} else if (intArray.length >= Integer.parseInt(selectNumbersOfCoinsTF.getText())) {
								errorAlert.setTitle("Error in entering coins numbers");
								errorAlert.setContentText(
										"It seems that you entered a number(s) that exceed  the number of coins. Please make sure that you have entered correctly to complete the process.");
								errorAlert.show();
							} else {
								errorAlert.setTitle("Error in entering coins numbers");
								errorAlert.setContentText(
										"It seems that there is a missing number(s) for the number of coins. Please make sure that you have entered correctly to complete the process.");
								errorAlert.show();
							}
						} else {
							errorAlert.setTitle("Error in entering the number of coins");
							errorAlert.setContentText(
									"You must enter an even number for the number of coins.Please make sure that you have entered correctlyto complete the process.");
							errorAlert.show();
						}
					} catch (Exception e) {
						exceptionAlertMethod(e);
					}
				}

			} else if (selectNumbersRandomRB.isSelected()) {
				if (selectNumbersOfCoinsTF.getText().isEmpty()) {
					errorAlert.setTitle("Error in choosing the number of coins");
					errorAlert.setContentText("You must choose an even number to complete the process.");
					errorAlert.show();
				} else {
					if (Integer.parseInt(selectNumbersOfCoinsTF.getText()) % 2 == 0) {

						coins.setNumberOfCoins(Integer.parseInt(selectNumbersOfCoinsTF.getText()));

						int[] randomNumbers = new int[Integer.parseInt(selectNumbersOfCoinsTF.getText())];

						randomNumbersGP.getChildren().clear();

						Random random = new Random();
						for (int i = 0; i < randomNumbers.length; i++) {
							int randomNumber = random.nextInt(100) + 1;
							randomNumbers[i] = randomNumber;

							Text numberText = new Text(String.valueOf(randomNumber));
							StackPane framedNumberSP = new StackPane(numberText);
							framedNumberSP.setPadding(new Insets(10));
							framedNumberSP.setStyle("-fx-border-color: black; -fx-border-width: 5;");
							randomNumbersGP.add(framedNumberSP, columnIndex, rowIndex);
							columnIndex++;

							if (columnIndex == 10) {
								rowIndex++;
								columnIndex = 0;
							}
						}

						coins.setCoins(randomNumbers);
					} else {
						errorAlert.setTitle("Error in entering the number of coins");
						errorAlert.setContentText(
								"You must enter an even number for the number of coins.Please make sure that you have entered correctlyto complete the process.");
						errorAlert.show();

					}
				}

			} else if (selectNumbersFileRB.isSelected()) {

			} else {
				errorAlert.setTitle("Error while selecting the number source for coins");
				errorAlert.setContentText("You must choose the source of numbers for coins to complete the process.");
				errorAlert.show();
			}
//			confirmOptionsCircle.setDisable(true);

		};

//		confirmOptionsCircle.setOnMouseClicked(clickHandler);
//		confirmOptionsText.setOnMouseClicked(clickHandler);

		Button button = new Button("Confirm Action");
//		if (selectNumbersManualRB.isSelected() || selectNumbersRandomRB.isSelected()
//				|| selectNumbersFileRB.isSelected())
			button.setOnMouseClicked(clickHandler);

		selectNumbersVB.getChildren().addAll(selectNumbersLabel, selectNumbersHB, selectNumbersOfCoinsTF,
				enterNumbersTF, randomNumbersGP, button);
		BorderPane selectNumbersBP = new BorderPane();
		selectNumbersBP.setLeft(selectNumbersVB);
		selectNumbersBP.setAlignment(selectNumbersVB, Pos.CENTER_LEFT);
		selectNumbersBP.setPadding(new Insets(0, 500, 300, 100));
		selectNumbersBP.setStyle("-fx-background-color: rgb(210, 255, 77)");

		Scene selectSourceNumbersScene = new Scene(selectNumbersBP, SCENE_WIDTH, SCENE_HEIGHT);

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
				infoAlert.show();
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
		BorderPane gameBetweenTwoPlayersBP = new BorderPane();
		Scene gameBetweenTwoPlayersScene = new Scene(gameBetweenTwoPlayersBP, SCENE_WIDTH, SCENE_HEIGHT);

		EventHandler<MouseEvent> enterHandler = event -> {
			
			showConfirmationDialog();
			
			System.out.println("Test");
			System.out.println(coins.getNumberOfCoins() + "   coins.getNumberOfCoins()");
			if (userChoice == "Two player") {
				Image onePlayerImage = null;
				Image twoPlayerImage = null;
				if (selectedGenderPlayer1 == Gender.MALE) {
					onePlayerImage = new Image("/person7.png");
				} else {
					onePlayerImage = new Image("/female.png");

				}
				if (selectedGenderPlayer2 == Gender.MALE) {
					twoPlayerImage = new Image("/person7.png");
				} else {
					twoPlayerImage = new Image("/female.png");

				}

				ImageView onePlayerImageView = new ImageView(onePlayerImage);
				ImageView TwoPlayerImageView = new ImageView(twoPlayerImage);
				onePlayerImageView.setFitWidth(200);
				onePlayerImageView.setFitHeight(200);
				TwoPlayerImageView.setFitWidth(200);
				TwoPlayerImageView.setFitHeight(200);

				Label playerOneNameLabel = new Label();
				playerOneNameLabel.setText(playerOneNameTF.getText());
				playerOneNameLabel.setStyle("-fx-font-size: 30px;-fx-font-weight: bold;-fx-text-fill: Black");

				Label playerTwoNameLabel = new Label();
				playerTwoNameLabel.setText(playerTwoNameTF.getText());
				playerTwoNameLabel.setStyle("-fx-font-size: 30px;-fx-font-weight: bold;-fx-text-fill: Black");

				GridPane twoPlayerGameGP = new GridPane();
				twoPlayerGameGP.add(onePlayerImageView, 0, 0);
				twoPlayerGameGP.add(TwoPlayerImageView, 1, 0);
				twoPlayerGameGP.add(playerOneNameLabel, 0, 1);
				twoPlayerGameGP.add(playerTwoNameLabel, 1, 1);
				twoPlayerGameGP.setHgap(200);
				twoPlayerGameGP.setVgap(20);

				try {

					if (!selectNumbersOfCoinsTF.getText().isEmpty() || !enterNumbersTF.getText().isEmpty()) {
						StackPane pane = new StackPane();
						displayCoinsInGridPane(gameBetweenTwoPlayersBP, gameBetweenTwoPlayersScene, coins, pane);
					}
				} catch (Exception e) {
					exceptionAlertMethod(e);
				}

				gameBetweenTwoPlayersBP.setStyle("-fx-background-color: rgb(210, 255, 77)");
				gameBetweenTwoPlayersBP.setLeft(twoPlayerGameGP);
				gameBetweenTwoPlayersBP.setPadding(new Insets(50, 100, 500, 200));
				primaryStage.setScene(gameBetweenTwoPlayersScene);
				primaryStage.setTitle("gameBetweenTwoPlayersScene");
			} else {
				primaryStage.setScene(onePlayerGameScene);
				primaryStage.setTitle("One Player Game !!!");
			}

		};
		enterCircle.setOnMouseClicked(enterHandler);
		nextText.setOnMouseClicked(enterHandler);

		///////////////// ONE PLAYER SCREEN

		selectOnePlayer.setOnAction(event -> {
			primaryStage.setScene(selectSourceNumbersScene);
		});

		onePlayerGameBP.setStyle("-fx-background-color: rgb(210, 255, 77)");

		Image onePlayerImage = new Image("/computer3.png");
		Image twoPlayerImage = new Image("/person7.png");

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
		showNumbersbutton.setStyle(
				"-fx-border-width: 3px;-fx-border-color: transparent;-fx-background-color: rgb(179, 255, 224);"
						+ " -fx-background-radius: 10em;-fx-padding: 10px 10px;"
						+ "-fx-border-width: 3; -fx-font-size: 15px ;-fx-text-fill: black;-fx-font-weight: bold;");
//		showNumbersbutton.setOnAction(event -> {

		try {

			if (!selectNumbersOfCoinsTF.getText().isEmpty() || !enterNumbersTF.getText().isEmpty()) {
				StackPane pane = new StackPane();
				displayCoinsInGridPane(gameBetweenTwoPlayersBP, gameBetweenTwoPlayersScene, coins, pane);

			}
			System.out.println(coins.getNumberOfCoins() + "   coins.getNumberOfCoins()");
			System.out.println(coins.getCoins()[0] + "    ----  " + coins.getCoins()[coins.getCoins().length - 1]);

		} catch (Exception e) {
			exceptionAlertMethod(e);
		}
//		showNumbersbutton.setDisable(true);

//		});
		onePlayerGameBP.setTop(showNumbersbutton);

		onePlayerGameBP.setLeft(picturesForGameHB);
//		onePlayerGameBP.setAlignment(picturesForGameHB, Pos.CENTER_LEFT);
		onePlayerGameBP.setPadding(new Insets(50, 100, 500, 200));

		//////////////// FIRST SCREEN

		Button startGameButton = new Button("START");
		startGameButton.setStyle(
				"-fx-border-width: 5px;-fx-border-color: rgb(0, 0, 0);-fx-background-color: transparent; -fx-background-radius: 50em;-fx-padding: 20px 40px;fx-font-family: 'Arial';-fx-border-width: 3; -fx-font-size: 40px ;-fx-text-fill: white;-fx-font-weight: bold;");

		startGameButton.setOnAction(event -> {
			primaryStage.setScene(SelectNumberOfPlayersScene);
			primaryStage.setTitle("Player selection stage");

		});
		BorderPane startGameBP = new BorderPane();
		startGameBP.setCenter(startGameButton);
		startGameBP.setStyle("-fx-background-image: url('AlgoPicture.png');-fx-background-size: cover;");
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
//	public int[] fromFileToAges(File filePath) throws Exception {
//		try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
//			String line;
//			while ((line = reader.readLine()) != null) {
//				String[] arr = line.split(",");
//				if (arr.length == 5) {
//					String name = null, eventLocation = null, dataOfDeath = null;
//					int age = 0;
//					char gendar = 0;
//					try {
//						name = arr[0].trim();
//						age = Integer.parseInt(arr[1].trim());
//						eventLocation = arr[2];
//						dataOfDeath = arr[3];
//						gendar = arr[4].charAt(0);
//					} catch (Exception e) {
//						System.err.println("Missing data for: " + name);
////						loadArea.setText("Missing or invalid data for: " + name);
//					}
////					martyr = new Martyr(name, age, eventLocation, dataOfDeath, gendar);
////					list.add(martyr);
////					System.out.println(martyr.toString());
//				}
//			}
//			return list;
//		}
//	}

//	private void loadInputScene(Stage stage) {
//		TextField inputField = new TextField();
//		Button enterButton = new Button("Enter");
//		VBox inputLayout = new VBox(10);
//		inputLayout.getChildren().addAll(new Label("Fill in your information:"), inputField, enterButton);
//		Scene inputScene = new Scene(inputLayout, 300, 200);
//
//		// Action for the Enter button
//		enterButton.setOnAction(event -> {
//			if (userChoice.equals("A")) {
//				loadFinalScene(stage, "You chose Scene A. You entered: " + inputField.getText());
//			} else {
//				loadFinalScene(stage, "You chose Scene B. You entered: " + inputField.getText());
//			}
//		});
//
//		// Switch to the input scene
//		stage.setScene(inputScene);
//	}
	public void displayCoinsInGridPane(BorderPane borderPane, Scene scene, Coins coins, StackPane framedNumberSP) {
		GridPane gridPane = new GridPane();
		gridPane.setHgap(5);
		gridPane.setVgap(5);

		int columnIndex = 0;
		int rowIndex = 0;

		for (int i = 0; i < coins.getNumberOfCoins(); i++) {
			int number = coins.getCoins()[i];
			Text numberText = new Text(String.valueOf(number));
			StackPane numberPane = new StackPane(numberText);
			numberPane.setPadding(new Insets(10));
			numberPane.setStyle("-fx-border-color: black; -fx-border-width: 5;");

			numberPane.setOnMouseClicked(event -> {
				if (isPlayerOneTurn || !isPlayerOneTurn) {
					if (number != coins.getCoins()[0] || number == coins.getCoins()[coins.getCoins().length - 1]) {
						warningAlert.setTitle("Warning when choosing coin");
						warningAlert.setContentText("The player is allowed to choose the first or last coin.");
						warningAlert.show();
					} else if (isPlayerOneTurn) {
						numberPane.setStyle(
								"-fx-background-color: rgb(255, 153, 51);-fx-border-color: black; -fx-border-width: 5;");

					} else {
						numberPane.setStyle(
								"-fx-background-color: rgb(255, 255, 102);-fx-border-color: black; -fx-border-width: 5;");
					}
					isPlayerOneTurn = !isPlayerOneTurn;

				}

			});

			gridPane.add(numberPane, columnIndex, rowIndex);
			columnIndex++;
			if (columnIndex == 10) {
				rowIndex++;
				columnIndex = 0;
			}
		}

		borderPane.setRight(gridPane);
		scene.setRoot(borderPane);
	}

//	private EventHandler<MouseEvent> createConfirmationHandler() {
//		return event -> {
//			Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//			alert.setTitle("Confirmation");
//			alert.setHeaderText("Are you sure?");
//			alert.setContentText("Do you want to proceed?");
//
//			ButtonType result = alert.showAndWait().orElse(ButtonType.CANCEL);
//
//			if (result == ButtonType.OK) {
//				System.out.println("Action confirmed!");
//			} else {
//				System.out.println("Action canceled.");
//			}
//		};
//	}
	
	
	private void showConfirmationDialog() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Are you sure?");
        alert.setContentText("Do you want to proceed with this action? Click 'OK' to confirm or 'Cancel' to abort.");

        ButtonType result = alert.showAndWait().orElse(ButtonType.CANCEL);

        if (result == ButtonType.OK) {
            // Perform the action on confirmation
            System.out.println("Action confirmed!");
            // Add your specific action logic here
        }
    }
	
//	private void populateGridPane() {
//		gridPane.getChildren().clear();
//		int columnIndex = 0;
//		int rowIndex = 0;
//
//		for (int i = 0; i < remainingCoins.size(); i++) {
//			int number = remainingCoins.get(i);
//			StackPane numberPane = createNumberPane(number, i);
//			gridPane.add(numberPane, columnIndex, rowIndex);
//			columnIndex++;
//			if (columnIndex == 10) {
//				rowIndex++;
//				columnIndex = 0;
//			}
//		}
//	}
//
//	private StackPane createNumberPane(int number, int index) {
//	        StackPane numberPane = new StackPane(new Label(String.valueOf(number)));
//	        numberPane.setPadding(new Insets(10));
//	        numberPane.setStyle("-fx-border-color: black; -fx-border-width: 2;");
//
//	        numberPane.setOnMouseClicked(event -> {
//	            if (!numberPane.getStyle().contains("disabled")) { // Ensure the number is not already chosen
//	                if (isPlayerOneTurn) {
//	                    // Player 1's turn
//	                    numberPane.setStyle("-fx-background-color: red; -fx-opacity: 0.5");
//	                } else {
//	                    // Player 2's turn
//	                    numberPane.setStyle("-fx-background-color: blue; -fx-opacity: 0.5");
//	                }
//
//	                // Disable the clicked number
//	                numberPane.setStyle("-fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.7), 10, 0, 0, 1)");
//	                numberPane.setDisable(true);
//
//	                // Toggle the player turn
//	                isPlayerOneTurn = !isPlayerOneTurn;
//
//	                // Check for game over
//	                if (allNumbersDisabled()) {
//	                    // Declare the winner based on the final score
//	                    Alert gameOverAlert = new Alert(Alert.AlertType.INFORMATION);
//	                    gameOverAlert.setTitle("Game Over");
//	                    gameOverAlert.setHeaderText("Game Over!");
//	                    gameOverAlert.setContentText(isPlayerOneTurn  ? "Player 2 wins!" : "Player 1 wins!");
//	                    gameOverAlert.showAndWait();
//	                }
//	            }
//	        });
//
//	        return numberPane;
//	    }
//
//	private boolean allNumbersDisabled() {
//		for (Node node : gridPane.getChildren()) {
//			if (node instanceof StackPane && !((StackPane) node).isDisabled()) {
//				return false;
//			}
//		}
//		return true;
//	}

//	 private boolean allNumbersDisabled() {
//	        for (Node node : gridPane.getChildren()) {
//	            if (node instanceof StackPane && !((StackPane) node).isDisabled()) {
//	                return false;
//	            }
//	        }
//	        return true;
//	    }
//	private void selectCoin(StackPane stackPane, int number) {
//	    int currentPlayer = getCurrentPlayer(); // Implement this logic
//
//	    if (currentPlayer == 1) {
//	        player1Coins.add(number);
//	        stackPane.setStyle("-fx-background-color: red; -fx-border-color: black; -fx-border-width: 2;");
//	    } else {
//	        player2Coins.add(number);
//	        stackPane.setStyle("-fx-background-color: blue; -fx-border-color: black; -fx-border-width: 2;");
//	    }
//
//	    // Switch to the next player
//	    currentPlayer = (currentPlayer == 1) ? 2 : 1;
//	}

//	private boolean getCurrentPlayer() {
//		return playerturn;
//	}
//
//	private boolean switchPlayer() {
//		if (!playerturn) {
//			playerturn = true;
//			return true;
//		}
//
//		else {
//			playerturn = false;
//			return false;
//		}
//	}
}
