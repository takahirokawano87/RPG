package application;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.util.Duration;

public class FormController {
	@FXML private Button topButton;
	@FXML private Label topLabel;
	@FXML private Label mainLabel;
	@FXML private TextField topTextField;
	@FXML private TextArea topTextArea;
	@FXML private TitledPane topTitledPane;

	@FXML public void onClick(){
		topLabel.setText("押されました！");
	}

//	インスタンス変数
	int x;
	int teamAHp;
	int teamBHp;
	boolean isEnded;

	Movable[][] team;

//	コンストラクタの中でインスタンスを生成する
	public FormController() {
		team = new Movable[4][4];
		team[0][0] = new Robber("ドラえもん", 2800, 600, 400);
		team[0][1] = new Sniper("のび太", 1600, 430, 800);
		team[0][2] = new Robber("しずか", 600, 250, 200);
		team[0][3] = new Currypanman();
		team[1][0] = new Robber("ジャイアン", 4500, 1000, 620);
		team[1][1] = new Sniper("スネ夫", 1200, 400, 300);
		team[1][2] = new Sniper("出木杉", 2600, 580, 700);
		team[1][3] = new Dokinchan();

		x = 0;
		isEnded = false;

//		Timeline型の変数
//		匿名クラス=その場で継承してクラスの中身をそのまま書く（「EventHandler型を継承した名前のないクラス」の中身）
		Timeline timer = new Timeline(new KeyFrame(Duration.millis(1000), new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if(isEnded)return;
				String message = "";
//				攻撃する人を順番に選ぶ
				x++;
				int i = x%team[0].length;
				int j = (x/team[0].length)%2;
//				FormControllerのインスタンス（mainLabel）にアクセスできる
				mainLabel.setText("");
				if(team[i][j] instanceof Character) {
					message = ((Character)team[i][j]).introduce();
					mainLabel.setText(mainLabel.getText()+message);
				}


//				if(j==0) {
//					if(teamA[i] instanceof Character) {
//						if(teamB[i] instanceof Character) {
//							message = ((Character)teamA[i]).introduce();
//							mainLabel.setText(mainLabel.getText()+message);
//
//							message = teamA[i].move((Character)teamB[(int)(Math.random()*teamB.length)]);
//							mainLabel.setText(mainLabel.getText()+message);
//
//							teamAHp += ((Character)teamA[i]).getHp();
//						}else {
//							message = ((Character)teamA[i]).introduce();
//							mainLabel.setText(mainLabel.getText()+message);
//						}
//					}else {
//						if(teamB[i] instanceof Character) {
//							message = teamA[i].introduce();
//							mainLabel.setText(mainLabel.getText()+message);
//
//							message = teamA[i].move((Character)teamB[(int)(Math.random()*teamB.length)]);
//							mainLabel.setText(mainLabel.getText()+message);
//						}else {
//							message = teamA[i].introduce();
//							mainLabel.setText(mainLabel.getText()+message);
//						}
//					}
//				}else {
//					if(teamB[i] instanceof Character) {
//						if(teamA[i] instanceof Character) {
//							message = ((Character)teamB[i]).introduce();
//							mainLabel.setText(mainLabel.getText()+message);
//
//							message = (teamB[i]).move((Character)teamA[(int)(Math.random()*teamA.length)]);
//							mainLabel.setText(mainLabel.getText()+message);
//
//							teamBHp += ((Character)teamB[i]).getHp();
//						}else {
//							message = ((Character)teamB[i]).introduce();
//							mainLabel.setText(mainLabel.getText()+message);
//						}
//					}else {
//						if(teamA[i] instanceof Character) {
//							message = teamB[i].introduce();
//							mainLabel.setText(mainLabel.getText()+message);
//
//							message = teamB[i].move((Character)teamA[(int)(Math.random()*teamA.length)]);
//							mainLabel.setText(mainLabel.getText()+message);
//						}else {
//							message = teamB[i].introduce();
//							mainLabel.setText(mainLabel.getText()+message);
//						}
//					}
//				}
//				teamAHp = 0;
//				teamBHp = 0;
//				for(int k=0; k<teamA.length; k++) {
//					if(teamA[k] instanceof Character) {
//						teamAHp += ((Character)teamA[k]).getHp();
//					}
//				}
//				for(int k=0; k<teamB.length; k++) {
//					if(teamB[k] instanceof Character) {
//						teamBHp += ((Character)teamB[k]).getHp();
//					}
//				}
//				if(teamAHp<=0) {
//					topLabel.setText(mainLabel.getText()+"teamBの勝利！");
//					isEnded = true;
//				}
//				if(teamBHp<=0) {
//					topLabel.setText(mainLabel.getText()+"teamAの勝利！");
//					isEnded = true;
//				}
			}
		}));
//		回数を指定する（ここでは無限)
		timer.setCycleCount(Timeline.INDEFINITE);
		timer.play();
	}
}

//		while(true) {
//			teamAHp=0;
//			for(int i=0; i<teamA.length; i++) {
//				if(teamA[i] instanceof Character) {
//					((Character)teamA[i]).introduce();
//					teamAHp+=((Character)teamA[i]).getHp();
//				}
//				int targetId;
//				do {
//					targetId = (int)(Math.random()*teamB.length);
//				}while(!(teamB[targetId] instanceof Character));
//				teamA[i].move((Character)teamB[targetId]);
//				System.out.println("--------------------------------------------");
//			}
//			if(teamAHp<=0) {
//				System.out.println("teamBの勝利！");
//				break;
//			}
//			teamBHp=0;
//			for(int i=0; i<teamB.length; i++) {
//				if(teamB[i] instanceof Character) {
//					((Character)teamB[i]).introduce();
//					teamBHp+=((Character)teamB[i]).getHp();
//				}
//				int targetId;
//				do {
//					targetId = (int)(Math.random()*teamA.length);
//				}while(!(teamA[targetId] instanceof Character));
//				teamB[i].move((Character)teamA[targetId]);
//				System.out.println("--------------------------------------------");
//			}
//			if(teamBHp<=0) {
//				System.out.println("teamAの勝利！");
//				break;
//			}
//		}
//	}
//}
