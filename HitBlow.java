import java.util.Random;
import java.util.Scanner;

//数桁の数値当てゲーム
//桁数は入力により設定する(3～6桁)
//数値はランダムに生成する
//好きな数値を言っていく
//桁も数値も正しい→「Hit」
//桁は違うが数値が正しい→「Blow」

public class HitBlow {

	public static void main(String[] args) {

		Scanner stdIn = new Scanner (System.in);
		int turn = 1;  //ターン数
		boolean bl = true;

		//--- ゲーム説明 ---//
		System.out.println("--- ゲーム説明 ---");
		System.out.println("数値を予想する\n"
				+ "桁も数値もあっていれば「Hit」\n"
				+ "桁は違うが数値があっていれば「Blow」");


		//--- ゲーム内容 ---//
		System.out.println("\nゲームスタート！！");
		System.out.print("桁数を設定してください。(3-6桁) ->");
		int fig = stdIn.nextInt();   //桁数fig


		int[] num = set_number(fig); // 正解数字設定メソッドへ

		hide_ans(fig); //正解数字の桁数分の「*」表示メソッドへ

		while(bl){
			System.out.println("\n-------  ターン" + turn + "  -------");
			int[] my_ans = my_answer(fig); //配列my_ansの要素をmy_answerメソッドで得る。仮引数はfig(桁数)

			int[] status = ans_judge(fig,my_ans,num); //配列statusの要素をans_jnudgeメソッドから得る

			if(status[0] == fig) //Hitの値と桁数が一致したらblをfalseにして繰り返しから抜ける
				bl = false;

			turn++; //ターン数を+1する
		}
		System.out.print("おめでとう！！正解です！答えは ");
		for(int k= 0; k < fig; k++){
			System.out.print(num[k]);
		}
		System.out.println("でした。");
	}


	//---- 正解数字設定メソッド ----//
	public static int[] set_number(int num){
		Random rnd = new Random();
		int[] n = new int[num] ;
		for(int i = 0; i < num; i++){
			n[i] = rnd.nextInt(10);
			for(int j = i-1; j >= 0; j--){
				if(n[j] == n[i])
					i -= 1;
			}
		}
		return n;
	}

	//---- 正解数字***の表示メソッド ----//
	public static void hide_ans(int fig){
		for(int i = 0; i < fig; i++)
			System.out.print("*");
	}

	//---- ユーザーの答え入力メソッド ----//
	public static int[] my_answer(int fig){
		Scanner stdIn = new Scanner (System.in);
		int[] my_ans = new int[fig];

		System.out.print(fig+" 桁の数字を入力してください。 ->");
		for(int i = 0; i < fig; i++){
			my_ans[i] = stdIn.nextInt(); //ユーザーの答えを入力する
		}
		return my_ans;
	}

	//--- 正解・不正解判定メソッド ---//
	public static int[] ans_judge(int n, int[] my_ans, int[] num){
		int hit = 0;
		int blow = 0;
		for(int i = 0; i < n; i++){
			for(int j = 0; j < n; j++){
				if(my_ans[j] == num[i] && j == i){ //数字と桁数が同じ場合Hitを+1する
					hit += 1;
				}
				if(my_ans[j] == num[i] && j != i){ //桁数が違っているが数字が同じ場合Blowを+1する
					blow += 1;
				}
			}
		}

		System.out.println(hit + " hit " + blow + " blow");

		int status[] = {hit, blow};
		return status;
	}
}
