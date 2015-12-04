	import java.util.Random;
	import java.util.Scanner;

	public class MathGame01 {

		public static void main(String[] args) {

			Scanner stdIn = new Scanner (System.in);
			Random rnd = new Random();
			int sum;  //入力値の合計
			int turn = 1;  //ターン数

			//--- ゲーム説明 ---//
			System.out.println("--- ゲーム説明 ---");
			System.out.println("任意の数値を入力する\n"
					+ "1人1～3つずつ数字を言って増やしていき、\n"
					+ "最終的に最初に設定した数値を言った方の負け。");


			//--- ゲーム内容 ---//
			System.out.println("\nゲームスタート！！");
			System.out.print("数値を設定してください。=>");
			int number = stdIn.nextInt();   //設定数値number

			int start = rnd.nextInt(2);  //先攻後攻決め、偶数：ユーザー、奇数：PC

			//--- ユーザー先攻 ---//
			if(start % 2 == 0){
				for(sum = 0; sum <= number-1;){
					System.out.println("\n---  " + turn + "ターン目  ---");
					sum = myTurn(sum, number);  //myTurnメソッドの返却値をsumに代入
					if(sum == (number - 1)){  //現在の設定値の1個前の数値になったら
						myEnd(number); //myEndメソッドへ
						break;  //ゲーム終了
					}
					if(sum >= number){ //合計値が設定値を越えてしまったら
						yourEnd(number); //yourEndメソッドへ
						break; //ゲーム終了
					}
					sum = yourTurn(sum, number); //yourTurnメソッドの返却値をsumに代入
					if(sum == (number - 1)){ //現在の設定値の1個前の数値になったら
						yourEnd(number); //yourEndメソッドへ
						break; //ゲーム終了
					}
					if(sum >= number){ //合計値が設定値を越えてしまったら
						myEnd(number); //myEndメソッドへ
						break; //ゲーム終了
					}

					turn++;
				}
			}

			//--- 相手先攻 ---//
			else{
				for(sum = 0; sum <= number-1;){
					System.out.println("\n---  " + turn + "ターン目  ---");
					sum = yourTurn(sum, number); //yourTurnメソッドの返却値をsumに代入
					if(sum == (number - 1)){ //現在の設定値の1個前の数値になったら
						yourEnd(number); //yourEndメソッドへ
						break; //ゲーム終了
					}
					if(sum >= number){ //合計値が設定値を越えてしまったら
						myEnd(number); //myEndメソッドへ
						break; //ゲーム終了
					}

					sum = myTurn(sum, number);  //myTurnメソッドの返却値をsumに代入
					if(sum == (number - 1)){  //現在の設定値の1個前の数値になったら
						myEnd(number); //myEndメソッドへ
						break; //ゲーム終了
					}
					if(sum >= number){ //合計値が設定値を越えてしまったら
						yourEnd(number); //yourEndメソッドへ
						break; //ゲーム終了
					}

					turn++; //turnを+1する
				}
			}
		}

		public static int myTurn(int max, int number){ //ユーザーのターンメソッド(合計値, 設定値)
			Scanner stdIn = new Scanner (System.in);
			int n1 = 0;
			System.out.println("--- あなたの番です ---");
			while(n1 < 1 || n1 > 3){  //1～3以外が入力されたら再入力
				System.out.print("現在の数値："+ max +"\n1～3までいくつ増やしますか？=>");
				n1 = stdIn.nextInt();
			}
			max += n1; //現在の値を指定された分だけ増やす

			return max;  //現在値を返す
		}


		public static int yourTurn(int max, int number) { //相手のターンメソッド(合計値, 設定値)
			int n1 = 0;
			Random rnd = new Random();
			System.out.println("--- 相手の番です ---");
			System.out.print("現在の数値："+ max +"\n1～3までいくつ増やしますか？=>");

			//---- 設定値まで4つ以上残っている場合 ----//
			if(3 < (number - max))
				n1 = rnd.nextInt(3) + 1;  //1～3までの数値をランダムで生成する。

			//---- 設定値まで残り3のとき ----//
			else if((number - max) == 3)
				n1 = rnd.nextInt(2) + 1;//1～2までの数値をランダムで生成する。

			//---- 設定値まで残り2のとき ----//
			else if((number - max) == 2)
				n1 = 1;//必ず1を取る。


			else if((number - max) == 1)
				n1 = 0;
			System.out.println(n1);

			max += n1;  //生成された数値をmaxに足す
			return max;  //maxの値を返す。
		}


		public static void myEnd(int number){  //ユーザーの勝ち表示メソッド
			System.out.println("---------------  相手が  "+number+"  を宣言しました。----------------");
			System.out.println("---------------  あなたの勝ちです！！  ----------------");
		}

		public static void yourEnd(int number){  //相手の勝ち表示メソッド
			System.out.println("---------------あなたが  "+number+"  を宣言しました----------------");
			System.out.println("-------------- あなたの負けです。。。 ---------------");
		}

	}
